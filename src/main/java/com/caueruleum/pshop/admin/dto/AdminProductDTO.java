package com.caueruleum.pshop.admin.dto;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;

public class AdminProductDTO
{
	private Integer id;
	private String title;
	private String description;
	private BigDecimal price;
	private String date;
	private HashSet<Integer> categories;
	private HashSet<Integer> orders;
	private HashMap<Integer, String> images;
	
	public AdminProductDTO() 
	{
		this.categories = new HashSet<Integer>();
		this.orders = new HashSet<Integer>();
		this.images = new HashMap<Integer, String>();
	}
	
	public AdminProductDTO(Integer id, String title, String description, BigDecimal price, String date,
			HashSet<Integer> categories, HashSet<Integer> orders, HashMap<Integer, String> images)
	{
		this.id = id;
		this.title = title;
		this.description = description;
		this.price = price;
		this.date = date;
		this.categories = categories;
		this.orders = orders;
		this.images = images;
	}
	
	public Integer getId()
	{
		return id;
	}
	
	public void setId(Integer id)
	{
		this.id = id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public BigDecimal getPrice()
	{
		return price;
	}
	
	public void setPrice(BigDecimal price)
	{
		this.price = price;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public void setDate(String date)
	{
		this.date = date;
	}
	
	public HashSet<Integer> getCategories()
	{
		return categories;
	}
	
	public void setCategories(HashSet<Integer> categories)
	{
		this.categories = categories;
	}
	
	public HashSet<Integer> getOrders()
	{
		return orders;
	}
	
	public void setOrders(HashSet<Integer> orders)
	{
		this.orders = orders;
	}
	
	public HashMap<Integer, String> getImages()
	{
		return images;
	}
	
	public void setImages(HashMap<Integer, String> images)
	{
		this.images = images;
	}
	
	public void addOrder(Integer orderId) 
	{
		if(this.orders == null) this.orders = new HashSet<Integer>();
		
		this.orders.add(orderId);
	}
	
	public void addCategory(Integer categoryId, String name) 
	{
		if(this.categories == null) this.categories = new HashSet<Integer>();
		this.categories.add(categoryId);
	}
	
	public void addImage(Integer imageId, String path) 
	{
		if(this.images == null) this.images = new HashMap<Integer, String>();
		
		this.images.put(imageId, path);
	}

	
	@Override
	public String toString()
	{
		return "AdminProductDTO [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", date=" + date + ", categories=" + categories + ", orders=" + orders + ", images=" + images + "]";
	}
	
	
	
}
