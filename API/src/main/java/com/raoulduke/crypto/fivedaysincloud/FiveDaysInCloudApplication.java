package com.raoulduke.crypto.fivedaysincloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

@SpringBootApplication()
public class FiveDaysInCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiveDaysInCloudApplication.class, args);
    }

}
