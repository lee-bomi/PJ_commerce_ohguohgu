package com.emma_dev.ohguohgu.cart.Service;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.repository.AdminRepository;
import com.emma_dev.ohguohgu.cart.entity.Cart;
import com.emma_dev.ohguohgu.cart.model.CartInput;
import com.emma_dev.ohguohgu.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final AdminRepository adminRepository;

    @Override
    public Long getCartNo() {

        return null;
    }

    @Override
    public Item getItem(Long id) {

        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 제품이 없습니다"));
    }

    @Override
    public Cart addItem(Cart cart) {

        return cartRepository.save(cart);
    }

    @Override
    public Cart getCart(Long cartId) {
        return cartRepository.findByCartId(cartId)
                .orElseThrow(() -> new RuntimeException("해당 카트가 없습니다"));
    }

    @Override
    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public UserDetails getUserInfo() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return (UserDetails) principal;
    }

    @Override
    public List<Cart> getAllCart(String user) {
        return cartRepository.findAllByUserName(user);
    }

    @Override
    public void deleteItem(Cart cart) {
        cartRepository.delete(cart);
    }
}
