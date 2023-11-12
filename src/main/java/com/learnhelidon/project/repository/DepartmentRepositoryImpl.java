package com.learnhelidon.project.repository;

import com.learnhelidon.project.config.AppConstants;
import com.learnhelidon.project.entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements DepartmentRepository{

    private Connection connection;

    public DepartmentRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Department> findAll() {
        List<Department> departments = new ArrayList<>();
        try{
            Statement statement = this.connection.createStatement();
            String query = "SELECT * FROM " + AppConstants.TABLE_NAME;
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Department department = new Department(resultSet.getLong("id"),
                        resultSet.getString("name"));
                departments.add(department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return departments;
    }

    @Override
    public Department findById(Long id) {
        return null;
    }

    @Override
    public Department save(Department department) {

        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO departments (name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, department.getId());
            statement.setString(2, department.getDept_name());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating department failed, no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                department.setId(generatedKeys.getLong(1));
            }
            return department;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}