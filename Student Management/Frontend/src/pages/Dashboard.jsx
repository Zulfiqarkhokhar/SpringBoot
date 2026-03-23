// src/pages/Dashboard.jsx
import React, { useState, useEffect } from 'react';
import { Users, BookOpen, Link2, Edit, Trash2, Eye } from 'lucide-react';
import SummaryCard from '../components/dashboard/SummaryCard';
import Card from '../components/common/Card';
import Table from '../components/common/Table';
import Pagination from '../components/common/Pagination';
import LoadingSpinner from '../components/common/LoadingSpinner';
import Button from '../components/common/Button';
import { studentService } from '../services/studentService';
import { courseService } from '../services/courseService';
import { assignmentService } from '../services/assignmentService';
import toast from 'react-hot-toast';

const Dashboard = () => {
  const [students, setStudents] = useState([]);
  const [courses, setCourses] = useState([]);
  const [assignments, setAssignments] = useState([]);
  const [studentCourses, setStudentCourses] = useState([]);
  const [loading, setLoading] = useState(true);
  
  // Pagination states
  const [studentPage, setStudentPage] = useState(1);
  const [coursePage, setCoursePage] = useState(1);
  const [enrollmentPage, setEnrollmentPage] = useState(1);
  const itemsPerPage = 5;

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
      
      // Create student-course relationship data
      const relationshipData = assignmentsData.map(assignment => {
        const student = studentsData.find(s => s.id === assignment.studentId);
        const course = coursesData.find(c => c.id === assignment.courseId);
        return {
          id: assignment.id,
          studentName: student?.name || 'Unknown',
          studentEmail: student?.email || 'Unknown',
          courseName: course?.name || 'Unknown',
          coursePrice: course?.price || 0,
          assignedDate: assignment.assignedDate
        };
      });
      setStudentCourses(relationshipData);
    } catch (error) {
      console.error('Error fetching dashboard data:', error);
      toast.error('Failed to load dashboard data');
    } finally {
      setLoading(false);
    }
  };

  const handleDeleteStudent = async (id) => {
    if (window.confirm('Are you sure you want to delete this student?')) {
      try {
        await studentService.deleteStudent(id);
        toast.success('Student deleted successfully');
        fetchData(); // Refresh data
      } catch (error) {
        toast.error('Failed to delete student');
      }
    }
  };

  const handleDeleteCourse = async (id) => {
    if (window.confirm('Are you sure you want to delete this course?')) {
      try {
        await courseService.deleteCourse(id);
        toast.success('Course deleted successfully');
        fetchData();
      } catch (error) {
        toast.error('Failed to delete course');
      }
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

  const studentColumns = [
    { header: 'Name', accessor: 'name' },
    { header: 'Email', accessor: 'email' },
    { header: 'Phone', accessor: 'phone' },
    { header: 'Address', accessor: 'address' },
    { 
      header: 'Actions', 
      render: (row) => (
        <div className="flex space-x-2">
          <button
            onClick={() => handleEditStudent(row)}
            className="text-blue-600 hover:text-blue-800 transition-colors"
            title="Edit"
          >
            <Edit className="w-4 h-4" />
          </button>
          <button
            onClick={() => handleDeleteStudent(row.id)}
            className="text-red-600 hover:text-red-800 transition-colors"
            title="Delete"
          >
            <Trash2 className="w-4 h-4" />
          </button>
        </div>
      )
    }
  ];

  const courseColumns = [
    { header: 'Course Name', accessor: 'name' },
    { header: 'Description', accessor: 'description' },
    { header: 'Price', accessor: 'price', render: (row) => `$${row.price}` },
    { 
      header: 'Actions', 
      render: (row) => (
        <div className="flex space-x-2">
          <button
            onClick={() => handleEditCourse(row)}
            className="text-blue-600 hover:text-blue-800 transition-colors"
            title="Edit"
          >
            <Edit className="w-4 h-4" />
          </button>
          <button
            onClick={() => handleDeleteCourse(row.id)}
            className="text-red-600 hover:text-red-800 transition-colors"
            title="Delete"
          >
            <Trash2 className="w-4 h-4" />
          </button>
        </div>
      )
    }
  ];

  const enrollmentColumns = [
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

  const handleEditStudent = (student) => {
    // You can implement edit modal or navigate to edit page
    console.log('Edit student:', student);
    toast.success('Edit functionality coming soon!');
  };

  const handleEditCourse = (course) => {
    console.log('Edit course:', course);
    toast.success('Edit functionality coming soon!');
  };

  // Pagination logic
  const studentStartIndex = (studentPage - 1) * itemsPerPage;
  const paginatedStudents = students.slice(studentStartIndex, studentStartIndex + itemsPerPage);
  
  const courseStartIndex = (coursePage - 1) * itemsPerPage;
  const paginatedCourses = courses.slice(courseStartIndex, courseStartIndex + itemsPerPage);
  
  const enrollmentStartIndex = (enrollmentPage - 1) * itemsPerPage;
  const paginatedEnrollments = studentCourses.slice(enrollmentStartIndex, enrollmentStartIndex + itemsPerPage);

  if (loading) {
    return <LoadingSpinner />;
  }

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-800">Dashboard</h1>
      
      {/* Summary Cards */}
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <SummaryCard
          title="Total Students"
          value={students.length}
          icon={Users}
          color="blue"
        />
        <SummaryCard
          title="Total Courses"
          value={courses.length}
          icon={BookOpen}
          color="green"
        />
        <SummaryCard
          title="Total Enrollments"
          value={studentCourses.length}
          icon={Link2}
          color="purple"
        />
      </div>

      {/* Students Table */}
      <Card title="Student List">
        <Table columns={studentColumns} data={paginatedStudents} />
        <Pagination
          currentPage={studentPage}
          totalPages={Math.ceil(students.length / itemsPerPage)}
          onPageChange={setStudentPage}
          itemsPerPage={itemsPerPage}
          totalItems={students.length}
        />
      </Card>

      {/* Courses Table */}
      <Card title="Course List">
        <Table columns={courseColumns} data={paginatedCourses} />
        <Pagination
          currentPage={coursePage}
          totalPages={Math.ceil(courses.length / itemsPerPage)}
          onPageChange={setCoursePage}
          itemsPerPage={itemsPerPage}
          totalItems={courses.length}
        />
      </Card>

      {/* Student-Course Enrollments Table */}
      <Card title="Student Course Enrollments">
        <Table columns={enrollmentColumns} data={paginatedEnrollments} />
        <Pagination
          currentPage={enrollmentPage}
          totalPages={Math.ceil(studentCourses.length / itemsPerPage)}
          onPageChange={setEnrollmentPage}
          itemsPerPage={itemsPerPage}
          totalItems={studentCourses.length}
        />
      </Card>
    </div>
  );
};

export default Dashboard;