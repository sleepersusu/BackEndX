package com.example.bistro.orderDetails;


import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;

	
	@ManyToOne
	@JoinColumn(name = "memberId")
	private Members members;

	@ManyToOne
	@JoinColumn(name = "seatsId")
	private Seats seats;

	
	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;

	private Integer ordersSumPrice;

	private Integer pointGetted;

	private String ordersStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 前端輸入輸出時的格式對應，若須強制轉換格式，el 須使用雙層大括號
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	private String paymentWay;

	private String paymentStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 前端輸入輸出時的格式對應，若須強制轉換格式，el 須使用雙層大括號
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentTime;

	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
	private List<OrderDetails> OrderDetails;

	public Orders() {

	}
	
	@PrePersist // 當物件要轉換成 Persistent 狀態以前，執行這個方法
	public void createdAt() {
		if (createdAt == null||paymentTime== null) {
			createdAt = new Date();
			paymentTime = new Date();
		}
	}
	
	

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public Members getMembers() {
		return members;
	}

	public void setMembers(Members members) {
		this.members = members;
	}

	public Seats getSeats() {
		return seats;
	}

	public void setSeats(Seats seats) {
		this.seats = seats;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Integer getOrdersSumPrice() {
		return ordersSumPrice;
	}

	public void setOrdersSumPrice(Integer ordersSumPrice) {
		this.ordersSumPrice = ordersSumPrice;
	}

	public Integer getPointGetted() {
		return pointGetted;
	}

	public void setPointGetted(Integer pointGetted) {
		this.pointGetted = pointGetted;
	}

	public String getOrdersStatus() {
		return ordersStatus;
	}

	public void setOrdersStatus(String ordersStatus) {
		this.ordersStatus = ordersStatus;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(String paymentWay) {
		this.paymentWay = paymentWay;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public Date getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(Date paymentTime) {
		this.paymentTime = paymentTime;
	}

	public List<OrderDetails> getOrderDetails() {
		return OrderDetails;
	}

	public void setOrderDetails(List<OrderDetails> orderDetails) {
		OrderDetails = orderDetails;
	}

}
