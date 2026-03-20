package com.example.first.repository;

import com.example.first.entity.CustomerClassificationTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerClassificationTypeRepository
        extends JpaRepository<CustomerClassificationTypeEntity, Long> {
}
