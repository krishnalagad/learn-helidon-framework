package com.learnhelidon.project.repository;

import com.learnhelidon.project.entity.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> findAll();
    Department findById(Long id);
    Department save(Department department);
    void delete(Long id);
}