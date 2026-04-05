package com.example.first.entity;
import jakarta.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "CustomerNames")
public class CustomerNamesEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerNameId;
    @JoinColumn(name = "customerIdentifier", nullable = false)
    @ManyToOne
    private CustomerDetailEntity customerIdentifier; 
    @Column(nullable = false)
    private String customerNameType;
    @Column(nullable = false)
    private String customerNameValue;


    public CustomerNamesEntity() {}
    public CustomerNamesEntity(CustomerDetailEntity customerIdentifier, String customerNameType, String customerNameValue, LocalDate effectiveDate) {
        this.customerIdentifier = customerIdentifier;
        this.customerNameType = customerNameType;
        this.customerNameValue = customerNameValue;
        this.setEffectiveDate(effectiveDate);
    }
    public CustomerDetailEntity getCustomerIdentifier() {
        return customerIdentifier;
    }
    public void setCustomerIdentifier(CustomerDetailEntity customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }
    public String getCustomerNameType() {
        return customerNameType;
    }
    public void setCustomerNameType(String customerNameType) {
        this.customerNameType = customerNameType;
    }
    public String getCustomerNameValue() {
        return customerNameValue;
    }
    public void setCustomerNameValue(String customerNameValue) {
        this.customerNameValue = customerNameValue;
    }

    public Long getCustomerNameId() {
        return customerNameId;
    }
    public void setCustomerNameId(Long customerNameId) {
        this.customerNameId = customerNameId;
    }

}
