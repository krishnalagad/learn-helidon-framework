package com.learnhelidon.project.service.impl;

import com.learnhelidon.project.entity.Department;
import com.learnhelidon.project.repository.DepartmentRepository;
import com.learnhelidon.project.service.DepartmentService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<Department> getAllDepartments() {
        return this.departmentRepository.findAll();
    }

    @Override
    public Department getDepartmentById(Long id) {
        return null;
    }

    @Override
    public Department createDepartment(Department department) {
        return null;
    }

    @Override
    public Department updateDepartment(Long id, Department department) {
        return null;
    }

    @Override
    public void deleteDepartment(Long id) {

    }
}