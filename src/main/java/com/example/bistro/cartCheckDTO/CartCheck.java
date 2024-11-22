package com.example.bistro.cartCheckDTO;

import com.example.bistro.members.Members;
import lombok.Data;

@Data
public class CartCheck {
    private String customerName;
    private String customerTel;
    private String seatStatus;
    private Integer totalPrice;
    private Integer pointEarned;
    private String specialRequest;
    private Members member; // 使用者對應的會員
}
