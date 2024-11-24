package com.example.bistro.orders;

import com.example.bistro.employee.Employee;
import com.example.bistro.employee.EmployeeRepositoryDao;
import com.example.bistro.members.Members;
import com.example.bistro.members.MembersRepositoryDao;
import com.example.bistro.menu.Menu;
import com.example.bistro.menu.MenuRepositoryDao;
import com.example.bistro.ordersDetails.OrdersDetails;
import com.example.bistro.payment.Payment;
import com.example.bistro.seats.Seats;
import com.example.bistro.seats.SeatsRepositoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class OrdersController {

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private MembersRepositoryDao membersRepositoryDao;
    @Autowired
    private SeatsRepositoryDao seatsRepositoryDao;
    @Autowired
    private EmployeeRepositoryDao employeeRepositoryDao;
    @Autowired
    private MenuRepositoryDao menuRepositoryDao;

    //findAll orders
        @GetMapping("/Bistro/Orders/findAll")
        public String findAll(Model model) {
            List<Orders> allOrders = ordersService.findAllOrders();
            model.addAttribute("allOrders", allOrders);
            return "orders/ordersView";
        };

    //delete orders by id OK
        @PostMapping("/Bistro/Orders/deleteOrders")
        public String deleteOrders(@RequestParam Integer id) {
            ordersService.deleteOrdersById(id);
            return "redirect:/Bistro/Orders/findAll";
        };


    //create 新增一筆訂單 OK 包含memberNull問題和Payment問題
        @PostMapping("/Bistro/Orders/createOrders")
        public String createOrders(
                        //表單回傳的值
                               @RequestParam String ordersName,
                               @RequestParam String ordersTel,
                               @RequestParam String seatType,
                               @RequestParam Integer ordersSumPrice,
                               @RequestParam String ordersStatus,
                               @RequestParam String ordersRequest,
                               @RequestParam List<String> paymentStatus,
                               @RequestParam List<String> paymentWay,
                               @RequestParam List<Integer> paymentPrice,
                               @RequestParam(required = false) Integer memberId,
//                               @RequestParam List<Integer> menuId,
//                               @RequestParam Integer quantities,
//                               @RequestParam List<Integer> prices,
                               @RequestParam Integer seatsId ) {

        //欄位要有的值，所以表單有的可以不用有，但欄位要的一定要給他
            Orders orders = new Orders();
            orders.setOrdersName(ordersName);
            orders.setOrdersTel(ordersTel);
            orders.setSeatType(seatType);
            orders.setOrdersSumPrice(ordersSumPrice);
            orders.setPointGetted(ordersSumPrice / 100);  // 自動計算 pointGetted
            orders.setOrdersStatus(ordersStatus);
            orders.setOrdersRequest(ordersRequest);

        // 處理 memberId
            if (memberId != null && memberId != 0) {
                // 查找會員，若未找到則返回錯誤
                Members member = membersRepositoryDao.findById(memberId).orElseThrow(() ->
                        new IllegalArgumentException("Invalid member ID: " + memberId));
                orders.setMembers(member);
            } else {
                // 非會員情況下不設置 membersId
                orders.setMembers(null);
            }
        // 座位設置關聯
            Seats seat = seatsRepositoryDao.findById(seatsId).orElse(null);
            orders.setSeats(seat);
        // 付款資訊植入
            for (int i = 0; i < paymentStatus.size(); i++) {
                Payment payment = new Payment();
                payment.setOrders(orders);
                payment.setPaymentStatus(paymentStatus.get(i));
                payment.setPaymentWay(paymentWay.get(i));
                payment.setPaymentPrice(paymentPrice.get(i));
                orders.getPayment().add(payment);
            // 更新訂單的最新付款狀態
                orders.setLatestPaymentStatus(paymentStatus.get(i));
            }

//            // 新增訂單詳情並將odName和odPrice的值來自menu1 according to menuid
//            for (int i = 0; i < menuId.size(); i++) {
//                int finalI = i;
//                Menu menu = menuRepositoryDao.findById(menuId.get(i)).orElseThrow(() -> new IllegalArgumentException("Invalid menu ID: " + menuId.get(finalI)));
//                OrdersDetails orderDetails = new OrdersDetails();
//                orderDetails.setOrders(orders);
//                orderDetails.setMenu(menu);
//                orderDetails.setOdName(menu.getProductName());
//                // 設置 `odName` 為產品名稱
//                orderDetails.setOdQuantity(quantities);
//                orderDetails.setOdPrice(prices.get(i));
//                orderDetails.setOdSumPrice( quantities * prices.get(i));
//                orders.getOrdersDetails().add(orderDetails);
//            }


        // 保存訂單到DB
            ordersService.insertOrders(orders);

        return "redirect:/Bistro/Orders/findAll";
    }


}


    //update



