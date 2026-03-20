package com.example.first.service;

import com.example.first.dto.CustomerIdentificationDTO;
import java.util.List;
public interface CustomerIdentificationService {
CustomerIdentificationDTO createCustomerIdentification(CustomerIdentificationDTO dto);

    CustomerIdentificationDTO getCustomerIdentificationById(Long id);

    List<CustomerIdentificationDTO> getAllCustomerIdentifications();

    CustomerIdentificationDTO updateCustomerIdentification(Long id, CustomerIdentificationDTO dto);

    void deleteCustomerIdentification(Long id);
    
}
