// src/pages/AssignCourse.jsx
import React, { useState, useEffect } from 'react';
import Card from '../components/common/Card';
import Button from '../components/common/Button';
import Table from '../components/common/Table';
import Pagination from '../components/common/Pagination';
import LoadingSpinner from '../components/common/LoadingSpinner';
import { studentService } from '../services/studentService';
import { courseService } from '../services/courseService';
import { assignmentService } from '../services/assignmentService';
import toast from 'react-hot-toast';
import { Trash2 } from 'lucide-react';

const AssignCourse = () => {
  const [loading, setLoading] = useState(false);
  const [students, setStudents] = useState([]);
  const [courses, setCourses] = useState([]);
  const [assignments, setAssignments] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5;
  const [formData, setFormData] = useState({
    studentId: '',
    courseId: ''
  });
  const [selectedCourse, setSelectedCourse] = useState(null);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    setLoading(true);
    try {
      const [studentsData, coursesData, assignmentsData] = await Promise.all([
        studentService.getStudents(),
        courseService.getCourses(),
        assignmentService.getAssignments()
      ]);
      setStudents(studentsData);
      setCourses(coursesData);
      setAssignments(assignmentsData);
    } catch (error) {
      console.error('Error fetching data:', error);
      toast.error('Failed to load data');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    if (formData.courseId) {
      const course = courses.find(c => c.id === parseInt(formData.courseId));
      setSelectedCourse(course);
    } else {
      setSelectedCourse(null);
    }
  }, [formData.courseId, courses]);

  const validateForm = () => {
    const newErrors = {};
    if (!formData.studentId) newErrors.studentId = 'Please select a student';
    if (!formData.courseId) newErrors.courseId = 'Please select a course';
    return newErrors;
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData(prev => ({ ...prev, [name]: value }));
    if (errors[name]) {
      setErrors(prev => ({ ...prev, [name]: '' }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const newErrors = validateForm();
    if (Object.keys(newErrors).length > 0) {
      setErrors(newErrors);
      return;
    }

    setLoading(true);
    try {
      await assignmentService.assignCourse({
        studentId: parseInt(formData.studentId),
        courseId: parseInt(formData.courseId)
      });
      toast.success('Course assigned successfully!');
      setFormData({ studentId: '', courseId: '' });
      setSelectedCourse(null);
      fetchData(); // Refresh the assignments list
    } catch (error) {
      toast.error('Failed to assign course. Please try again.');
    } finally {
      setLoading(false);
    }
  };

  const handleDeleteAssignment = async (id) => {
    if (window.confirm('Are you sure you want to remove this course assignment?')) {
      try {
        await assignmentService.removeAssignment(id);
        toast.success('Course assignment removed successfully');
        fetchData();
      } catch (error) {
        toast.error('Failed to remove assignment');
      }
    }
  };

  const columns = [
    { header: 'Student Name', accessor: 'studentName' },
    { header: 'Student Email', accessor: 'studentEmail' },
    { header: 'Course', accessor: 'courseName' },
    { header: 'Price', accessor: 'coursePrice', render: (row) => `$${row.coursePrice}` },
    { header: 'Assigned Date', accessor: 'assignedDate', render: (row) => new Date(row.assignedDate).toLocaleDateString() },
    { 
      header: 'Actions', 
      render: (row) => (
        <button
          onClick={() => handleDeleteAssignment(row.id)}
          className="text-red-600 hover:text-red-800 transition-colors"
          title="Remove Assignment"
        >
          <Trash2 className="w-4 h-4" />
        </button>
      )
    }
  ];

  // Prepare assignments data with student and course details
  const assignmentsWithDetails = assignments.map(assignment => {
    const student = students.find(s => s.id === assignment.studentId);
    const course = courses.find(c => c.id === assignment.courseId);
    return {
      id: assignment.id,
      studentName: student?.name || 'Unknown',
      studentEmail: student?.email || 'Unknown',
      courseName: course?.name || 'Unknown',
      coursePrice: course?.price || 0,
      assignedDate: assignment.assignedDate
    };
  });

  const startIndex = (currentPage - 1) * itemsPerPage;
  const paginatedAssignments = assignmentsWithDetails.slice(startIndex, startIndex + itemsPerPage);

  if (loading && students.length === 0) {
    return <LoadingSpinner />;
  }

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-800">Assign Course to Student</h1>
      
      <Card>
        <form onSubmit={handleSubmit} className="space-y-6">
          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Select Student <span className="text-red-500">*</span>
            </label>
            <select
              name="studentId"
              value={formData.studentId}
              onChange={handleChange}
              className={`w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent ${
                errors.studentId ? 'border-red-500' : ''
              }`}
            >
              <option value="">Choose a student...</option>
              {students.map(student => (
                <option key={student.id} value={student.id}>
                  {student.name} - {student.email}
                </option>
              ))}
            </select>
            {errors.studentId && (
              <p className="mt-1 text-sm text-red-500">{errors.studentId}</p>
            )}
          </div>

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Select Course <span className="text-red-500">*</span>
            </label>
            <select
              name="courseId"
              value={formData.courseId}
              onChange={handleChange}
              className={`w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent ${
                errors.courseId ? 'border-red-500' : ''
              }`}
            >
              <option value="">Choose a course...</option>
              {courses.map(course => (
                <option key={course.id} value={course.id}>
                  {course.name} - ${course.price}
                </option>
              ))}
            </select>
            {errors.courseId && (
              <p className="mt-1 text-sm text-red-500">{errors.courseId}</p>
            )}
          </div>

          {selectedCourse && (
            <div className="bg-gray-50 p-4 rounded-lg">
              <h3 className="text-sm font-medium text-gray-700 mb-2">Course Details:</h3>
              <p className="text-sm text-gray-600">
                <strong>Name:</strong> {selectedCourse.name}
              </p>
              <p className="text-sm text-gray-600 mt-1">
                <strong>Description:</strong> {selectedCourse.description}
              </p>
              <p className="text-sm text-gray-600 mt-1">
                <strong>Price:</strong> ${selectedCourse.price}
              </p>
            </div>
          )}

          <div className="flex justify-end">
            <Button
              type="submit"
              variant="primary"
              loading={loading}
            >
              Assign Course
            </Button>
          </div>
        </form>
      </Card>

      {/* Assignments List Table */}
      <Card title="Current Course Assignments">
        <Table columns={columns} data={paginatedAssignments} />
        <Pagination
          currentPage={currentPage}
          totalPages={Math.ceil(assignmentsWithDetails.length / itemsPerPage)}
          onPageChange={setCurrentPage}
          itemsPerPage={itemsPerPage}
          totalItems={assignmentsWithDetails.length}
        />
      </Card>
    </div>
  );
};

export default AssignCourse;