package com.raoulduke.crypto.fivedaysincloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderHandler {

    @Autowired
    private OrderRepository orderRepository;


    public void handleRequest(Order order){
        List<Order> orders;
        if (order.getType().equals("BUY")){
            System.out.println("BUY");
            orders = orderRepository.findAllInRange("SELL",0, order.getPrice());

        }else{
            System.out.println("SELL");
            orders = orderRepository.findAllInRange("BUY",order.getPrice(), Double.MAX_VALUE);
        }
        System.out.println("Found orders for : " + order.getType());
        //orders = orderRepository.findAll();
        for( Order foundOrder : orders){
            System.out.println(foundOrder);
        }

    }
}
