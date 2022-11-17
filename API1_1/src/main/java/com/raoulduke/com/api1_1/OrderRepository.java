package com.raoulduke.com.api1_1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query(value = "select * from Order o where o.type = ?1 and o.price >= ?2 and o.price<= ?3 order by o.price,o.createdDateTime", nativeQuery = true)
    List<Order> findAllInRange(String type, double low, double high );
}