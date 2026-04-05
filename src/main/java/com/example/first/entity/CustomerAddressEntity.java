package com.example.first.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CustomerAddress")
public class CustomerAddressEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @ManyToOne
    @JoinColumn(name = "customerIdentifier", nullable = false)
    private CustomerDetailEntity customerIdentifier;

    @Column(name = "customerAddressType")
    private String customerAddressType;

    @Column(name = "customerAddressValue")
    private String customerAddressValue;



    public CustomerAddressEntity() {
    }

    public Long getAddressId() {
        return addressId;
    }
    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
    public CustomerDetailEntity getCustomerIdentifier() {
        return customerIdentifier;
    }
    public void setCustomerIdentifier(CustomerDetailEntity customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }
    public String getCustomerAddressType() {
        return customerAddressType;
    }
    public void setCustomerAddressType(String customerAddressType) {
        this.customerAddressType = customerAddressType;
    }
    public String getCustomerAddressValue() {
        return customerAddressValue;
    }
    public void setCustomerAddressValue(String customerAddressValue) {
        this.customerAddressValue = customerAddressValue;
    }
}
