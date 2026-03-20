package com.example.first.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat; 
import java.time.LocalDate;

public class CustomerContactInformationDTO {
    private Long contactInformationId;
    @NotNull(message = "Customer identifier is required")
    private Long customerIdentifier;
    @NotBlank(message = "Customer contact type is required")
    private String customerContactType;
    @NotBlank(message = "Customer contact value is required")
    private String customerContactValue;
    @NotNull(message = "Effective date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;
    @NotNull(message = "End date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @NotNull(message = "Start date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    public CustomerContactInformationDTO() {}
    public CustomerContactInformationDTO(Long customerIdentifier, String customerContactType,
            String customerContactValue, LocalDate effectiveDate, LocalDate endDate, LocalDate startDate) {
        this.customerIdentifier = customerIdentifier;
        this.customerContactType = customerContactType;
        this.customerContactValue = customerContactValue;
        this.effectiveDate = effectiveDate;
        this.endDate = endDate;
        this.startDate = startDate;
    }
    public Long getCustomerIdentifier() {
        return customerIdentifier;
    }
    public void setCustomerIdentifier(Long customerIdentifier) {
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
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
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
