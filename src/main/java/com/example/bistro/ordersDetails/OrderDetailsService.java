package com.example.bistro.ordersDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepositoryDao orderDetailsRepositoryDao;

    // 根據訂單 ID 查詢所有訂單詳情
    public List<OrderDetails> findByOrderDetailsId(OrderDetailsId orderDetailsId) {
        return orderDetailsRepositoryDao.findByOrderDetailsId(orderDetailsId);
    }
}
