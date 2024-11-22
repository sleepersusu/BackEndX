package com.example.bistro.cartCheckDTO;

import com.example.bistro.cart.Cart;
import com.example.bistro.cart.CartRepositoryDao;
import com.example.bistro.menu.Menu;
import com.example.bistro.menu.MenuRepositoryDao;
import com.example.bistro.orders.Orders;
import com.example.bistro.orders.OrdersRepository;
import com.example.bistro.ordersDetails.OrdersDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartCheckService {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private CartRepositoryDao cartRepositoryDao;

    @Autowired
    private MenuRepositoryDao menuRepositoryDao;

    @Transactional
    public void createOrder(CartCheck cartCheck) {
        //新增訂單
            Orders order = new Orders();
            order.setOrdersName(cartCheck.getCustomerName());
            order.setOrdersTel(cartCheck.getCustomerTel());
            order.setSeatStatus(cartCheck.getSeatStatus());
            order.setOrdersSumPrice(cartCheck.getTotalPrice());
            order.setOrdersStatus("已建立");
            order.setPointGetted(cartCheck.getPointEarned());
            order.setOrdersRequest(cartCheck.getSpecialRequest());
            order.setCreatedAt(new Date());
            order.setMembers(cartCheck.getMember());
            ordersRepository.save(order);

        // 2. 從購物車中完成生成訂單詳情
            List<Cart> carts = cartRepositoryDao.findByMemberId(cartCheck.getMember().getID());
            if (carts.isEmpty()) {
                throw new IllegalArgumentException("該會員的購物車記錄不存在，ID：" + cartCheck.getMember().getID());
            }
            for (Cart cart : carts) {
                OrdersDetails orderDetails = new OrdersDetails();
                orderDetails.setOrders(order);
                orderDetails.setMenu(cart.getMenu());
                orderDetails.setOdName(cart.getMenu().getProductName());
                orderDetails.setOdQuantity(cart.getCartCount());
                orderDetails.setOdPrice(cart.getMenu().getProductPrice());
                orderDetails.setOdSumPrice(cart.getCartCount() * cart.getMenu().getProductPrice());
                order.getOrdersDetails().add(orderDetails);

            // 同時更新菜單的庫存
                Menu menu = cart.getMenu();
                menu.setProductCount(menu.getProductCount() - cart.getCartCount());
                menuRepositoryDao.save(menu);
            }
        // 3. 保存訂單
            ordersRepository.save(order);
        // 4. 將購物車清空
            cartRepositoryDao.deleteByMemberId(cartCheck.getMember().getID());
    }
}


