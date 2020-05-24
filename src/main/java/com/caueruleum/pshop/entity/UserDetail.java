package com.caueruleum.pshop.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="users_details",
		uniqueConstraints =  @UniqueConstraint(columnNames = {"u_d_email", "u_d_phone_number"}))
public class UserDetail 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="u_d_id")
	private int id;
	
	@Column(name="u_d_address",
			nullable= false,
			length=50)
	private String address;
	
	@Column(name="u_d_email",
			nullable= false,
			length=30,
			unique = true)
	private String email;
	
	@Column(name="u_d_phone_number",
			nullable= false,
			length=15,
			unique = true)
	private String phoneNumber;
	
	@Column(name="u_d_first_name",
			nullable=false,
			length=15)
	private String firstName;
	
	@Column(name="u_d_last_name",
			nullable=false,
			length=15)
	private String lastName;
	

	@OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="u_d_username", nullable=false)
	private User user;
	
	public UserDetail() {}
	
	public UserDetail(String address, String email, String phoneNumber,
			String firstName, String lastName) 
	{
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
