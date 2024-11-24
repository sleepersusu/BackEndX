package com.example.bistro.PointsRecords;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PointsRecordsRepository extends JpaRepository<PointsRecordsBean, Integer>{

	@Query(value = "SELECT PointsRecords.ID, " +
            "Members.ID, " +
            "Members.memberName, " +
            "PointPrizes.ID, " +
            "PointPrizes.pointPrizesName, " +
            "PointPrizes.pointPrizesPoints, " +
            "PointsRecords.recordsDate " +
            "FROM PointsRecords " +
            "JOIN Members ON PointsRecords.memberId = Members.ID " +
            "JOIN PointPrizes ON PointsRecords.pointPrizesId = PointPrizes.ID", 
    nativeQuery = true)
	List<Object[]> findMembersAllPointRecord();

	
}
