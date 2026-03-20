package com.example.first.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;


public class CustomerClassificationTypeDTO {

    private Long classificationId;

    @NotBlank(message = "Classification type is required")
    private String classificationType;

    @NotBlank(message = "Classification value is required")
    private String classificationValue;

    @NotNull(message = "Effective date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate effectiveDate;

    public CustomerClassificationTypeDTO() {}

    public CustomerClassificationTypeDTO(Long classificationId,
                                         String classificationType,
                                         String classificationValue,
                                         LocalDate effectiveDate) {
        this.classificationId = classificationId;
        this.classificationType = classificationType;
        this.classificationValue = classificationValue;
        this.effectiveDate = effectiveDate;
    }

    public Long getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    public String getClassificationType() {
        return classificationType;
    }

    public void setClassificationType(String classificationType) {
        this.classificationType = classificationType;
    }

    public String getClassificationValue() {
        return classificationValue;
    }

    public void setClassificationValue(String classificationValue) {
        this.classificationValue = classificationValue;
    }

    public LocalDate getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(LocalDate effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
