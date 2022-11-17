package com.raoulduke.com.api1_1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
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
        List<Order> updatedOrders = new ArrayList<>();

        for( Order foundOrder : orders){
            System.out.println(foundOrder);

            double tradeQuantity = Math.min(order.getUnfilledQuantity(), foundOrder.getUnfilledQuantity());
            Trade trade = new Trade();
            if (order.getType().equals("BUY")){
                trade.setBuyOrder(order.getId());
                trade.setSellOrder(foundOrder.getId());
            }else{
                trade.setBuyOrder(foundOrder.getId());
                trade.setSellOrder(order.getId());
            }
            trade.setPrice(foundOrder.getPrice());
            trade.setTimestamp(new Date());
            order.getTrades().add(trade);
            foundOrder.getTrades().add(trade);

            order.setFilledQuantity(order.getFilledQuantity()+tradeQuantity);
            foundOrder.setFilledQuantity(foundOrder.getFilledQuantity()+tradeQuantity);
            updatedOrders.add(foundOrder);
            if (order.getFilledQuantity() == order.getQuantity())break;
        }
        updatedOrders.add(order);
        orderRepository.saveAll(updatedOrders);
    }
}
