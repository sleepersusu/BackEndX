package com.example.bistro.PointsTotal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PointsTotalRepository extends JpaRepository<PointsTotalBean, Integer> {

	@Query(value = "SELECT PointsTotal.ID, " +
            "PointsTotal.PointsTotal, " +
            "PointsTotal.pointGetId, " +
            "PointsTotal.pointPrizesId, " +
            "PointsTotal.memberId, " +
            "Members.memberName " +
            "FROM PointsTotal " +
            "JOIN Members ON PointsTotal.memberId = Members.id", 
    nativeQuery = true)
	List<Object[]> findPointsTotal();
	
}
