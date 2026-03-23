// src/services/assignmentService.js
import api from './api';
import mockData from '../utils/mockData';

export const assignmentService = {
  getAssignments: async () => {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve(mockData.assignments);
      }, 500);
    });
  },

  assignCourse: async (assignmentData) => {
    return new Promise((resolve) => {
      setTimeout(() => {
        const newAssignment = {
          id: Date.now(),
          ...assignmentData,
          assignedDate: new Date().toISOString()
        };
        mockData.assignments.push(newAssignment);
        resolve(newAssignment);
      }, 500);
    });
  },

  removeAssignment: async (id) => {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        const index = mockData.assignments.findIndex(a => a.id === id);
        if (index !== -1) {
          mockData.assignments.splice(index, 1);
          resolve({ success: true });
        } else {
          reject(new Error('Assignment not found'));
        }
      }, 500);
    });
  },

  getStudentAssignments: async (studentId) => {
    const response = await api.get(`/assignments/student/${studentId}`);
    return response.data;
  },

  getCourseAssignments: async (courseId) => {
    const response = await api.get(`/assignments/course/${courseId}`);
    return response.data;
  }
};