package com.emma_dev.ohguohgu.admin.service;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.model.ItemInput;
import com.emma_dev.ohguohgu.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminRepository adminRepository;


    @Override
    @Transactional
    public Item itemRegister(Item item) {
        return adminRepository.save(item);
    }

    @Override
    @Transactional
    public List<Item> getItemList() {
        return adminRepository.findAll();
    }

    @Override
    public void deleteItem(Long id) {

        Optional<Item> byId = adminRepository.findById(id);
        if (byId.isEmpty()) {
            System.out.println("해당 아이템이 없습니다.");
            return;
        }
        Item item = byId.get();

        adminRepository.deleteById(item.getId());
    }

    @Override
    public Item getItem(Long id) {
        return adminRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 상품이 없습니다"));
    }


}
