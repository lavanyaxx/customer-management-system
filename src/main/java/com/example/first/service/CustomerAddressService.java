package com.example.first.service;

import com.example.first.dto.CustomerAddressDTO;
import java.util.List;

public interface CustomerAddressService {

    List<CustomerAddressDTO> getAllCustomerAddresses();
    CustomerAddressDTO getCustomerAddressById(Long id);
    CustomerAddressDTO createCustomerAddress(CustomerAddressDTO customerAddressDTO);
    CustomerAddressDTO updateCustomerAddress(Long id, CustomerAddressDTO customerAddressDTO);
    void deleteCustomerAddress(Long id);

}
