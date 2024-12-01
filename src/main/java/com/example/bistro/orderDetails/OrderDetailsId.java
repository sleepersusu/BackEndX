package com.example.bistro.orderDetails;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;


@Embeddable
public class OrderDetailsId implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer menuId;
	
	private Integer ordersId;
	
	public OrderDetailsId() {
		
	}

	public OrderDetailsId(Integer menuId, Integer ordersId) {
		super();
		this.menuId = menuId;
		this.ordersId = ordersId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public Integer getOrdersId() {
		return ordersId;
	}

	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(ordersId, menuId);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailsId other = (OrderDetailsId) obj;
		return Objects.equals(ordersId, other.ordersId) && Objects.equals(menuId, other.menuId);
	}

	
}
