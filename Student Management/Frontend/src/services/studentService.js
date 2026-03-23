// src/services/studentService.js
import api from './api';
import mockData from '../utils/mockData';

export const studentService = {
  getStudents: async () => {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve(mockData.students);
      }, 500);
    });
  },

  addStudent: async (studentData) => {
    return new Promise((resolve) => {
      setTimeout(() => {
        const newStudent = {
          id: Date.now(),
          ...studentData,
          createdAt: new Date().toISOString()
        };
        mockData.students.push(newStudent);
        resolve(newStudent);
      }, 500);
    });
  },

  updateStudent: async (id, studentData) => {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        const index = mockData.students.findIndex(s => s.id === id);
        if (index !== -1) {
          mockData.students[index] = { ...mockData.students[index], ...studentData };
          resolve(mockData.students[index]);
        } else {
          reject(new Error('Student not found'));
        }
      }, 500);
    });
  },

  deleteStudent: async (id) => {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        const index = mockData.students.findIndex(s => s.id === id);
        if (index !== -1) {
          mockData.students.splice(index, 1);
          resolve({ success: true });
        } else {
          reject(new Error('Student not found'));
        }
      }, 500);
    });
  },

  getStudentById: async (id) => {
    const response = await api.get(`/students/${id}`);
    return response.data;
  }
};