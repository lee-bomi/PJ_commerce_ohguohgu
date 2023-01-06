package com.emma_dev.ohguohgu.admin.controller;

import com.emma_dev.ohguohgu.admin.entity.Category;
import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.model.CategoryInput;
import com.emma_dev.ohguohgu.admin.model.ItemDto;
import com.emma_dev.ohguohgu.admin.service.AdminService;
import com.emma_dev.ohguohgu.admin.service.CategoryService;
import com.emma_dev.ohguohgu.admin.service.CategoryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    public String itemRegister(Model model, HttpServletRequest request) {

        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);

        return "/admin/itemRegister";
    }

    @PostMapping("/item/register.do")
    public String itemRegisterSubmit(Model model, ItemDto dto) {
        System.out.println("받아온 이름 : " + dto.getItemName());
        System.out.println("받아온 아이디 : " +dto.getCategoryId());

        Item item = adminService.itemRegister(Item.from(dto));
        model.addAttribute("item", item);

        //상품리스트 가져와서 front로 보내기
        List<Item> itemList = adminService.getItemList();
        model.addAttribute("itemList", itemList);

        return "/member/itemList";
    }

    @GetMapping("/item/itemList.do")
    public String itemList(Model model) {

        List<Item> itemList = adminService.getItemList();
        model.addAttribute("itemList", itemList);

        return "/admin/itemList";
    }

    @PostMapping("/item/delete.do")
    public String deleteItem(Model model, Long id) {

        adminService.deleteItem(id);

        return "redirect:/admin/item/itemList.do";
    }

    @GetMapping("/category/list.do")
    public String categoryList(Model model) {
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categories", categoryList);

        return "/admin/categoryList";
    }


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
