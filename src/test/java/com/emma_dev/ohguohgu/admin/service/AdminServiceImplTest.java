package com.emma_dev.ohguohgu.admin.service;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.repository.AdminRepository;
import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceImplTest {

    @Autowired
    private AdminService adminService;

    @Autowired
    private AdminRepository adminRepository;

    
    @Test
    void itemRegister() {
        //given
        Long itemId = 1L;
        Item build = Item.builder()
                .id(itemId)
                .itemName("뼈간식")
                .price(1000)
                .description("뼈간식입니다")
                .build();

        //when
        adminService.itemRegister(build);
        Optional<Item> byId = adminRepository.findById(itemId);
        Item item = byId.get();
        //then
        assertEquals(item.getId(), itemId);
        assertEquals(item.getItemName(), "뼈간식");
        assertEquals(item.getPrice(), 1000);
        assertEquals(item.getDescription(), "뼈간식입니다");
        assertNotNull(item);
    }

}