// src/utils/validators.js
export const validators = {
  required: (value) => {
    if (!value || value.trim() === '') {
      return 'This field is required';
    }
    return null;
  },

  email: (value) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailRegex.test(value)) {
      return 'Please enter a valid email address';
    }
    return null;
  },

  phone: (value) => {
    const phoneRegex = /^\d{10,}$/;
    if (!phoneRegex.test(value.replace(/\D/g, ''))) {
      return 'Please enter a valid phone number (minimum 10 digits)';
    }
    return null;
  },

  minLength: (min) => (value) => {
    if (value.length < min) {
      return `Minimum ${min} characters required`;
    }
    return null;
  },

  maxLength: (max) => (value) => {
    if (value.length > max) {
      return `Maximum ${max} characters allowed`;
    }
    return null;
  },

  number: (value) => {
    if (isNaN(value) || value <= 0) {
      return 'Please enter a valid positive number';
    }
    return null;
  },

  validateForm: (data, rules) => {
    const errors = {};
    for (const [field, fieldRules] of Object.entries(rules)) {
      for (const rule of fieldRules) {
        const error = rule(data[field]);
        if (error) {
          errors[field] = error;
          break;
        }
      }
    }
    return errors;
  }
};