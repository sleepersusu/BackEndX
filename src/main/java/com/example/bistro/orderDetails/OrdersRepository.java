package com.example.bistro.orderDetails;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	
	
	

	@Query("select SUM(odt.odQuantity*odt.odPrice-odt.odDiscount) from OrderDetails odt WHERE odt.orders.ID= :ordersId")
	Integer countOrdersSumPrice(@Param("ordersId") Integer oredersId); 
	
}
