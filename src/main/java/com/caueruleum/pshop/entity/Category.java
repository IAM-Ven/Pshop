package com.caueruleum.pshop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name="categories")
public class Category 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="c_id")
	private int id;
	
	@Column(name="c_category")
	@Field
	@Analyzer(definition="customanalyzer")
	private String name;
	
	@Column(name="c_description")
	@Field
	@Analyzer(definition="customanalyzer")
	private String description;
	
	@ManyToMany(
			fetch=FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH}
			)
	@JoinTable(
			name="products_categories",
			joinColumns = @JoinColumn(name="p_c_category_id"),
			inverseJoinColumns = @JoinColumn(name = "p_c_product_id")
			)
	@ContainedIn
	private List<Product> products;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_image_id")
	private Image image;
	
	public int getId() {
		return id;
	}
	
	public Category() {}
	
	public Category(String name) {
		this.name = name;
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
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	
	public void addProduct(Product product) 
	{
		if(this.products == null) 
		{
			this.products = new ArrayList<Product>();
		}
		
		this.products.add(product);
		product.addCategory(this);
		
 	}
	
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", products=" + products
				+ "]";
	}
	
}
