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
@Table(name="orders_types")
public class OrderType 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="o_t_id")
	private int id;
	
	@Column(name="o_t_name")
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="type")
	private List<Order> orders;
	
	public OrderType() {}
	
	public OrderType(String name) {
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
	
	public void addOrder(Order order) 
	{
		if (this.orders == null) 
		{
			this.orders = new ArrayList<Order>();
		}
		
		this.orders.add(order);
		
		order.setType(this);
	}
	
}
