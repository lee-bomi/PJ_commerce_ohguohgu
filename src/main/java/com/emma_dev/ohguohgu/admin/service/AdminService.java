package com.emma_dev.ohguohgu.admin.service;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.model.ItemInput;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {

    /**
     * 상품 등록
     */
    Item itemRegister(Item item);

    /**
     * 상품리스트 가져오기
     */
    List<Item> getItemList();

    /**
     * 상품삭제
     */
    void deleteItem(Long id);
}
