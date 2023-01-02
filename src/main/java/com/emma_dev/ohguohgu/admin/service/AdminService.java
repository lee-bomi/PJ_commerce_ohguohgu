package com.emma_dev.ohguohgu.admin.service;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.model.ItemInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface AdminService {

    /**
     * 상품 등록
     */
    Item itemRegister(Item item);
}
