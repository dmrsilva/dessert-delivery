package com.myproject.dessertdelivery.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.myproject.dessertdelivery.entities.PK.OrderProductPK;

@Entity
@Table(name = "tb_order_product")
public class OrderProduct {
	
	@EmbeddedId
	private OrderProductPK id = new OrderProductPK();	
	
	private Integer quantity;
	
	public OrderProduct() {
	}
	
	public OrderProduct(Order order, Product product, Integer quantity) {
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
