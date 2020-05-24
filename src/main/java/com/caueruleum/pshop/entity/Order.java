package com.caueruleum.pshop.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orders")
public class Order 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="o_id")
	private int id;
	
	@Column(name="o_phone_number")
	private String phoneNumber;
	
	@Column(name="o_email")
	private String email;
	
	@Column(name="o_first_name")
	private String firstName;
	
	@Column(name="o_last_name")
	private String lastName;
	
	@Column(name="o_city")
	private String city;
	
	@Column(name="o_post_code")
	private String postCode;
	
	@Column(name="o_address")
	private String address;
	
	@Column(name="o_date")
	private Date date;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="o_status_id")
	private OrderStatus status;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="o_users_id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="o_type_id")
	private OrderType type;

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="orders_products",
			joinColumns = @JoinColumn(name="o_p_order_id"),
			inverseJoinColumns = @JoinColumn(name="o_p_product_id"))
	private List<Product> products;
	
	public Order() {}
	
	public Order(String phoneNumber, String email, String firstName, String lastName, String city, String postCode,
			String address, Date date) {
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.postCode = postCode;
		this.address = address;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public OrderType getType() {
		return type;
	}

	public void setType(OrderType type) {
		this.type = type;
	}
	

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", phoneNumber=" + phoneNumber + ", email=" + email + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", city=" + city + ", postCode=" + postCode + ", address=" + address
				+ ", date=" + date + ", status=" + status + ", user=" + user + ", type=" + type + "]";
	}
}
