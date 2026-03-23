// src/pages/AddCourse.jsx
import React, { useState, useEffect } from 'react';
import Card from '../components/common/Card';
import FormInput from '../components/common/FormInput';
import Button from '../components/common/Button';
import Table from '../components/common/Table';
import Pagination from '../components/common/Pagination';
import { courseService } from '../services/courseService';
import toast from 'react-hot-toast';
import { Edit, Trash2 } from 'lucide-react';

const AddCourse = () => {
  const [loading, setLoading] = useState(false);
  const [courses, setCourses] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const itemsPerPage = 5;
  const [formData, setFormData] = useState({
    name: '',
    description: '',
    price: ''
  });
  const [errors, setErrors] = useState({});
  const [editingCourse, setEditingCourse] = useState(null);

  useEffect(() => {
    fetchCourses();
  }, []);

  const fetchCourses = async () => {
    try {
      const data = await courseService.getCourses();
      setCourses(data);
    } catch (error) {
      toast.error('Failed to fetch courses');
    }
  };

  const validateForm = () => {
    const newErrors = {};
    if (!formData.name.trim()) newErrors.name = 'Course name is required';
    if (!formData.description.trim()) newErrors.description = 'Description is required';
    if (!formData.price) {
      newErrors.price = 'Price is required';
    } else if (isNaN(formData.price) || parseFloat(formData.price) <= 0) {
      newErrors.price = 'Price must be a positive number';
    }
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
      if (editingCourse) {
        await courseService.updateCourse(editingCourse.id, {
          ...formData,
          price: parseFloat(formData.price)
        });
        toast.success('Course updated successfully!');
        setEditingCourse(null);
      } else {
        await courseService.addCourse({
          ...formData,
          price: parseFloat(formData.price)
        });
        toast.success('Course added successfully!');
      }
      setFormData({ name: '', description: '', price: '' });
      fetchCourses();
    } catch (error) {
      toast.error(editingCourse ? 'Failed to update course' : 'Failed to add course');
    } finally {
      setLoading(false);
    }
  };

  const handleEdit = (course) => {
    setEditingCourse(course);
    setFormData({
      name: course.name,
      description: course.description,
      price: course.price.toString()
    });
    window.scrollTo({ top: 0, behavior: 'smooth' });
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this course?')) {
      try {
        await courseService.deleteCourse(id);
        toast.success('Course deleted successfully');
        fetchCourses();
      } catch (error) {
        toast.error('Failed to delete course');
      }
    }
  };

  const handleCancelEdit = () => {
    setEditingCourse(null);
    setFormData({ name: '', description: '', price: '' });
    setErrors({});
  };

  const columns = [
    { header: 'Course Name', accessor: 'name' },
    { header: 'Description', accessor: 'description' },
    { header: 'Price', accessor: 'price', render: (row) => `$${row.price}` },
    { 
      header: 'Actions', 
      render: (row) => (
        <div className="flex space-x-2">
          <button
            onClick={() => handleEdit(row)}
            className="text-blue-600 hover:text-blue-800 transition-colors"
            title="Edit"
          >
            <Edit className="w-4 h-4" />
          </button>
          <button
            onClick={() => handleDelete(row.id)}
            className="text-red-600 hover:text-red-800 transition-colors"
            title="Delete"
          >
            <Trash2 className="w-4 h-4" />
          </button>
        </div>
      )
    }
  ];

  const startIndex = (currentPage - 1) * itemsPerPage;
  const paginatedCourses = courses.slice(startIndex, startIndex + itemsPerPage);

  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold text-gray-800">
        {editingCourse ? 'Edit Course' : 'Add New Course'}
      </h1>
      
      <Card>
        <form onSubmit={handleSubmit} className="space-y-4">
          <FormInput
            label="Course Name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            error={errors.name}
            required
            placeholder="e.g., React Development"
          />
          
          <div className="mb-4">
            <label className="block text-sm font-medium text-gray-700 mb-2">
              Description <span className="text-red-500">*</span>
            </label>
            <textarea
              name="description"
              value={formData.description}
              onChange={handleChange}
              rows="4"
              className={`w-full px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-blue-500 focus:border-transparent ${
                errors.description ? 'border-red-500' : ''
              }`}
              placeholder="Enter course description"
            />
            {errors.description && (
              <p className="mt-1 text-sm text-red-500">{errors.description}</p>
            )}
          </div>
          
          <FormInput
            label="Price"
            type="number"
            name="price"
            value={formData.price}
            onChange={handleChange}
            error={errors.price}
            required
            placeholder="99.99"
          />
          
          <div className="flex justify-end space-x-3 pt-4">
            {editingCourse && (
              <Button
                type="button"
                variant="secondary"
                onClick={handleCancelEdit}
              >
                Cancel
              </Button>
            )}
            <Button
              type="submit"
              variant="primary"
              loading={loading}
            >
              {editingCourse ? 'Update Course' : 'Add Course'}
            </Button>
          </div>
        </form>
      </Card>

      {/* Course List Table */}
      <Card title="Course List">
        <Table columns={columns} data={paginatedCourses} />
        <Pagination
          currentPage={currentPage}
          totalPages={Math.ceil(courses.length / itemsPerPage)}
          onPageChange={setCurrentPage}
          itemsPerPage={itemsPerPage}
          totalItems={courses.length}
        />
      </Card>
    </div>
  );
};

export default AddCourse;