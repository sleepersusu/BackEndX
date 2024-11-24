package com.example.bistro.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartRepositoryDao cartRepositoryDao;

    //購物車邏輯
        //前台：使用者
            //使用者按照會員id查詢當前購物車的所有資訊
                public List<Cart> getCartByMemberId(Integer memberId) {
                    List<Cart> carts = cartRepositoryDao.findByMemberId(memberId);
                    if (carts.isEmpty()) {
                        throw new IllegalArgumentException("該會員的購物車為空，ID：" + memberId);
                    }
                    return carts;
                }

            //使用者看到目前購物車全部
                public List<Cart> findAllCart(){
                    return cartRepositoryDao.findAll();
                };

            //使用者在將產品加入購物車時，只會將該品項累計+1，不會一直增加該產品的品項

            //使用者可以編輯購物車中產品的數量+/-，當歸0時候該產品直接從購物車中刪除

            //使用者可以刪除購物車中的該品項


        //後台：管理員
            //管理者依據購物車完成結帳後將根據會員id刪除該筆購物車
                public void deleteCartByMemberId(Integer memberId) {
                // 檢查購物車是否還存在
                    List<Cart> carts = cartRepositoryDao.findByMemberId(memberId);
                    if (carts.isEmpty()) {
                        throw new IllegalArgumentException("該會員的購物車記錄不存在，ID：" + memberId);
                    }

                // 根據會員id刪除所有購物車資訊
                cartRepositoryDao.deleteByMemberId(memberId);
                }





}
