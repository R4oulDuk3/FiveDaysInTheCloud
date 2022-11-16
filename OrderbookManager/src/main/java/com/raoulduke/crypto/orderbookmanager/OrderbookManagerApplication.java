package com.raoulduke.crypto.orderbookmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class OrderbookManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderbookManagerApplication.class, args);
    }

}
