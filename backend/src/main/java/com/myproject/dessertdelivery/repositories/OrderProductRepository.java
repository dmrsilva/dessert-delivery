package com.myproject.dessertdelivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.dessertdelivery.entities.OrderProduct;
import com.myproject.dessertdelivery.entities.PK.OrderProductPK;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductPK> {

}
