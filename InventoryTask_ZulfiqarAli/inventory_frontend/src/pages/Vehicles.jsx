/* eslint-disable no-unused-vars */
import React, { useState, useEffect, useCallback } from 'react';
import { vehicleApi, dealerApi } from '../api/endpoints';
import Table from '../components/Table';
import FormModal from '../components/FormModal';
import Pagination from '../components/Pagination';
import Filters from '../components/Filters';
import LoadingSpinner from '../components/LoadingSpinner';
import useApi from '../hooks/useApi';
import { VEHICLE_STATUSES } from '../utils/constants';
import { useDebounce } from '../hooks/useDebounce';
import toast from 'react-hot-toast';

const Vehicles = () => {
  const [vehicles, setVehicles] = useState([]);
  const [dealers, setDealers] = useState([]);
  const [pagination, setPagination] = useState({
    pageNumber: 0,
    pageSize: 5,
    totalElements: 0,
    totalPages: 0,
  });
  const [sort, setSort] = useState({ sortBy: 'model', direction: 'ASC' });
  const [filters, setFilters] = useState({
    model: '',
    status: '',
    priceMin: '',
    priceMax: '',
    subscription: false,
  });
  const [modalOpen, setModalOpen] = useState(false);
  const [editingVehicle, setEditingVehicle] = useState(null);
  
  const debouncedModel = useDebounce(filters.model, 500);
  
  const { execute: fetchVehicles, loading } = useApi(vehicleApi.getAll);
  const { execute: createVehicle } = useApi(vehicleApi.create);
  const { execute: updateVehicle } = useApi(vehicleApi.update);
  const { execute: deleteVehicle } = useApi(vehicleApi.delete);
  const { execute: fetchDealers } = useApi(dealerApi.getAll);

  const loadDealers = useCallback(async () => {
    try {
      const response = await fetchDealers({ page: 0, size: 100 });
      setDealers(response.content);
    } catch (error) {
      console.error('Error loading dealers:', error);
    }
  }, [fetchDealers]);

  const loadVehicles = useCallback(async () => {
    try {
      const params = {
        page: pagination.pageNumber,
        size: pagination.pageSize,
        sortBy: sort.sortBy,
        direction: sort.direction,
        ...filters,
        priceMin: filters.priceMin ? parseFloat(filters.priceMin) : undefined,
        priceMax: filters.priceMax ? parseFloat(filters.priceMax) : undefined,
      };
      
      if (filters.subscription) {
        params.subscription = true;
      }
      
      const response = await fetchVehicles(params);
      
      setVehicles(response.content);
      setPagination({
        pageNumber: response.pageNumber,
        pageSize: response.pageSize,
        totalElements: response.totalElements,
        totalPages: response.totalPages,
      });
    } catch (error) {
      console.error('Error loading vehicles:', error);
    }
  }, [fetchVehicles, pagination.pageNumber, pagination.pageSize, sort, filters]);

  useEffect(() => {
    // eslint-disable-next-line react-hooks/set-state-in-effect
    loadDealers();
  }, [loadDealers]);

  useEffect(() => {
    // eslint-disable-next-line react-hooks/set-state-in-effect
    loadVehicles();
  }, [loadVehicles]);

  const handleCreate = async (data) => {
    try {
      await createVehicle(data);
      toast.success('Vehicle created successfully');
      loadVehicles();
    } catch (error) {
      console.error('Error creating vehicle:', error);
    }
  };

  const handleUpdate = async (data) => {
    try {
      await updateVehicle(editingVehicle.id, data);
      toast.success('Vehicle updated successfully');
      setEditingVehicle(null);
      loadVehicles();
    } catch (error) {
      console.error('Error updating vehicle:', error);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this vehicle?')) {
      try {
        await deleteVehicle(id);
        toast.success('Vehicle deleted successfully');
        loadVehicles();
      } catch (error) {
        console.error('Error deleting vehicle:', error);
      }
    }
  };

  const handleEdit = (vehicle) => {
    setEditingVehicle(vehicle);
    setModalOpen(true);
  };

  const handleSubmit = (data) => {
    if (editingVehicle) {
      handleUpdate(data);
    } else {
      handleCreate(data);
    }
  };

  const handleFilterChange = (newFilters) => {
    setFilters(prev => ({ ...prev, ...newFilters }));
    setPagination(prev => ({ ...prev, pageNumber: 0 }));
  };

  const resetFilters = () => {
    setFilters({
      model: '',
      status: '',
      priceMin: '',
      priceMax: '',
      subscription: false,
    });
    setPagination(prev => ({ ...prev, pageNumber: 0 }));
  };

  const columns = [
    { key: 'model', label: 'Model' },
    {
      key: 'price',
      label: 'Price',
      render: (value) => `$${value?.toLocaleString()}`,
    },
    { key: 'status', label: 'Status' },
    {
      key: 'dealerId',
      label: 'Dealer',
      render: (value) => {
        const dealer = dealers.find(d => d.id === value);
        return dealer?.name || value;
      },
    },
  ];

  const formFields = [
    {
      name: 'dealerId',
      label: 'Dealer',
      type: 'select',
      required: true,
      options: dealers.map(d => ({ value: d.id, label: d.name })),
    },
    { name: 'model', label: 'Model', type: 'text', required: true },
    { name: 'price', label: 'Price', type: 'number', required: true, step: '0.01' },
    {
      name: 'status',
      label: 'Status',
      type: 'select',
      required: true,
      options: VEHICLE_STATUSES,
    },
  ];

  const filterConfig = [
    { name: 'model', label: 'Model', type: 'text', placeholder: 'Search by model...', value: filters.model },
    {
      name: 'status',
      label: 'Status',
      type: 'select',
      options: VEHICLE_STATUSES,
      value: filters.status,
    },
    { name: 'priceMin', label: 'Min Price', type: 'number', placeholder: 'Min', value: filters.priceMin },
    { name: 'priceMax', label: 'Max Price', type: 'number', placeholder: 'Max', value: filters.priceMax },
    { name: 'subscription', label: 'Premium Only', type: 'checkbox', value: filters.subscription },
  ];

  return (
    <div className="max-w-7xl mx-auto px-4 py-8">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold text-gray-800">Vehicles</h1>
        <button
          onClick={() => {
            setEditingVehicle(null);
            setModalOpen(true);
          }}
          className="btn-primary"
        >
          Add Vehicle
        </button>
      </div>
      
      <Filters
        filters={filterConfig}
        onFilterChange={handleFilterChange}
        onReset={resetFilters}
      />
      
      {loading ? (
        <LoadingSpinner />
      ) : (
        <>
          <Table
            columns={columns}
            data={vehicles}
            actions={true}
            onEdit={handleEdit}
            onDelete={handleDelete}
          />
          
          <Pagination
            currentPage={pagination.pageNumber}
            totalPages={pagination.totalPages}
            onPageChange={(page) => setPagination(prev => ({ ...prev, pageNumber: page }))}
          />
        </>
      )}
      
      <FormModal
        isOpen={modalOpen}
        onClose={() => {
          setModalOpen(false);
          setEditingVehicle(null);
        }}
        onSubmit={handleSubmit}
        title={editingVehicle ? 'Edit Vehicle' : 'Add Vehicle'}
        fields={formFields}
        initialData={editingVehicle}
      />
    </div>
  );
};

export default Vehicles;