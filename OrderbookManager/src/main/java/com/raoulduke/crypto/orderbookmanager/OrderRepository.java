package com.raoulduke.crypto.orderbookmanager;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
//$and:  , $lte: '?2'
    @Query(value = "{$and: [{type :  {$eq:  '?0'}}, {price :  {$gte : ?1 , $lte: ?2}}] }", sort = "{ price : 1 , createdDateTime: 1 }")
    List<Order> findAllInRange(String type,double low, double high );
}