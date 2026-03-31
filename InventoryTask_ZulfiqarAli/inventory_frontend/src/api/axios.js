import axios from 'axios';

const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8080';

const axiosInstance = axios.create({
  baseURL: API_BASE_URL,
  timeout: 10000,
});

axiosInstance.interceptors.request.use(
  (config) => {
    // Don't add auth header for login endpoint
    if (config.url === '/api/auth/login') {
      return config;
    }
    
    const auth = localStorage.getItem('auth');
    const tenantId = localStorage.getItem('tenantId');
    
    if (auth) {
      const { username, password } = JSON.parse(auth);
      const credentials = btoa(`${username}:${password}`);
      config.headers.Authorization = `Basic ${credentials}`;
    }
    
    if (tenantId) {
      config.headers['X-Tenant-Id'] = tenantId;
    }
    
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

axiosInstance.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      // Don't redirect for login endpoint
      if (!error.config.url?.includes('/api/auth/login')) {
        localStorage.removeItem('auth');
        localStorage.removeItem('tenantId');
        localStorage.removeItem('user');
        window.location.href = '/login';
      }
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;