package com.example.bistro.orders;



import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.bistro.employee.Employee;
import com.example.bistro.members.Members;
import com.example.bistro.orderDetails.OrderDetails;
import com.example.bistro.seats.Seats;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "Orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;

	private Integer ordersSumPrice;

	private Integer pointGetted;

	private String ordersStatus;
	
	private String ordersName;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 前端輸入輸出時的格式對應，若須強制轉換格式，el 須使用雙層大括號
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	private String paymentWay;

	private String paymentStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") // 前端輸入輸出時的格式對應，若須強制轉換格式，el 須使用雙層大括號
	@Temporal(TemporalType.TIMESTAMP)
	private Date paymentTime;
	
	
	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	@ManyToOne
	@JoinColumn(name = "memberId")
	private Members member;
	
	@ManyToOne
	@JoinColumn(name = "Id")
	private Seats seats;
	
	
	@OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
	private List<OrderDetails> orderDetails;

	
	public Orders() {

	}

	public String getOrdersName() {
		return ordersName;
	}

	public void setOrdersName(String ordersName) {
		this.ordersName = ordersName;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
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

}
