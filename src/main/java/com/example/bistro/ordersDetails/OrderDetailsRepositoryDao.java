package com.example.bistro.ordersDetails;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailsRepositoryDao extends JpaRepository<OrderDetails, OrderDetailsId> {
    // 根據訂單 ID 查詢所有訂單詳情
        List<OrderDetails> findByOrderDetailsId(OrderDetailsId orderDetailsId );


}
