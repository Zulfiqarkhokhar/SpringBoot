import React, { useState, useEffect } from 'react';
import { adminApi } from '../api/endpoints';
import LoadingSpinner from '../components/LoadingSpinner';
import toast from 'react-hot-toast';

const Admin = () => {
  const [counts, setCounts] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    loadSubscriptionCounts();
  }, []);

  const loadSubscriptionCounts = async () => {
    try {
      setLoading(true);
      const response = await adminApi.getSubscriptionCounts();
      console.log(response.data)
      setCounts(response.data);
    } catch (error) {
      console.error('Error loading subscription counts:', error);
      toast.error('Failed to load subscription counts');
    } finally {
      setLoading(false);
    }
  };

  if (loading) {
    return <LoadingSpinner />;
  }

  return (
    <div className="max-w-7xl mx-auto px-4 py-8">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-800">Admin Panel</h1>
        <p className="text-gray-600 mt-2">
          System-wide statistics and management
        </p>
      </div>
      
      <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
        <div className="card">
          <div className="text-center">
            <div className="text-4xl font-bold text-blue-600 mb-2">
              {counts?.basic || 0}
            </div>
            <div className="text-gray-600">Basic Subscriptions</div>
          </div>
        </div>
        
        <div className="card">
          <div className="text-center">
            <div className="text-4xl font-bold text-purple-600 mb-2">
              {counts?.premium || 0}
            </div>
            <div className="text-gray-600">Premium Subscriptions</div>
          </div>
        </div>
      </div>
      
      {counts?.globalCount && (
        <div className="mt-6 text-center text-sm text-gray-500">
          * These are global counts across all tenants
        </div>
      )}
    </div>
  );
};

export default Admin;