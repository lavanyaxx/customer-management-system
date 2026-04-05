package com.example.first.entity;
import jakarta.persistence.*;
import java.time.*;

@Entity
@Table(name = "CustomerContactInformation")
public class CustomerContactInformationEntity extends AuditableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactInformationId;
    @JoinColumn(name = "customerIdentifier", nullable = false)
    @ManyToOne
    private CustomerDetailEntity customerIdentifier;
    @Column(name = "CustomerContactType",nullable = false)
    private String customerContactType;
    @Column(name = "CustomerContactValue",nullable = false)
    private String customerContactValue;

    @Column(name = "EndDate",nullable = false)
    private LocalDate endDate;
    @Column(name = "StartDate",nullable = false)
    private LocalDate startDate;
    public CustomerContactInformationEntity() {
    }
    public CustomerContactInformationEntity(CustomerDetailEntity customerIdentifier, String customerContactType, String customerContactValue, LocalDate effectiveDate, LocalDate endDate, LocalDate startDate) {
        this.customerIdentifier = customerIdentifier;
        this.customerContactType = customerContactType;
        this.customerContactValue = customerContactValue;
        this.setEffectiveDate(effectiveDate);
        this.endDate = endDate;
        this.startDate = startDate;
    }
    public CustomerDetailEntity getCustomerIdentifier() {
        return customerIdentifier;
    }
    public void setCustomerIdentifier(CustomerDetailEntity customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }
    public String getCustomerContactType() {
        return customerContactType;
    }
    public void setCustomerContactType(String customerContactType) {
        this.customerContactType = customerContactType;
    }
    public String getCustomerContactValue() {
        return customerContactValue;
    }
    public void setCustomerContactValue(String customerContactValue) {
        this.customerContactValue = customerContactValue;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public Long getContactInformationId() {
        return contactInformationId;
    }
    public void setContactInformationId(Long contactInformationId) {
        this.contactInformationId = contactInformationId;
    }
    
    
}
