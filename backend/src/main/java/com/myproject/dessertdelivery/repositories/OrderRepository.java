package com.myproject.dessertdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.dessertdelivery.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
