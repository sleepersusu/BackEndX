package com.example.bistro.payment;

import com.example.bistro.orders.Orders;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "Payment")
public class Payment {

  //PK
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private Integer ID;


  //欄位
        private Integer paymentPrice;
        private String paymentWay;
        private String paymentStatus;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @Temporal(TemporalType.TIMESTAMP)
        private Date createdAt;

        //後面就不用set時間
        @PrePersist
        public void onCreate() {
            if(createdAt == null) {
                createdAt = new Date();
            }
        }


   //FK
        // 每筆付款對應一筆訂單ordersId Orders(ID)
            @ManyToOne(fetch = FetchType.LAZY)  // 多對一關聯
            @JoinColumn(name = "ordersId",nullable = false)
            private Orders orders;  // 訂單





}
