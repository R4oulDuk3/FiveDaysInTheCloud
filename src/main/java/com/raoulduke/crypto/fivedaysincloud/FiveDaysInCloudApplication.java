package com.raoulduke.crypto.fivedaysincloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FiveDaysInCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiveDaysInCloudApplication.class, args);
    }

    @PostMapping("/order")
    public static String order(@RequestBody Order order){
        return order.toString();
    }

}
