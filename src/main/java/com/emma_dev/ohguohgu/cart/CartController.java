package com.emma_dev.ohguohgu.cart;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.service.AdminService;
import com.emma_dev.ohguohgu.cart.Service.CartService;
import com.emma_dev.ohguohgu.cart.entity.Cart;
import com.emma_dev.ohguohgu.cart.model.CartInput;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CartController {

    private AdminService adminService;
    private CartService cartService;

    @ApiOperation(value = "카트에 삼품을 담는 API", notes = "JPA사용에 실패하여 카트아이디를 하나씩 추가해는것으로 완료하였습니다")
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
