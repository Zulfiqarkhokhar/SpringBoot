// src/components/layout/Navbar.jsx
import React from 'react';
import { Menu, User } from 'lucide-react';
// import { useAuth } from '../../context/AuthContext';

const Navbar = ({ onMenuClick }) => {
//   const { user } = useAuth();

  return (
    <nav className="bg-white shadow-sm border-b border-gray-200">
      <div className="px-4 py-3 lg:px-6">
        <div className="flex items-center justify-between">
          <div className="flex items-center">
            <button
              onClick={onMenuClick}
              className="p-2 rounded-md text-gray-500 lg:hidden hover:bg-gray-100"
            >
              <Menu className="w-5 h-5" />
            </button>
            <h2 className="ml-2 text-lg font-semibold text-gray-800 lg:ml-0">
              Student Management System
            </h2>
          </div>
          
          <div className="flex items-center space-x-3">
            <div className="flex items-center space-x-2">
              <div className="w-8 h-8 bg-primary-100 rounded-full flex items-center justify-center">
                <User className="w-4 h-4 text-primary-600" />
              </div>
              <span className="text-sm font-medium text-gray-700 hidden sm:block">
                Admin User
              </span>
            </div>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;