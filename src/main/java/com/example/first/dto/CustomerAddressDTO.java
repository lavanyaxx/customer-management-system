package com.example.first.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class CustomerAddressDTO {

    private Long addressId;
    @NotNull(message = "Customer identifier is required")
    private Long customerIdentifier;

    @NotBlank(message = "Customer address type is required")
    private String customerAddressType;

    @NotBlank(message = "Customer address value is required")
    private String customerAddressValue;

    @NotNull(message = "Effective date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")

    private LocalDate effectiveDate;

    public CustomerAddressDTO() {
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getCustomerIdentifier() {
        return customerIdentifier;
    }

    public void setCustomerIdentifier(Long customerIdentifier) {
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

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
    
