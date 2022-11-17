package com.raoulduke.crypto.fivedaysincloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class OrderHandler {



    private MongoOperations mongoOperations;

    @Autowired
    public OrderHandler(MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }

    @Autowired
    MongoCounter mongoCounter;
    public void handleRequest(Order order, OrderRepository orderRepository){
        List<Order> orders;
        if (order.getType().equals("BUY")){
            System.out.println("BUY");
            orders = orderRepository.findAllInRangeSell(0, order.getPrice());

        }else{
            System.out.println("SELL");
            orders = orderRepository.findAllInRangeBuy(order.getPrice(), Double.MAX_VALUE);
        }
        System.out.println("Found orders for : " + order.getType());
        //orders = orderRepository.findAll();
        List<Order> updatedOrders = new ArrayList<>();

        for( Order foundOrder : orders){
            System.out.println(foundOrder);

            double tradeQuantity = Math.min(order.getQuantity()- order.getFilledQuantity(), foundOrder.getQuantity()- foundOrder.getFilledQuantity());
            Trade trade = new Trade();
            trade.setId(mongoCounter.generateSequence("TRADE_ID"));
            if (order.getType().equals("BUY")){
                trade.setBuyOrderId(order.getId());
                trade.setSellOrderId(foundOrder.getId());
            }else{
                trade.setBuyOrderId(foundOrder.getId());
                trade.setSellOrderId(order.getId());
            }
            trade.setPrice(foundOrder.getPrice());
            trade.setTimestamp(new Date());
            order.getTrades().add(trade);
            foundOrder.getTrades().add(trade);

            order.setFilledQuantity(order.getFilledQuantity()+tradeQuantity);
            trade.setQuantity(tradeQuantity);
            foundOrder.setFilledQuantity(foundOrder.getFilledQuantity()+tradeQuantity);
            updatedOrders.add(foundOrder);
            if (order.getFilledQuantity() == order.getQuantity())break;
        }
        updatedOrders.add(order);
        orderRepository.saveAll(updatedOrders);
    }
}
