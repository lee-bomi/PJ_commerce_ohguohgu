package com.emma_dev.ohguohgu.admin;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.model.ItemDto;
import com.emma_dev.ohguohgu.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/main.do")
    public String main() {

        return "/admin/main";
    }

    @GetMapping("/item/register")
    public String itemRegister() {
        return "/admin/itemRegister";
    }

    @PostMapping("/item/register")
    public String itemRegisterSubmit(Model model, ItemDto dto) {

        Item item = adminService.itemRegister(Item.from(dto));
        model.addAttribute("item", item);

        //상품리스트 가져와서 front로 보내기
        List<Item> itemList = adminService.getItemList();
        model.addAttribute("itemList", itemList);

        return "/member/itemList";
    }


}
