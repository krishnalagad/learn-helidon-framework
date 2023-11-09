package com.learnhelidon.project.controller;

import com.learnhelidon.project.entity.Department;
import com.learnhelidon.project.service.DepartmentService;
import com.learnhelidon.project.service.impl.DepartmentServiceImpl;
import io.helidon.webserver.http.*;

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

    // -------------------------------------------------Routes Initialization-------------------------------------------

    public static void initRoutes(HttpRouting.Builder router, DepartmentService departmentService) {
        DepartmentController controller = new DepartmentController(departmentService);
        router.register("/greet", new HttpService() {
            @Override
            public void routing(HttpRules httpRules) {
                httpRules.get("/", controller::findAll);
            }
        });
    }
}