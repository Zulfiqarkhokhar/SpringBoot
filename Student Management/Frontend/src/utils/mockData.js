// src/utils/mockData.js
const mockData = {
  students: [
    {
      id: 1,
      name: 'John Doe',
      email: 'john.doe@example.com',
      phone: '1234567890',
      address: '123 Main St, New York, NY 10001'
    },
    {
      id: 2,
      name: 'Jane Smith',
      email: 'jane.smith@example.com',
      phone: '0987654321',
      address: '456 Oak Ave, Los Angeles, CA 90001'
    },
    {
      id: 3,
      name: 'Mike Johnson',
      email: 'mike.johnson@example.com',
      phone: '5551234567',
      address: '789 Pine Rd, Chicago, IL 60601'
    },
    {
      id: 4,
      name: 'Emily Brown',
      email: 'emily.brown@example.com',
      phone: '4449876543',
      address: '321 Elm St, Houston, TX 77001'
    },
    {
      id: 5,
      name: 'David Wilson',
      email: 'david.wilson@example.com',
      phone: '7775558888',
      address: '654 Maple Dr, Phoenix, AZ 85001'
    }
  ],

  courses: [
    {
      id: 1,
      name: 'React Development',
      description: 'Learn React.js from basics to advanced',
      price: 299.99
    },
    {
      id: 2,
      name: 'Spring Boot Masterclass',
      description: 'Build REST APIs with Spring Boot',
      price: 399.99
    },
    {
      id: 3,
      name: 'Full Stack Web Development',
      description: 'Complete guide to web development',
      price: 499.99
    },
    {
      id: 4,
      name: 'Python Programming',
      description: 'Python from zero to hero',
      price: 199.99
    },
    {
      id: 5,
      name: 'Database Design',
      description: 'Master SQL and database design',
      price: 249.99
    }
  ],

  assignments: [
    {
      id: 1,
      studentId: 1,
      courseId: 1,
      assignedDate: '2024-01-15T10:00:00Z'
    },
    {
      id: 2,
      studentId: 1,
      courseId: 2,
      assignedDate: '2024-01-20T10:00:00Z'
    },
    {
      id: 3,
      studentId: 2,
      courseId: 1,
      assignedDate: '2024-01-18T10:00:00Z'
    },
    {
      id: 4,
      studentId: 3,
      courseId: 3,
      assignedDate: '2024-01-22T10:00:00Z'
    },
    {
      id: 5,
      studentId: 4,
      courseId: 4,
      assignedDate: '2024-01-25T10:00:00Z'
    }
  ]
};

export default mockData;