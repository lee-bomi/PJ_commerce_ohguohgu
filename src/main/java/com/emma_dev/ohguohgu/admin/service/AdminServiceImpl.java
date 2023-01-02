package com.emma_dev.ohguohgu.admin.service;

import com.emma_dev.ohguohgu.admin.entity.Item;
import com.emma_dev.ohguohgu.admin.model.ItemInput;
import com.emma_dev.ohguohgu.admin.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


}
