package com.example.first.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerProofOfIdDTO {
    private Long proofId;
    @JsonProperty("customerIdentifier")
    @NotNull(message = "Customer identifier is required")
    private Long customerIdentifier;
    @JsonProperty("proofOfIdType")
    @NotBlank(message = "Proof of ID type is required")
    private String proofOfIdType;
    @JsonProperty("proofOfIdValue")
    @NotBlank(message = "Proof of ID value is required")
    private String proofOfIdValue;
    @JsonProperty("effectiveDate")
    @NotNull(message = "Effective date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;
    @JsonProperty("endDate")
    @NotNull(message = "End date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    @JsonProperty("startDate")
    @NotNull(message = "Start date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    public CustomerProofOfIdDTO() {}
    public CustomerProofOfIdDTO(Long customerIdentifier, String proofOfIdType, String proofOfIdValue, LocalDate effectiveDate, LocalDate endDate, LocalDate startDate) {
        this.customerIdentifier = customerIdentifier;
        this.proofOfIdType = proofOfIdType;
        this.proofOfIdValue = proofOfIdValue;
        this.effectiveDate = effectiveDate;
        this.endDate = endDate;
        this.startDate = startDate;
    }
    public Long getProofId() {
        return proofId;
    }
    public void setProofId(Long proofId) {
        this.proofId = proofId;
    }
    public Long getCustomerIdentifier() {
        return customerIdentifier;
    }
    public void setCustomerIdentifier(Long customerIdentifier) {
        this.customerIdentifier = customerIdentifier;
    }
    public String getProofOfIdType() {
        return proofOfIdType;
    }
    public void setProofOfIdType(String proofOfIdType) {
        this.proofOfIdType = proofOfIdType;
    }
    public String getProofOfIdValue() {
        return proofOfIdValue;
    }
    public void setProofOfIdValue(String proofOfIdValue) {
        this.proofOfIdValue = proofOfIdValue;
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
    
}