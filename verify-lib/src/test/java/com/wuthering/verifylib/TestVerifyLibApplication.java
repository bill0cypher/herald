package com.wuthering.verifylib;

import org.springframework.boot.SpringApplication;

public class TestVerifyLibApplication {

    public static void main(String[] args) {
        SpringApplication.from(VerifyLibApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
