package com.emma_dev.ohguohgu;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final AdminService adminService;

    @GetMapping("/")
    public String main(Model model) {

        List<Item> itemList = adminService.getItemList();
        model.addAttribute("itemList", itemList);

        return "/index";
    }


}
