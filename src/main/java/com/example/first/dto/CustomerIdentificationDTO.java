package com.example.first.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class CustomerIdentificationDTO {

    private Long customerIdentificationId;

    @NotNull(message = "Customer identifier is required")
    private Long customerIdentifier;

    @NotBlank(message = "Customer identification type is required")
    private String customerIdentificationType;

    @NotBlank(message = "Customer identification item is required")
    private String customerIdentificationItem;

    @NotNull(message = "Effective date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;

    public CustomerIdentificationDTO() {}

    public CustomerIdentificationDTO(Long customerIdentifier, String customerIdentificationType, String customerIdentificationItem, LocalDate effectiveDate) {
        this.customerIdentifier = customerIdentifier;
        this.customerIdentificationType = customerIdentificationType;
        this.customerIdentificationItem = customerIdentificationItem;
        this.effectiveDate = effectiveDate;
    }

    public Long getCustomerIdentificationId() {
        return customerIdentificationId;
    }

    public void setCustomerIdentificationId(Long customerIdentificationId) {
        this.customerIdentificationId = customerIdentificationId;
    }

    public Long getCustomerIdentifier() {
        return customerIdentifier;
    }

    public void setCustomerIdentifier(Long customerIdentifier) {
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

}
