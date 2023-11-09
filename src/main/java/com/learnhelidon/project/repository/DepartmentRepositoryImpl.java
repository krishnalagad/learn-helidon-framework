package com.learnhelidon.project.repository;

import com.learnhelidon.project.config.AppConstants;
import com.learnhelidon.project.entity.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}