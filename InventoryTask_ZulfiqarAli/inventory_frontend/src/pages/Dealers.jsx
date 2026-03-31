/* eslint-disable no-unused-vars */
import React, { useState, useEffect, useCallback } from 'react';
import { dealerApi } from '../api/endpoints';
import Table from '../components/Table';
import FormModal from '../components/FormModal';
import Pagination from '../components/Pagination';
import LoadingSpinner from '../components/LoadingSpinner';
import useApi from '../hooks/useApi';
import { SUBSCRIPTION_TYPES } from '../utils/constants';
import toast from 'react-hot-toast';

const Dealers = () => {
  const [dealers, setDealers] = useState([]);
  const [pagination, setPagination] = useState({
    pageNumber: 0,
    pageSize: 5,
    totalElements: 0,
    totalPages: 0,
  });
  const [sort, setSort] = useState({ sortBy: 'name', direction: 'ASC' });
  const [modalOpen, setModalOpen] = useState(false);
  const [editingDealer, setEditingDealer] = useState(null);
  
  const { execute: fetchDealers, loading } = useApi(dealerApi.getAll);
  const { execute: createDealer } = useApi(dealerApi.create);
  const { execute: updateDealer } = useApi(dealerApi.update);
  const { execute: deleteDealer } = useApi(dealerApi.delete);

  const loadDealers = useCallback(async () => {
    try {
      const response = await fetchDealers({
        page: pagination.pageNumber,
        size: pagination.pageSize,
        sortBy: sort.sortBy,
        direction: sort.direction,
      });
      
      setDealers(response.content);
      setPagination({
        pageNumber: response.pageNumber,
        pageSize: response.pageSize,
        totalElements: response.totalElements,
        totalPages: response.totalPages,
      });
    } catch (error) {
      console.error('Error loading dealers:', error);
    }
  }, [fetchDealers, pagination.pageNumber, pagination.pageSize, sort]);

  useEffect(() => {
    // eslint-disable-next-line react-hooks/set-state-in-effect
    loadDealers();
  }, [loadDealers]);

  const handleCreate = async (data) => {
    try {
      await createDealer(data);
      toast.success('Dealer created successfully');
      loadDealers();
    } catch (error) {
      console.error('Error creating dealer:', error);
    }
  };

  const handleUpdate = async (data) => {
    try {
      await updateDealer(editingDealer.id, data);
      toast.success('Dealer updated successfully');
      setEditingDealer(null);
      loadDealers();
    } catch (error) {
      console.error('Error updating dealer:', error);
    }
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this dealer?')) {
      try {
        await deleteDealer(id);
        toast.success('Dealer deleted successfully');
        loadDealers();
      } catch (error) {
        console.error('Error deleting dealer:', error);
      }
    }
  };

  const handleEdit = (dealer) => {
    setEditingDealer(dealer);
    setModalOpen(true);
  };

  const handleSubmit = (data) => {
    if (editingDealer) {
      handleUpdate(data);
    } else {
      handleCreate(data);
    }
  };

  const columns = [
    { key: 'name', label: 'Name' },
    { key: 'email', label: 'Email' },
    { key: 'subscriptionType', label: 'Subscription Type' },
  ];

  const formFields = [
    { name: 'name', label: 'Name', type: 'text', required: true },
    { name: 'email', label: 'Email', type: 'email', required: true },
    {
      name: 'subscriptionType',
      label: 'Subscription Type',
      type: 'select',
      required: true,
      options: SUBSCRIPTION_TYPES,
    },
  ];

  return (
    <div className="max-w-7xl mx-auto px-4 py-8">
      <div className="flex justify-between items-center mb-6">
        <h1 className="text-3xl font-bold text-gray-800">Dealers</h1>
        <button
          onClick={() => {
            setEditingDealer(null);
            setModalOpen(true);
          }}
          className="btn-primary"
        >
          Add Dealer
        </button>
      </div>
      
      {loading ? (
        <LoadingSpinner />
      ) : (
        <>
          <Table
            columns={columns}
            data={dealers}
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
          setEditingDealer(null);
        }}
        onSubmit={handleSubmit}
        title={editingDealer ? 'Edit Dealer' : 'Add Dealer'}
        fields={formFields}
        initialData={editingDealer}
      />
    </div>
  );
};

export default Dealers;