package com.emma_dev.ohguohgu.admin.controller;

import com.emma_dev.ohguohgu.admin.entity.Category;
import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.model.CategoryInput;
import com.emma_dev.ohguohgu.admin.model.ItemDto;
import com.emma_dev.ohguohgu.admin.service.AdminService;
import com.emma_dev.ohguohgu.admin.service.CategoryService;
import com.emma_dev.ohguohgu.admin.service.CategoryServiceImpl;
import com.emma_dev.ohguohgu.member.entity.Member;
import com.emma_dev.ohguohgu.member.service.MemberService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final CategoryService categoryService;
    private final MemberService memberService;

    @GetMapping("/main.do")
    public String main() {
        return "/admin/main";
    }

    @ApiOperation("관리자 - 카테고리 등록페이지로 이동")
    @GetMapping("/item/register.do")
    public String itemRegister(Model model, HttpServletRequest request) {

        List<Category> categoryList = categoryService.getCategoryList();

        model.addAttribute("categoryList", categoryList);

        return "/admin/itemRegister";
    }

    @ApiOperation("관리자 - 상품 등록")
    @PostMapping("/item/register.do")
    public String itemRegisterSubmit(Model model, ItemDto dto) {

        Item item = adminService.itemRegister(Item.from(dto));
        model.addAttribute("item", item);

        //상품리스트 가져와서 front로 보내기
        List<Item> itemList = adminService.getItemList();
        model.addAttribute("itemList", itemList);

        return "/admin/itemList";
    }

    @ApiOperation("관리자 - 등록된 모든 상품의 리스트 확인")
    @GetMapping("/item/itemList.do")
    public String itemList(Model model) {

        List<Item> itemList = adminService.getItemList();
        model.addAttribute("itemList", itemList);

        return "/admin/itemList";
    }

    @ApiOperation("관리자 - 상품리스트의 상품 삭제")
    @PostMapping("/item/delete.do")
    public String deleteItem(Model model, Long id) {

        adminService.deleteItem(id);

        return "redirect:/admin/item/itemList.do";
    }

    @ApiOperation("관리자 - 카테고리를 등록하고 확인하는 페이지로 이동")
    @GetMapping("/category/list.do")
    public String categoryList(Model model) {
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categories", categoryList);

        return "/admin/categoryList";
    }

    @ApiOperation("관리자 - 카테고리 등록")
    @PostMapping("/category/register.do")
    public String categoryRegisterSubmit(Model model, CategoryInput categoryInput) {

        categoryService.categoryRegister(Category.from(categoryInput));
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categories", categoryList);

        return "/admin/categoryList";
    }

    @ApiOperation("관리자 - 카테고리 삭제")
    @PostMapping("/category/delete.do")
    public String deleteCategory(Model model, Long id) {

        categoryService.deleteCategory(id);
        return "redirect:/admin/category/list.do";
    }

    @ApiOperation("관리자 - 회원 리스트 가져오기")
    @GetMapping("/member/list.do")
    public String memberList(Model model) {
        List<Member> memberList = memberService.getMemberList();
        model.addAttribute("memberList", memberList);

        return "/admin/memberList";
    }
}
