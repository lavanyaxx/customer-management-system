package com.example.first.dto;

import java.util.List;

public class BulkUploadResponseDTO {
    private int totalRecords;
    private int successCount;
    private int failureCount;
    private List<BulkUploadErrorDTO> errors;
    private String message;

    public BulkUploadResponseDTO(int totalRecords, int successCount, int failureCount, 
                                 List<BulkUploadErrorDTO> errors, String message) {
        this.totalRecords = totalRecords;
        this.successCount = successCount;
        this.failureCount = failureCount;
        this.errors = errors;
        this.message = message;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(int successCount) {
        this.successCount = successCount;
    }

    public int getFailureCount() {
        return failureCount;
    }

    public void setFailureCount(int failureCount) {
        this.failureCount = failureCount;
    }

    public List<BulkUploadErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<BulkUploadErrorDTO> errors) {
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
