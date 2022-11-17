package com.raoulduke.crypto.fivedaysincloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class API {
    @Autowired
    private OrderHandler orderHandler;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    MongoCounter mongoCounter;

    @Autowired
    MongoOperations mongoOperations;

    @PostMapping(value = "/order", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> order(@RequestBody Order order){
        if(!order.getCurrencyPair().equals("BTCUSD")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if(!order.getType().equals("BUY") && !order.getType().equals("SELL")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        if(order.getPrice()<=0 || order.getQuantity()<=0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        Optional<Order> opt = orderRepository.findById(order.getId());
        if(opt.isPresent()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        order.setPrice(Math.floor(order.getPrice()  * 100) / 100); //Truncate
        order.setOrderStatus("OPEN");
        order.setCreatedDateTime(new Date());




        //TODO: Check if there is already order with that ID
        //TODO: ROUND PRICE TO 2 DECIMALS

        //orderRepository.save(order);
        orderHandler.handleRequest(order,orderRepository);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }
    @GetMapping(value = "/order/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> order(@PathVariable Integer id){
        Optional<Order> order = orderRepository.findById(id);
        if(order.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(order.get());
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
    @DeleteMapping(value = "/delete/all")
    public ResponseEntity delete(){
        orderRepository.deleteAll();
        mongoCounter.resetSequence("TRADE_ID");
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/orderbook", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity orderbook(){
        List<OrderbookElement> buy = orderRepository.orderbookBuy();
        List<OrderbookElement> sell =orderRepository.orderbookSell();
        Orderbook orderbook = new Orderbook();
        orderbook.setSellOrders(sell);
        orderbook.setBuyOrders(buy);
        return ResponseEntity.ok().body(orderbook);
    }
}
