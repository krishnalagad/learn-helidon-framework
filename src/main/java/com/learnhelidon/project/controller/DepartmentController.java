package com.learnhelidon.project.controller;

import com.learnhelidon.project.entity.Department;
import com.learnhelidon.project.service.DepartmentService;
import io.helidon.webserver.http.HttpRouting;
import io.helidon.webserver.http.ServerRequest;
import io.helidon.webserver.http.ServerResponse;

import java.util.List;

public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public void findAll(ServerRequest request, ServerResponse response) {
        List<Department> allDepartments = this.departmentService.getAllDepartments();
        response.send(allDepartments);
    }

    public void create(ServerRequest request, ServerResponse response) {
        Department department = request.content().as(Department.class);
        Department savedDept = this.departmentService.createDepartment(department);
        response.send(savedDept);
    }

    // -------------------------------------------------Routes Initialization-------------------------------------------

    public static void initRoutes(HttpRouting.Builder router, DepartmentService departmentService) {
        DepartmentController controller = new DepartmentController(departmentService);
        router.get("/", controller::findAll);
        router.post("/", controller::create);
    }
}