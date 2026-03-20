package com.example.first.entity;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "CustomerIdentification")
@Access(AccessType.FIELD)
public class CustomerIdentificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerIdentificationId;
    @JoinColumn(name = "customerIdentifier", nullable = false)
    @OneToOne
    private CustomerDetailEntity customerIdentifier;
    @Column(nullable = false)
    private String customerIdentificationType;
    @Column(nullable = false)
    private String customerIdentificationItem;
    @Column(nullable = false)
    private LocalDate effectiveDate;

    public CustomerIdentificationEntity() {}
    public CustomerIdentificationEntity(CustomerDetailEntity customerIdentifier, String customerIdentificationType, String customerIdentificationItem, LocalDate effectiveDate) {
        this.customerIdentifier = customerIdentifier;
        this.customerIdentificationType = customerIdentificationType;
        this.customerIdentificationItem = customerIdentificationItem;
        this.effectiveDate = effectiveDate;
    }
    public CustomerDetailEntity getCustomerIdentifier() {
        return customerIdentifier;
    }
    public void setCustomerIdentifier(CustomerDetailEntity customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }
    public String getCustomerIdentificationType() {
        return customerIdentificationType;
    }
    public void setCustomerIdentificationType(String customerIdentificationType) {
        this.customerIdentificationType = customerIdentificationType;
    }
    public String getCustomerIdentificationItem() {
        return customerIdentificationItem;
    }
    public void setCustomerIdentificationItem(String customerIdentificationItem) {
        this.customerIdentificationItem = customerIdentificationItem;
    }
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    public Long getCustomerIdentificationId() {
        return customerIdentificationId;
    }
    public void setCustomerIdentificationId(Long customerIdentificationId) {
        this.customerIdentificationId = customerIdentificationId;
    }

}
