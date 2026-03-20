package com.example.first.service;
import com.example.first.dto.CustomerNamesDTO;
import java.util.List;

public interface CustomerNamesService {
    CustomerNamesDTO createCustomerName(CustomerNamesDTO dto);
    CustomerNamesDTO getCustomerNameById(Long id);
    List<CustomerNamesDTO> getAllCustomerNames();
    CustomerNamesDTO updateCustomerName(Long id, CustomerNamesDTO dto);
    void deleteCustomerName(Long id);
}
