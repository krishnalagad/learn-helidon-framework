
package com.learnhelidon.project;


import com.learnhelidon.project.config.MySqlConnection;
import com.learnhelidon.project.controller.DepartmentController;
import com.learnhelidon.project.repository.DepartmentRepository;
import com.learnhelidon.project.repository.DepartmentRepositoryImpl;
import com.learnhelidon.project.service.DepartmentService;
import com.learnhelidon.project.service.impl.DepartmentServiceImpl;
import io.helidon.logging.common.LogConfig;
import io.helidon.config.Config;
import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;

import java.sql.Connection;


/**
 * The application main class.
 */
public class Main {

    private Main() {
    }

    public static void main(String[] args) {

        // load logging configuration
        LogConfig.configureRuntime();

        // initialize global config from default configuration
        Config config = Config.create();
        Config.global(config);

        try{
            Connection connection = MySqlConnection.getConnection();
            DepartmentRepository departmentRepository = new DepartmentRepositoryImpl(connection);
            DepartmentService departmentService = new DepartmentServiceImpl(departmentRepository);

            WebServer server = WebServer.builder()
                    .config(config.get("server"))
                    .routing("/dept", routing -> DepartmentController.initRoutes(routing, departmentService))
                    .build()
                    .start();

            System.out.println("WEB server is up! http://localhost:" + server.port() + "/dept");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

//        // load logging configuration
//        LogConfig.configureRuntime();
//
//        // initialize global config from default configuration
//        Config config = Config.create();
//        Config.global(config);
//
//
//        WebServer server = WebServer.builder()
//                .config(config.get("server"))
//                .routing(Main::routing)
//                .build()
//                .start();
//
//        System.out.println("WEB server is up! http://localhost:" + server.port() + "/simple-greet");

    }

//    static void routing(HttpRouting.Builder routing) {
//        routing
//               .register("/greet", new GreetService())
//               .get("/simple-greet", (req, res) -> res.send("Hello World!"));
//    }
}