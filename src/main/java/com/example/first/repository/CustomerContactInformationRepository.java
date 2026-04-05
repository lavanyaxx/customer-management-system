package com.example.first.repository;

import com.example.first.entity.CustomerContactInformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerContactInformationRepository extends JpaRepository<CustomerContactInformationEntity, Long> {
    void deleteByCustomerIdentifier_CustomerIdentifier(Long customerId);
}
