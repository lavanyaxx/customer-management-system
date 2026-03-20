package com.example.first.service;

import com.example.first.dto.CustomerClassificationTypeDTO;
import java.util.List;

public interface CustomerClassificationTypeService {

    CustomerClassificationTypeDTO createCustomerClassificationType(CustomerClassificationTypeDTO dto);

    CustomerClassificationTypeDTO getCustomerClassificationTypeById(Long id);

    List<CustomerClassificationTypeDTO> getAllCustomerClassificationTypes();

    CustomerClassificationTypeDTO updateCustomerClassificationType(Long id, CustomerClassificationTypeDTO dto);

    void deleteCustomerClassificationType(Long id);
}
