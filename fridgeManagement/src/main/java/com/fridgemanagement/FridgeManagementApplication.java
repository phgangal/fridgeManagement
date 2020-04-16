package com.fridgemanagement;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableSwagger2
public class FridgeManagementApplication {

    public static void main(String[] args) {
        run(FridgeManagementApplication.class, args);
    }
}
