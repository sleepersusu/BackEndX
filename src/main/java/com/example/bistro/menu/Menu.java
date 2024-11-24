package com.example.bistro.menu;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.bistro.comment.Comment;
import com.example.bistro.orderDetails.OrderDetails;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Menu")
public class Menu {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer ID;
	private String productCategory;
	private String productName;

	@Lob
	private byte[] productImg;
	private Integer productPrice;
	private String productDescribe;
	private String productImgUrl;

	private Integer productCount;

	private Integer minproductCount;

	private Double avgScore;

	private String menuStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd") // 前端輸入輸出時的格式對應，若須強制轉換格式，el 須使用雙層大括號
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdAt")
	private Date createdAt;

	@JsonIgnore
	@OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
	private List<OrderDetails> menuId; // 用於映射 OrderDetails 實體中的 menu

	@JsonIgnore
	@OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
	private List<Comment> comments = new ArrayList<Comment>(); // 用於映射 Comment 實體中的 menu
	
	
	

	
	

	@PrePersist // 當物件要轉換成 Persistent 狀態以前，執行這個方法
	public void createdAt() {
		if (createdAt == null) {
			createdAt = new Date();
		}
	}

	public Menu() {

	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public byte[] getProductImg() {
		return productImg;
	}

	public void setProductImg(byte[] productImg) {
		this.productImg = productImg;
	}

	public Integer getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Integer productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductDescribe() {
		return productDescribe;
	}

	public void setProductDescribe(String productDescribe) {
		this.productDescribe = productDescribe;
	}

	public String getProductImgUrl() {
		return productImgUrl;
	}

	public void setProductImgUrl(String productImgUrl) {
		this.productImgUrl = productImgUrl;
	}

	public Integer getProductCount() {
		return productCount;
	}

	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}

	public Integer getMinproductCount() {
		return minproductCount;
	}

	public void setMinproductCount(Integer minproductCount) {
		this.minproductCount = minproductCount;
	}

	public double getAvgScore() {
		if (avgScore == null) {
			return 0.0; // 默認值，避免返回 null
		}
		return avgScore;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}

	public Date getCreatedDate() {
		return createdAt;
	}

	public void setCreatedDate(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<OrderDetails> getMenuId() {
		return menuId;
	}

	public void setMenuId(List<OrderDetails> menuId) {
		this.menuId = menuId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public void setAvgScore(Double avgScore) {
		this.avgScore = avgScore;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
