package com.example.first.repository;
import com.example.first.entity.CustomerDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CustomerDetailRepository extends JpaRepository<CustomerDetailEntity, Long> {
}
