// src/services/courseService.js
import api from './api';
import mockData from '../utils/mockData';

export const courseService = {
  getCourses: async () => {
    return new Promise((resolve) => {
      setTimeout(() => {
        resolve(mockData.courses);
      }, 500);
    });
  },

  addCourse: async (courseData) => {
    return new Promise((resolve) => {
      setTimeout(() => {
        const newCourse = {
          id: Date.now(),
          ...courseData,
          createdAt: new Date().toISOString()
        };
        mockData.courses.push(newCourse);
        resolve(newCourse);
      }, 500);
    });
  },

  updateCourse: async (id, courseData) => {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        const index = mockData.courses.findIndex(c => c.id === id);
        if (index !== -1) {
          mockData.courses[index] = { ...mockData.courses[index], ...courseData };
          resolve(mockData.courses[index]);
        } else {
          reject(new Error('Course not found'));
        }
      }, 500);
    });
  },

  deleteCourse: async (id) => {
    return new Promise((resolve, reject) => {
      setTimeout(() => {
        const index = mockData.courses.findIndex(c => c.id === id);
        if (index !== -1) {
          mockData.courses.splice(index, 1);
          resolve({ success: true });
        } else {
          reject(new Error('Course not found'));
        }
      }, 500);
    });
  },

  getCourseById: async (id) => {
    const response = await api.get(`/courses/${id}`);
    return response.data;
  }
};