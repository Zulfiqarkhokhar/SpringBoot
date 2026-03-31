import React from 'react';

const Filters = ({ filters, onFilterChange, onReset }) => {
  const handleChange = (e) => {
    const { name, value } = e.target;
    onFilterChange({ [name]: value });
  };

  return (
    <div className="bg-white p-4 rounded-lg shadow mb-6">
      <div className="grid grid-cols-1 md:grid-cols-4 gap-4">
        {filters.map((filter) => (
          <div key={filter.name}>
            <label className="block text-sm font-medium text-gray-700 mb-1">
              {filter.label}
            </label>
            {filter.type === 'select' ? (
              <select
                name={filter.name}
                value={filter.value}
                onChange={handleChange}
                className="input-field"
              >
                <option value="">All</option>
                {filter.options?.map((option) => (
                  <option key={option.value} value={option.value}>
                    {option.label}
                  </option>
                ))}
              </select>
            ) : filter.type === 'checkbox' ? (
              <input
                type="checkbox"
                name={filter.name}
                checked={filter.value}
                onChange={(e) => onFilterChange({ [filter.name]: e.target.checked })}
                className="mt-2"
              />
            ) : (
              <input
                type={filter.type || 'text'}
                name={filter.name}
                value={filter.value}
                onChange={handleChange}
                className="input-field"
                placeholder={filter.placeholder}
              />
            )}
          </div>
        ))}
        <div className="flex items-end">
          <button
            onClick={onReset}
            className="bg-gray-500 text-white px-4 py-2 rounded-lg hover:bg-gray-600"
          >
            Reset Filters
          </button>
        </div>
      </div>
    </div>
  );
};

export default Filters;