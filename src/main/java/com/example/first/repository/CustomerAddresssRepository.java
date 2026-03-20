package com.example.first.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.first.entity.CustomerAddressEntity;


public interface CustomerAddresssRepository extends JpaRepository<CustomerAddressEntity, Long> {

}
