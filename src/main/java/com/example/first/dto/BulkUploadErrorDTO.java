package com.example.first.dto;

public class BulkUploadErrorDTO {
    private int rowNumber;
    private String errorMessage;
    private String data;

    public BulkUploadErrorDTO(int rowNumber, String errorMessage, String data) {
        this.rowNumber = rowNumber;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
