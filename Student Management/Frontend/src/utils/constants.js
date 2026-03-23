// src/utils/constants.js
export const API_ENDPOINTS = {
  AUTH: {
    LOGIN: '/auth/login',
    LOGOUT: '/auth/logout',
    REGISTER: '/auth/register'
  },
  STUDENTS: {
    BASE: '/students',
    GET_ALL: '/students',
    GET_BY_ID: '/students/:id',
    CREATE: '/students',
    UPDATE: '/students/:id',
    DELETE: '/students/:id'
  },
  COURSES: {
    BASE: '/courses',
    GET_ALL: '/courses',
    GET_BY_ID: '/courses/:id',
    CREATE: '/courses',
    UPDATE: '/courses/:id',
    DELETE: '/courses/:id'
  },
  ASSIGNMENTS: {
    BASE: '/assignments',
    GET_ALL: '/assignments',
    CREATE: '/assignments',
    DELETE: '/assignments/:id',
    GET_BY_STUDENT: '/assignments/student/:studentId',
    GET_BY_COURSE: '/assignments/course/:courseId'
  }
};

export const PAGINATION = {
  DEFAULT_PAGE: 1,
  DEFAULT_LIMIT: 10,
  PAGE_SIZE_OPTIONS: [5, 10, 20, 50]
};

export const MESSAGES = {
  SUCCESS: {
    LOGIN: 'Login successful!',
    LOGOUT: 'Logged out successfully',
    STUDENT_ADDED: 'Student added successfully!',
    COURSE_ADDED: 'Course added successfully!',
    ASSIGNMENT_CREATED: 'Course assigned successfully!'
  },
  ERROR: {
    LOGIN_FAILED: 'Invalid credentials',
    NETWORK_ERROR: 'Network error. Please try again.',
    SOMETHING_WRONG: 'Something went wrong. Please try again.',
    STUDENT_ADD_FAILED: 'Failed to add student',
    COURSE_ADD_FAILED: 'Failed to add course',
    ASSIGNMENT_FAILED: 'Failed to assign course'
  }
};