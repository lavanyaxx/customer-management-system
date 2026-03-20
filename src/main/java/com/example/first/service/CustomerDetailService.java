package com.example.first.service;
import com.example.first.dto.CustomerDetailDTO;
import com.example.first.dto.BulkUploadResponseDTO;
import java.util.List;
public interface CustomerDetailService {
    CustomerDetailDTO createCustomerDetail(CustomerDetailDTO dto);
    CustomerDetailDTO getCustomerDetailById(Long id);
    List<CustomerDetailDTO> getAllCustomerDetails();
    CustomerDetailDTO updateCustomerDetail(Long id, CustomerDetailDTO dto);
    void deleteCustomerDetail(Long id);
    BulkUploadResponseDTO bulkUploadCustomers(List<CustomerDetailDTO> customers);
}