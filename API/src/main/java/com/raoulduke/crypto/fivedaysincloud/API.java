package com.raoulduke.crypto.fivedaysincloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class API {

    @Autowired
    private RedisTemplate<String,String > template;

    @Autowired
    private OrderMongoDBRepository orderMongoDBRepository;

    static final String orderQueue = "ORDER_QUEUE";

    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> order(@RequestBody Order order){
        if(!order.getCurrencyPair().equals("BTCUSD")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if(!order.getType().equals("BUY") && !order.getType().equals("SELL")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if(order.getPrice()<0 || order.getQuantity()<0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        order.setOrderStatus("OPEN");
        order.setCreatedDateTime(new Date());

        //TODO: Check if there is already order with that ID

        orderMongoDBRepository.save(order);
        System.out.println("Hi");
        template.opsForList().leftPush(orderQueue,order.toString());

        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
}
