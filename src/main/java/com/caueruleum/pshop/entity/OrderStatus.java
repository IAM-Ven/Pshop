package com.caueruleum.pshop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="orders_status")
public class OrderStatus 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="o_s_id")
	private int id;
	
	@Column(name="o_s_name")
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="status")
	private List<Order> orders;
	
	public OrderStatus() {}
	
	public OrderStatus(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addOrder(Order order) 
	{
		if (this.orders == null) 
		{
			this.orders = new ArrayList<Order>();
		}
		
		this.orders.add(order);
		
		order.setStatus(this);
	}

	@Override
	public String toString() {
		return "OrderStatus [id=" + id + ", name=" + name + ", orders=" + orders + "]";
	}
	
	
	
}
