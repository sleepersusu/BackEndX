package com.example.bistro.menu;





import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;




@Repository
public interface MenuRepository extends JpaRepository<Menu, Integer> {

	@Query("FROM Menu  WHERE productName like %:productname%")
	List<Menu> findMenuByNameLike(@Param("productname") String productname);

	Menu findByProductName(String productName);

	//11/19修改
	@Query("SELECT odt.menu.productName AS productName, COALESCE(AVG(odt.commentRating), 0.0) AS avgScore " +
		       "FROM OrderDetails odt GROUP BY odt.menu.productName")
		List<Object[]> countAvgScores();  // 返回 Object[] 是因為查詢會返回兩個值，菜品名稱和平均分數
	
		//11/19修改
	@Query("FROM Menu  WHERE menuStatus = '上架'")
		List<Menu> findMenuByStatusIsSold();  
	
	@Query("""
		    SELECT odt.menu.ID, odt.menu.productName, COALESCE(AVG(odt.commentRating), 0.0) AS avgScore
		    FROM OrderDetails odt
		    JOIN odt.menu 
		    WHERE odt.menu.menuStatus = '上架'
		    GROUP BY odt.menu.ID, odt.menu.productName
		    """)
		List<Object[]> countAvgScoresMenuIsSold();  

	

	

}
