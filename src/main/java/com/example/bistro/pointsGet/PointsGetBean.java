package com.example.bistro.pointsGet;

import com.example.bistro.members.Members;
import com.example.bistro.orders.Orders;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "PointGet")
public class PointsGetBean {
	
    //PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    
    @ManyToOne
    @JoinColumn(name = "memberId")
    private Members members;
    
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orders;
    
    private Integer pointGetted;

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

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Integer getPointGetted() {
		return pointGetted;
	}

	public void setPointGetted(Integer pointGetted) {
		this.pointGetted = pointGetted;
	}

}
