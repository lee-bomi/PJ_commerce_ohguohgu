package com.emma_dev.ohguohgu.admin.controller;

import com.emma_dev.ohguohgu.admin.entity.Category;
import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.model.CategoryInput;
import com.emma_dev.ohguohgu.admin.model.ItemDto;
import com.emma_dev.ohguohgu.admin.service.AdminService;
import com.emma_dev.ohguohgu.admin.service.CategoryService;
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
    private final CategoryService categoryService;

    @GetMapping("/main.do")
    public String main() {

        return "/admin/main";
    }

    @GetMapping("/item/register.do")
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

    @GetMapping("/item/itemList.do")
    public String itemList() {
        return "/admin/itemList";
    }

    @GetMapping("/category/list.do")
    public String categoryList(Model model) {
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categories", categoryList);

        return "/admin/categoryList";
    }

//    @GetMapping("/category/register.do")
//    public String categoryRegister() {
//        return "/admin/categoryRegister";
//    }

    @PostMapping("/category/register.do")
    public String categoryRegisterSubmit(Model model, CategoryInput categoryInput) {

        //categoryInput -> Category로 변환
        categoryService.categoryRegister(Category.from(categoryInput));

        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categories", categoryList);

        return "/admin/categoryList";
    }

    @PostMapping("/category/delete.do")
    public String deleteCategory(Model model, Long id) {

        categoryService.deleteCategory(id);
        return "redirect:/admin/category/list.do";
    }
}
