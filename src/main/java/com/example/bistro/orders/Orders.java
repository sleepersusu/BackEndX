package com.example.bistro.orders;

import com.example.bistro.employee.Employee;
import com.example.bistro.members.Members;
import com.example.bistro.ordersDetails.OrderDetails;
import com.example.bistro.seats.Seats;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "Orders")
public class Orders {

    //PK
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer ID;

    //欄位
        //訂單資訊
            private String  ordersName;
            private String  ordersTel;
            private String  eatStatus;
            private Integer ordersSumPrice;
            private Integer pointGetted;
            private String ordersStatus;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @Temporal(TemporalType.TIMESTAMP)
            private Date createdAt;


        //付款資訊
            private String paymentWay;
            private String paymentStatus;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @Temporal(TemporalType.TIMESTAMP)
            private Date paymentTime;

    //FK
        //多對一：多個訂單可以來自同一個會員
            @ManyToOne
            @JoinColumn(name = "memberId")
            private Members members;
        //多對一：多個訂單可以來自同一個桌號
            @ManyToOne
            @JoinColumn(name = "seatsId")
            private Seats seats;
        //多對一：多個訂單可以來自同一個員工處理
            @ManyToOne
            @JoinColumn(name = "employeeId")
            private Employee employee;
        //一對多：一筆訂單會有多筆訂單詳情
            @OneToMany(mappedBy = "orders",cascade = CascadeType.ALL, orphanRemoval = true)
            private List<OrderDetails> orderDetails = new ArrayList<>();  // 訂單明細



    public Orders() {}





}
