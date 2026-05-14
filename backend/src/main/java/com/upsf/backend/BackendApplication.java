package com.upsf.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class BackendApplication {

    // http://localhost:8080/

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        System.out.println("hello world");

    }

}
