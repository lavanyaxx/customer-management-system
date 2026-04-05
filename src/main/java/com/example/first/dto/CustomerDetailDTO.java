package com.example.first.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public class CustomerDetailDTO {
    private Long customerIdentifier;
    @NotBlank(message = "Customer full name is required")
    private String customerFullName;
    @NotBlank(message = "Customer gender is required")
    private String customerGender;
    @NotNull(message = "Customer type is required")
    private Long customerType;
    @NotNull(message = "Customer date of birth is required")
    @PastOrPresent(message = "Customer date of birth must be in the past or present")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate customerDateOfBirth;
    @NotBlank(message = "Customer preferred language is required")
    private String customerPreferredLanguage;
    @NotBlank(message = "Customer status is required")
    private String customerStatus;
    @NotBlank(message = "Customer country of origin is required")
    private String customerCountryOfOrigin;

    public CustomerDetailDTO() {
    }

    public CustomerDetailDTO(String customerFullName, String customerGender, Long customerType,
            LocalDate customerDateOfBirth, String customerPreferredLanguage, String customerStatus,
            String customerCountryOfOrigin) {
        this.customerFullName = customerFullName;
        this.customerGender = customerGender;
        this.customerType = customerType;
        this.customerDateOfBirth = customerDateOfBirth;
        this.customerPreferredLanguage = customerPreferredLanguage;
        this.customerStatus = customerStatus;
        this.customerCountryOfOrigin = customerCountryOfOrigin;
    }

    public Long getCustomerIdentifier() {
        return customerIdentifier;
    }

    public void setCustomerIdentifier(Long customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public Long getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Long customerType) {
        this.customerType = customerType;
    }

    public LocalDate getCustomerDateOfBirth() {
        return customerDateOfBirth;
    }

    public void setCustomerDateOfBirth(LocalDate customerDateOfBirth) {
        this.customerDateOfBirth = customerDateOfBirth;
    }

    public String getCustomerPreferredLanguage() {
        return customerPreferredLanguage;
    }

    public void setCustomerPreferredLanguage(String customerPrefferedLanguage) {
        this.customerPreferredLanguage = customerPrefferedLanguage;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getCustomerCountryOfOrigin() {
        return customerCountryOfOrigin;
    }

    public void setCustomerCountryOfOrigin(String customerCountryOfOrigin) {
        this.customerCountryOfOrigin = customerCountryOfOrigin;
    }

}
