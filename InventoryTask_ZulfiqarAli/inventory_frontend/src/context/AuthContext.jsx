/* eslint-disable no-unused-vars */
import React, { createContext, useState, useContext, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axiosInstance from '../api/axios';
import toast from 'react-hot-toast';

const AuthContext = createContext();

// eslint-disable-next-line react-refresh/only-export-components
export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within AuthProvider');
  }
  return context;
};

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [tenantId, setTenantId] = useState('');
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const storedAuth = localStorage.getItem('auth');
    const storedTenant = localStorage.getItem('tenantId');
    const storedUser = localStorage.getItem('user');
    
    if (storedAuth && storedTenant && storedUser) {
      const authData = JSON.parse(storedAuth);
      const userData = JSON.parse(storedUser);
      // eslint-disable-next-line react-hooks/set-state-in-effect
      setUser(userData);
      setTenantId(storedTenant);
    }
    setLoading(false);
  }, []);

  const login = async (username, password, tenant) => {
    try {
      const response = await axiosInstance.post('/api/auth/login', {
        username,
        password,
        tenantId: tenant
      });
      
      if (response.data.authenticated) {
        const authData = { username, password };
        localStorage.setItem('auth', JSON.stringify(authData));
        localStorage.setItem('tenantId', tenant);
        localStorage.setItem('user', JSON.stringify({
          username: response.data.username,
          role: response.data.role
        }));
        
        setUser({ username: response.data.username, role: response.data.role });
        setTenantId(tenant);
        
        toast.success(response.data.message);
        navigate('/dashboard');
      } else {
        toast.error('Authentication failed');
      }
    } catch (error) {
      const errorMessage = error.response?.data?.message || 'Login failed. Please check your credentials.';
      toast.error(errorMessage);
      console.error('Login error:', error);
    }
  };

  const logout = () => {
    localStorage.removeItem('auth');
    localStorage.removeItem('tenantId');
    localStorage.removeItem('user');
    setUser(null);
    setTenantId('');
    toast.success('Logged out successfully');
    navigate('/login');
  };

  const isAdmin = () => {
    return user?.role === 'GLOBAL_ADMIN';
  };

  const value = {
    user,
    tenantId,
    login,
    logout,
    isAdmin,
    loading,
  };

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};