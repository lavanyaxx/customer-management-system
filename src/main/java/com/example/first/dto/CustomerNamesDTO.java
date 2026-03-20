package com.example.first.dto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class CustomerNamesDTO {
    private Long customerNameId;
    @NotNull(message = "Customer identifier is required")
    private Long customerIdentifier;
    @NotBlank(message = "Customer name type is required")
    private String customerNameType;
    @NotBlank(message = "Customer name value is required")
    private String customerNameValue;
    @NotNull(message = "Effective date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;
    public CustomerNamesDTO() {}
    public CustomerNamesDTO(Long customerIdentifier, String customerNameType, String customerNameValue, LocalDate effectiveDate) {
        this.customerIdentifier = customerIdentifier;
        this.customerNameType = customerNameType;
        this.customerNameValue = customerNameValue;
        this.effectiveDate = effectiveDate;
    }
    public Long getCustomerIdentifier() {
        return customerIdentifier;
    }
    public void setCustomerIdentifier(Long customerIdentifier) {
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
    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }
    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
    public Long getCustomerNameId() {
        return customerNameId;
    }
    public void setCustomerNameId(Long customerNameId) {
        this.customerNameId = customerNameId;
    }
    
}
