package com.wuthering.mockreceiver;

import org.springframework.boot.SpringApplication;

public class TestMockReceiverApplication {

    public static void main(String[] args) {
        SpringApplication.from(MockReceiverApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
