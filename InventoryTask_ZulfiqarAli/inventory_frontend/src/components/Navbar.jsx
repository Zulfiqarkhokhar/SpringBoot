import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Navbar = () => {
  const { user, tenantId, logout, isAdmin } = useAuth();
  const location = useLocation();

  const navItems = [
    { path: '/dealers', label: 'Dealers' },
    { path: '/vehicles', label: 'Vehicles' },
  ];

  if (isAdmin()) {
    navItems.push({ path: '/admin', label: 'Admin Panel' });
  }

  return (
    <nav className="bg-white shadow-lg">
      <div className="max-w-7xl mx-auto px-4">
        <div className="flex justify-between h-16">
          <div className="flex items-center space-x-8">
            <Link to="/dashboard" className="text-xl font-bold text-gray-800">
              Inventory System
            </Link>
            <div className="flex space-x-4">
              {navItems.map((item) => (
                <Link
                  key={item.path}
                  to={item.path}
                  className={`px-3 py-2 rounded-md text-sm font-medium ${
                    location.pathname === item.path
                      ? 'bg-blue-100 text-blue-700'
                      : 'text-gray-600 hover:bg-gray-100'
                  }`}
                >
                  {item.label}
                </Link>
              ))}
            </div>
          </div>
          <div className="flex items-center space-x-4">
            <div className="text-sm text-gray-600">
              <div>User: <span className="font-medium">{user?.username}</span></div>
              <div>Role: <span className="font-medium">{user?.role}</span></div>
              <div>Tenant: <span className="font-medium">{tenantId}</span></div>
            </div>
            <button
              onClick={logout}
              className="bg-red-600 text-white px-4 py-2 rounded-lg text-sm hover:bg-red-700"
            >
              Logout
            </button>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;