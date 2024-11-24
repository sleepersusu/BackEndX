package com.example.bistro.members;

import com.example.bistro.cart.Cart;
import com.example.bistro.orders.Orders;
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
@Table(name = "Members")
public class Members {

  //PK
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer ID;

  //欄位
        //會員資訊
            private String memberAccount ; //UNIQUE
            private String memberPassword  ;
            private String memberName  ;
            private Integer memberAge;  //tinyint
            private Integer memberSex;  //tinyint

            @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
            @DateTimeFormat(pattern = "yyyy-MM-dd")
            @Temporal(TemporalType.TIMESTAMP)
            private Date memberBirthday;

            private Integer memberFavor ;
            private String memberAddress ;
            private String memberPhone ;
            private String memberEmail ;

            @Lob
            private byte[] memberImg ;

            private Integer memberPoint ;
            private String memberStatus ;

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
            @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            @Temporal(TemporalType.TIMESTAMP)
            private Date createdAt;
   //FK
        // 一對一：每個會員只有一個購物車
            @OneToOne(mappedBy = "members", cascade = CascadeType.ALL, orphanRemoval = true)
            private Cart cart;  // 每個會員只有一個購物車
        // 一對多：一個會員可以有很多訂單
            @OneToMany(mappedBy = "members", cascade = CascadeType.ALL, orphanRemoval = true)
            private List<Orders> orders = new ArrayList<>(); // 會員可以有多個訂單



    public Members() {};


}
