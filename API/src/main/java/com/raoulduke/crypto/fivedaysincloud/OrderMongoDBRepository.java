package com.raoulduke.crypto.fivedaysincloud;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderMongoDBRepository extends MongoRepository<Order, String> {


}