package com.example.bistro.orderDetails;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<OrderDetails, OrderDetailsId> {

	
	//計算評論人數(商品名)
	@Query("SELECT COALESCE(COUNT(odt.commentRating), 0)  From OrderDetails odt  WHERE odt.menu.productName = :odProduct")
	Integer countCommentTimesByOdProduct(@Param("odProduct") String odProduct);
	
	
	//計算評論人數(menuId)
		@Query("SELECT COALESCE(COUNT(odt.commentRating), 0)  From OrderDetails odt  WHERE odt.menu.ID = : menuId")
		Integer countCommentTimesByMenuId(@Param("menuId") Integer menuId);
		

	
}
