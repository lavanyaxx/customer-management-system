package com.example.first.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "CustomerClassificationType")
public class CustomerClassificationTypeEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerClassificationId;  

    @Column(nullable = false)
    private String customerClassificationType;
    @Column(nullable = false)
    private String customerClassificationValue;



    public CustomerClassificationTypeEntity() {}

    public Long getCustomerClassificationId() {
        return customerClassificationId;
    }

    public void setCustomerClassificationId(Long customerClassificationId) {
        this.customerClassificationId = customerClassificationId;
    }

    public String getCustomerClassificationType() {
        return customerClassificationType;
    }

    public void setCustomerClassificationType(String customerClassificationType) {
        this.customerClassificationType = customerClassificationType;
    }

    public String getCustomerClassificationValue() {
        return customerClassificationValue;
    }

    public void setCustomerClassificationValue(String customerClassificationValue) {
        this.customerClassificationValue = customerClassificationValue;
    }

}
