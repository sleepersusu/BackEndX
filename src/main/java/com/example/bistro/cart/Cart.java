package com.example.bistro.cart;

import com.example.bistro.members.Members;
import com.example.bistro.menu.Menu;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "Cart", uniqueConstraints = @UniqueConstraint(columnNames = {"membersId", "menuId"}))
public class Cart {

//PK
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private Integer ID;


//欄位
        @Column(name = "cartCount", nullable = false)
        private Integer cartCount;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        @Temporal(TemporalType.TIMESTAMP)
        @Column(name = "createdAt", nullable = false)
        private Date createdAt;


//FK
    // 一對一：一個會員只會有一台購物車
        @OneToOne
        @JoinColumn(name = "membersId", referencedColumnName = "ID", nullable = true , unique = true)
        private Members members;  // 與 Member 表的多對一關係，允許為 NULL
    //多對一：多個產品可以同時出現在一台購物車中
        @ManyToOne
        @JoinColumn(name = "menuId", referencedColumnName = "ID", nullable = false)
        private Menu menu;  // 與 Menu 表的多對一關係，不允許為 NULL


// hashCode 和 equals 方法，用於確保主鍵比較正確
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cart cart = (Cart) o;
            return Objects.equals(members, cart.members) && Objects.equals(menu, cart.menu);
        }

        @Override
        public int hashCode() {
            return Objects.hash(members, menu);
        }



}
