package com.emma_dev.ohguohgu.cart;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.service.AdminService;
import com.emma_dev.ohguohgu.cart.Service.CartService;
import com.emma_dev.ohguohgu.cart.entity.Cart;
import com.emma_dev.ohguohgu.cart.model.CartInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CartController {

    private AdminService adminService;
    private CartService cartService;

    @GetMapping("/member/item/add")
    public String cartAdd(Model model, Long id) {
        Item item = adminService.getItem(id);

        Long cartNo = cartService.getCartNo();

        CartInput cart = CartInput.builder()
                .cartId(cartNo + 1)
                .item(item)
                .count(1)
                .build();

        return "/member/itemList";
    }
}
