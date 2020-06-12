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
import javax.persistence.Table;

@Entity
@Table(name="images")
public class Image 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="i_id")
	private int id;
	
	@Column(name="i_upload_date")
	private Date uploadDate;
	
	@Column(name="i_path")
	private String path;
	
	// 0 -> Thumbnail (first image)
	// 1: -> Gallery (for future implementations)
	@Column(name="i_turn")
	private int turn;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="images_products",
			joinColumns = @JoinColumn(name="i_p_image_id"),
			inverseJoinColumns = @JoinColumn(name="i_p_product_id")
			)
	private List<Product> products;

	public Image(Date uploadDate, String path, int turn) 
	{
		this.uploadDate = uploadDate;
		this.path = path;
		this.turn = turn;
	}

	public Image()
	{
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProduct(List<Product> product) {
		this.products = product;
	}
	
	@Override
	public String toString() {
		return "Image [id=" + id + ", uploadDate=" + uploadDate + ", path=" + path + ", turn=" + turn + ", product="
				+ products + "]";
	}
	

}
