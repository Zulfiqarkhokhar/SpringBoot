import axiosInstance from './axios';

export const dealerApi = {
  getAll: (params) => axiosInstance.get('/api/dealers', { params }),
  getById: (id) => axiosInstance.get(`/api/dealers/${id}`),
  create: (data) => axiosInstance.post('/api/dealers', data),
  update: (id, data) => axiosInstance.patch(`/api/dealers/${id}`, data),
  delete: (id) => axiosInstance.delete(`/api/dealers/${id}`),
};

export const vehicleApi = {
  getAll: (params) => axiosInstance.get('/api/vehicles', { params }),
  getById: (id) => axiosInstance.get(`/api/vehicles/${id}`),
  create: (data) => axiosInstance.post('/api/vehicles', data),
  update: (id, data) => axiosInstance.patch(`/api/vehicles/${id}`, data),
  delete: (id) => axiosInstance.delete(`/api/vehicles/${id}`),
};

export const adminApi = {
  getSubscriptionCounts: () => axiosInstance.get('/api/admin/dealers/countBySubscription'),
};