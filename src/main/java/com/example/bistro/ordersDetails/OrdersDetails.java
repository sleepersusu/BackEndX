package com.example.bistro.ordersDetails;

import com.example.bistro.menu.Menu;
import com.example.bistro.orders.Orders;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;

@Setter
@Getter
@Entity
@Table(name = "OrderDetails")
public class OrderDetails {

    //PK
            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private Integer ID;

    //欄位名稱
            private String  odName;
            private Integer odQuantity;
            private Integer odPrice;
            private Integer odSumPrice;

    //FK
        // 多對一：多筆訂單詳情來自同一筆訂單
            @ManyToOne
            @JoinColumn(name = "ordersId", nullable = false)
            private Orders orders;  // 訂單明細與訂單關聯
        // 多對一： 一個商品項目會出現在多筆訂單
            @ManyToOne
            @JoinColumn(name = "menuId", nullable = false)
            private Menu menu;  // 訂單明細與菜單商品關聯


        public OrderDetails() {};

}
