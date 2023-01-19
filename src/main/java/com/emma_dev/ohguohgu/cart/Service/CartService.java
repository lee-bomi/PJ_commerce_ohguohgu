package com.emma_dev.ohguohgu.cart.Service;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.cart.entity.Cart;
import com.emma_dev.ohguohgu.cart.model.CartInput;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface CartService {

    /**
     * 현재 카트번호 가져오기
     */
    Long getCartNo();


    /**
     * 장바구니에 넣을 제품 가져오기
     */
    Item getItem(Long id);


    /**
     * 카트에 상품 담기
     */
    Cart addItem(Cart cart);

    /**
     * 카트정보가져오기
     */
    Cart getCart(Long cartId);

    /**
     * 수정된 카트 정보 저장
     */
    Cart saveCart(Cart cart);

    /**
     * 현재 로그인한 사람의 정보 가져오기
     */
    UserDetails getUserInfo();

    /**
     * 현재 로그인한 사람의 카트내열 모두 가져오기
     */
    List<Cart> getAllCart(String user);

    /**
     * 카트에서 특정 상품을 삭제하기
     */
    void deleteItem(Cart cart);
}
