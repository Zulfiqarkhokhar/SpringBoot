import React, { useState } from 'react';
import { useAuth } from '../context/AuthContext';

const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [tenantId, setTenantId] = useState('');
  const [loading, setLoading] = useState(false);
  const { login } = useAuth();

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!username || !password || !tenantId) {
      return;
    }
    
    setLoading(true);
    await login(username, password, tenantId);
    setLoading(false);
  };

  return (
    <div className="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-50 to-gray-100">
      <div className="card w-full max-w-md">
        <div className="text-center mb-8">
          <div className="text-5xl mb-4">🚗</div>
          <h1 className="text-3xl font-bold text-gray-800">Inventory System</h1>
          <p className="text-gray-600 mt-2">Dealer & Vehicle Management</p>
        </div>
        
        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Username
            </label>
            <input
              type="text"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              className="input-field"
              placeholder="Enter username"
              required
            />
          </div>
          
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Password
            </label>
            <input
              type="password"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="input-field"
              placeholder="Enter password"
              required
            />
          </div>
          
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              Tenant ID
            </label>
            <input
              type="text"
              value={tenantId}
              onChange={(e) => setTenantId(e.target.value)}
              className="input-field"
              placeholder="e.g., tenant1"
              required
            />
          </div>
          
          <button 
            type="submit" 
            className="btn-primary w-full"
            disabled={loading}
          >
            {loading ? 'Logging in...' : 'Login'}
          </button>
        </form>
        
        {/* <div className="mt-8 pt-6 border-t border-gray-200">
          <div className="text-sm text-gray-600">
            <p className="font-semibold text-center mb-3">Demo Credentials:</p>
            <div className="space-y-2">
              <div className="bg-blue-50 p-3 rounded-lg">
                <p className="font-medium text-blue-800">Regular User</p>
                <p className="text-xs">Username: <span className="font-mono">user</span></p>
                <p className="text-xs">Password: <span className="font-mono">password</span></p>
                <p className="text-xs">Tenant: <span className="font-mono">tenant1</span> or <span className="font-mono">tenant2</span></p>
              </div>
              <div className="bg-purple-50 p-3 rounded-lg">
                <p className="font-medium text-purple-800">Admin User</p>
                <p className="text-xs">Username: <span className="font-mono">admin</span></p>
                <p className="text-xs">Password: <span className="font-mono">admin123</span></p>
                <p className="text-xs">Tenant: <span className="font-mono">tenant1</span> or <span className="font-mono">tenant2</span></p>
              </div>
            </div>
          </div>
        </div> */}
      </div>
    </div>
  );
};

export default Login;