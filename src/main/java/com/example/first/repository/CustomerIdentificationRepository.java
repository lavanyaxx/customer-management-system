package com.example.first.repository;

import com.example.first.entity.CustomerIdentificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerIdentificationRepository extends JpaRepository<CustomerIdentificationEntity, Long> {
    void deleteByCustomerIdentifier_CustomerIdentifier(Long customerId);
}
