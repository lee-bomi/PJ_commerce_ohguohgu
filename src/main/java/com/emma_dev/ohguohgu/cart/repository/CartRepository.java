package com.emma_dev.ohguohgu.cart.repository;

import com.emma_dev.ohguohgu.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByCartId(Long cartId);

    List<Cart> findAllByUserName(String username);

}
