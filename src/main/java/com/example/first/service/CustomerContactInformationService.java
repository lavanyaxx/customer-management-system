package com.example.first.service;

import com.example.first.dto.CustomerContactInformationDTO;
import java.util.List;

public interface CustomerContactInformationService {
    CustomerContactInformationDTO createCustomerContactInformation(CustomerContactInformationDTO dto);

    CustomerContactInformationDTO getCustomerContactInformationById(Long id);

    List<CustomerContactInformationDTO> getAllCustomerContactInformations();

    CustomerContactInformationDTO updateCustomerContactInformation(Long id, CustomerContactInformationDTO dto);

    void deleteCustomerContactInformation(Long id);
}
