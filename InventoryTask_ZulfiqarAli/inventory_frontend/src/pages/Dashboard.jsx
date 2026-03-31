import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Dashboard = () => {
  const { user, tenantId, isAdmin } = useAuth();

  const cards = [
    {
      title: 'Dealers',
      description: 'Manage dealer information, subscriptions, and contact details',
      link: '/dealers',
      icon: '🏢',
      color: 'bg-blue-500',
    },
    {
      title: 'Vehicles',
      description: 'Manage vehicle inventory, pricing, and status',
      link: '/vehicles',
      icon: '🚗',
      color: 'bg-green-500',
    },
  ];

  if (isAdmin()) {
    cards.push({
      title: 'Admin Panel',
      description: 'View system-wide statistics and subscription counts',
      link: '/admin',
      icon: '👑',
      color: 'bg-purple-500',
    });
  }

  return (
    <div className="max-w-7xl mx-auto px-4 py-8">
      <div className="mb-8">
        <h1 className="text-3xl font-bold text-gray-800">Dashboard</h1>
        <p className="text-gray-600 mt-2">
          Welcome back, {user?.username}! Managing inventory for tenant: {tenantId}
        </p>
      </div>
      
      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {cards.map((card) => (
          <Link
            key={card.title}
            to={card.link}
            className="card hover:shadow-lg transition-shadow cursor-pointer"
          >
            <div className={`${card.color} w-12 h-12 rounded-lg flex items-center justify-center text-2xl mb-4`}>
              {card.icon}
            </div>
            <h3 className="text-xl font-semibold text-gray-800 mb-2">{card.title}</h3>
            <p className="text-gray-600">{card.description}</p>
          </Link>
        ))}
      </div>
    </div>
  );
};

export default Dashboard;