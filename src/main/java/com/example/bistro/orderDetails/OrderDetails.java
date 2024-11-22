package com.example.bistro.orderDetails;

import java.sql.Timestamp;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.bistro.menu.Menu;
import com.example.bistro.orders.Orders;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "OrderDetails")
public class OrderDetails {

    @EmbeddedId
    private OrderDetailsId orderDetailsPFKId; // 嵌入的複合主鍵

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ordersId")
    @JoinColumn(name = "ordersId") // 這裡指定資料庫中的欄位名稱 (11/19新增) 
    private Orders orders;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("menuId")
    @JoinColumn(name = "menuId") // 這裡指定資料庫中的欄位名稱 (11/19新增)
    private Menu menu;


    private Integer odQuantity ;
    private Integer odPrice;
    private Integer odDiscount;

    private Integer commentRating;
    private String commentMessage ;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp commentDate ;

    public OrderDetails() {}

	public OrderDetailsId getOrderDetailsPFKId() {
		return orderDetailsPFKId;
	}

	public void setOrderDetailsPFKId(OrderDetailsId orderDetailsPFKId) {
		this.orderDetailsPFKId = orderDetailsPFKId;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public Integer getOdQuantity() {
		return odQuantity;
	}

	public void setOdQuantity(Integer odQuantity) {
		this.odQuantity = odQuantity;
	}

	public Integer getOdPrice() {
		return odPrice;
	}

	public void setOdPrice(Integer odPrice) {
		this.odPrice = odPrice;
	}

	public Integer getOdDiscount() {
		return odDiscount;
	}

	public void setOdDiscount(Integer odDiscount) {
		this.odDiscount = odDiscount;
	}

	public Integer getCommentRating() {
		return commentRating;
	}

	public void setCommentRating(Integer commentRating) {
		this.commentRating = commentRating;
	}

	public String getCommentMessage() {
		return commentMessage;
	}

	public void setCommentMessage(String commentMessage) {
		this.commentMessage = commentMessage;
	}

	public Timestamp getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Timestamp commentDate) {
		this.commentDate = commentDate;
	};

    
    
}

	




	

