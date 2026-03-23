
export const authService = {
  login: async (email, password) => {
    // Mock API call - Replace with actual backend endpoint
    // For now, simulate successful login with dummy credentials
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        if (email === 'admin@example.com' && password === 'demo123') {
          resolve({
            token: 'dummy-jwt-token-12345',
            user: {
              id: 1,
              name: 'Admin User',
              email: 'admin@example.com',
              role: 'admin'
            }
          });
        } else {
          reject(new Error('Invalid credentials'));
        }
      }, 500);
    });

    // Uncomment this when backend is ready
    // const response = await api.post('/auth/login', { email, password });
    // return response.data;
  },

  logout: () => {
    localStorage.removeItem('token');
  },

  getCurrentUser: () => {
    const token = localStorage.getItem('token');
    if (token) {
      // Decode token or fetch user data
      return { token };
    }
    return null;
  }
};