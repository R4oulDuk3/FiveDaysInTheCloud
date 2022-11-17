package com.raoulduke.crypto.fivedaysincloud;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Map;

public interface OrderRepository extends MongoRepository<Order, Integer> {
    @Query(value = "{$and: [{type :  {$eq:  'BUY'}}, {price :  {$gte : ?0 , $lte: ?1}}, {orderStatus: {$ne:  'CLOSED'}}] }", sort = "{ price : -1 , createdDateTime: 1 }")
    List<Order> findAllInRangeBuy(double low, double high);

    @Query(value = "{$and: [{type :  {$eq:  'SELL'}}, {price :  {$gte : ?0 , $lte: ?1}}, {orderStatus: {$ne:  'CLOSED'}}] }", sort = "{ price : 1, createdDateTime: 1 }")
    List<Order> findAllInRangeSell(double low, double high);

    @Aggregation(pipeline = {
            "{$match: {type: {$eq: 'SELL' } } }",
            " {$match: {orderStatus: {$eq: 'OPEN' } } }",
            "{$group: {_id: '$price',quantity: {$sum: { $subtract: ['$quantity', '$filledQuantity']}}}}",
            "{$addFields: {price: '$_id' }}",
            "{$sort: {price: 1}}",

    })
    List<OrderbookElement> orderbookSell();

    @Aggregation(pipeline = {
            "{$match: {type: {$eq: 'BUY' } } }",
            " {$match: {orderStatus: {$eq: 'OPEN' } } }",
            "{$group: {_id: '$price',quantity: {$sum: { $subtract: ['$quantity', '$filledQuantity']}}}}",
            "{$addFields: {price: '$_id' }}",
            "{$sort: {price: -1}}",

    })
    List<OrderbookElement> orderbookBuy();
}