package com.creepy.authcreepy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AuthCreepyApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthCreepyApplication.class, args);
    }

}
