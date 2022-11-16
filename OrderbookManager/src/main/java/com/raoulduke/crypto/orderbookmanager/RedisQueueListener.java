package com.raoulduke.crypto.orderbookmanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPooled;

import java.util.List;
import java.util.Optional;

@Component
public class RedisQueueListener {

    public static final String ORDER_QUEUE = "ORDER_QUEUE";

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderHandler orderHandler;

    @Scheduled(fixedDelay = 1)
    public void redisQueueListener(){

        JedisPooled jedis = new JedisPooled("localhost", 6379);
        System.out.println("Listening");
        List<String> res = jedis.blpop(0,ORDER_QUEUE);

        for (String id : res){
            Optional<Order> optional = orderRepository.findById(id);
            if(optional.isPresent()){
                Order order = optional.get();
                System.out.println(order);
                orderHandler.handleRequest(order);

            }
        }

    }
}
