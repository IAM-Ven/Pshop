package com.caueruleum.pshop.admin.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.caueruleum.pshop.admin.dto.AdminProductDTO;
import com.caueruleum.pshop.dao.CategoryDAO;
import com.caueruleum.pshop.dao.ImageDAO;
import com.caueruleum.pshop.dao.OrderDAO;
import com.caueruleum.pshop.dao.ProductDAO;
import com.caueruleum.pshop.entity.Category;
import com.caueruleum.pshop.entity.Image;
import com.caueruleum.pshop.entity.Order;
import com.caueruleum.pshop.entity.Product;

@Service
public class AdminProductServiceImpl implements AdminProductService
{
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private ImageDAO imageDAO;
	
	/**
	 * Cycles through the categoriesIDS and finds the category and 
	 * saves it into an ArrayList then returns the ArrayList.
	 * 
	 * If the ArrayList is empty on return it means that either we don`t have
	 * input or we did not find any categories.
	 * 
	 * @param categoriesIDS HashSet<Integer> hash set of the categories ids
	 * @return ArrayList<Category> the found categories
	 */
	private ArrayList<Category> fillCategories(HashSet<Integer> categoriesIDS) 
	{
		ArrayList<Category> categories = 
				new ArrayList<Category>();
		
		if(categoriesIDS != null && !categoriesIDS.isEmpty()) 
		{
			for(Integer c: categoriesIDS) 
			{
				Category category = categoryDAO.findCategoryById(c);
				
				
				if(category != null) 
				{
					categories.add(category);
				}
			}
		}
		
		
		return categories;
	}
	
	/**
	 * @see private ArrayList<Category> fillCategories(HashSet<Integer> categoriesIDS);
	 * 
	 * @param ordersIDS orders ids
	 * @return ArrayList<Order> the found orders
	 */
	private ArrayList<Order> fillOrders(HashSet<Integer> ordersIDS)
	{
		ArrayList<Order> orders = 
				new ArrayList<Order>();
		
		if(ordersIDS != null && !ordersIDS.isEmpty()) 
		{
			for(Integer o: ordersIDS)
			{
				Order order = this.orderDAO.findById(o);
				
				if(order != null) 
					orders.add(order);
			}
		}
		
		return orders;
		
	}
	
	/**
	 * private ArrayList<Category> fillCategories(HashSet<Integer> categoriesIDS)
	 * 
	 * @param imagesIDS images ids
	 * @return ArrayList<Image> the found images
	 */
	private ArrayList<Image> fillImages(HashMap<Integer, String> imagesIDS)
	{
		
		ArrayList<Image> images = new ArrayList<Image>();
		
		if(imagesIDS != null && !imagesIDS.isEmpty()) 
		{
			for(Integer i: imagesIDS.keySet()) 
			{
				Image image = imageDAO.findImageById(i);
				
				if(image != null)
					images.add(image);
				
			}
		}
		
		return images;
	}
	
	@Override
	@Transactional
	public Product handleMerge(AdminProductDTO dto) 
	{
		Product product;
		
		if(dto.getId() == null) 
		{
			product = new Product();
		}
		else 
		{
			product = this.productDAO.getById(dto.getId());
			
			if(product == null) 
			{
				return product;
			}
			
		}
		
		product.setTitle(dto.getTitle());
		product.setDescription(dto.getDescription());
		product.setPrice(dto.getPrice());
		product.setDate(Date.valueOf(dto.getDate()));
	
		product.setCategories(this.fillCategories(dto.getCategories()));
		product.setOrders(this.fillOrders(dto.getOrders()));
		product.setImages(this.fillImages(dto.getImages()));
		
		try 
		{
			productDAO.save(product);
		}
		catch(Exception ex) 
		{
			throw ex;
		}
		
		return product;
	}
}
