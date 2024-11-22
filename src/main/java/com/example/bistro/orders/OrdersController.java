package com.example.bistro.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;


}
