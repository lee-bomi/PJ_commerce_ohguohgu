package com.emma_dev.ohguohgu.admin.repository;

import com.emma_dev.ohguohgu.admin.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Item, Long> {
}
