package com.example.first.repository;

import com.example.first.entity.CustomerProofOfIdEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerProofOfIdRepository extends JpaRepository<CustomerProofOfIdEntity, Long> {
    void deleteByCustomerIdentifier_CustomerIdentifier(Long customerId);
}
