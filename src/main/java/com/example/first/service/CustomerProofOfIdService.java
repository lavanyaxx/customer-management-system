package com.example.first.service;

import com.example.first.dto.CustomerProofOfIdDTO;
import java.util.List;
public interface CustomerProofOfIdService {
    CustomerProofOfIdDTO createCustomerProofOfId(CustomerProofOfIdDTO dto);
    CustomerProofOfIdDTO getCustomerProofOfIdById(Long id);
    List<CustomerProofOfIdDTO> getAllCustomerProofOfIds();
    CustomerProofOfIdDTO updateCustomerProofOfId(Long id, CustomerProofOfIdDTO dto);
    void deleteCustomerProofOfId(Long id);
}

