package com.example.first.repository;

import com.example.first.entity.CustomerNamesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerNamesRepository extends JpaRepository<CustomerNamesEntity, Long> {
    void deleteByCustomerIdentifier_CustomerIdentifier(Long customerId);
}
