package com.example.bistro.pointsGet;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PointsGetRepository extends JpaRepository<PointsGetBean, Integer> {

	@Query(value = "select PointGet.ID, Orders.ID, Orders.memberId, Members.memberName, Orders.ordersSumPrice, "
	        + " (Orders.ordersSumPrice / 100) as pointGetted, Orders.createdAt "
	        + "from Orders "
	        + "join PointGet on Orders.ID = PointGet.orderId "
	        + "join Members on Orders.memberId = Members.ID", nativeQuery = true)
	List<Object[]> findMembersAllPointGet();
	
	
}
