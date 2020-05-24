package com.caueruleum.pshop.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
import javax.persistence.Table;

import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.snowball.SnowballPorterFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

@Entity
@Indexed
@AnalyzerDef(name = "customanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
  @TokenFilterDef(factory = LowerCaseFilterFactory.class),
  @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
    @Parameter(name = "language", value = "English")
  })
})
@Table(name="products")
public class Product 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="p_id")
	@DocumentId
	private int id;
	
	@Column(name="p_title")
	@Field
	@Analyzer(definition="customanalyzer")
	private String title;
	
	@Column(name="p_description")
	@Field
	@Analyzer(definition="customanalyzer")
	private String description;
	
	@Column(name="p_price")
	private BigDecimal price;
	
	@Column(name="p_date")
	private Date date;
	
	@ManyToMany(
			fetch=FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="products_categories",
			joinColumns = @JoinColumn(name="p_c_product_id"),
			inverseJoinColumns = @JoinColumn(name = "p_c_category_id"))
	@IndexedEmbedded
	private List<Category> categories;
	
	@ManyToMany(
			fetch=FetchType.LAZY,
			cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name="orders_products",
			joinColumns = @JoinColumn(name="o_p_product_id"),
			inverseJoinColumns= @JoinColumn(name="o_p_order_id"))
	private List<Order> orders;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="images_products",
			joinColumns = @JoinColumn(name="i_p_product_id"),
			inverseJoinColumns = @JoinColumn(name="i_p_image_id")
			)
	private List<Image> images;
	
	
	public Product() {}
	
	public Product(String title, String description, BigDecimal price, Date date) {
		this.title = title;
		this.description = description;
		this.price = price;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public void addCategory(Category category) 
	{
		if (this.categories == null) 
		{
			this.categories = new ArrayList<Category>();
		}
		
		this.categories.add(category);
		category.addProduct(this);
	}
	
	public void addOrder(Order order) 
	{
		if (this.categories == null) 
		{
			this.categories = new ArrayList<Category>();
		}
		
		this.orders.add(order);
		
	}
	
	public void addImage(Image image) 
	{
		if (this.images == null) 
		{
			this.images = new ArrayList<Image>();
		}
		
		this.images.add(image);
		
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", description=" + description + ", price=" + price
				+ ", date=" + date + ", categories=" + categories + ", orders=" + orders + "]";
	}
	
}
