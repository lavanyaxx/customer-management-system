package com.example.first.service.impl;

import com.example.first.dto.CustomerAddressDTO;
import com.example.first.service.CustomerAddressService;
import com.example.first.entity.CustomerAddressEntity;
import com.example.first.entity.CustomerDetailEntity;
import com.example.first.repository.CustomerAddresssRepository;
import com.example.first.repository.CustomerDetailRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerAddressServiceImpl implements CustomerAddressService {

    private final CustomerAddresssRepository repository;
    private final CustomerDetailRepository customerDetailRepository;
    public CustomerAddressServiceImpl(CustomerAddresssRepository repository, CustomerDetailRepository customerDetailRepository) {
        this.repository = repository;
        this.customerDetailRepository = customerDetailRepository;
    }

    @Override
    public CustomerAddressDTO createCustomerAddress(CustomerAddressDTO customerAddressDTO) {
        CustomerAddressEntity entity = new CustomerAddressEntity();
        CustomerDetailEntity customerDetail = customerDetailRepository.findById(customerAddressDTO.getCustomerIdentifier())
                .orElseThrow(() -> new RuntimeException("Customer Detail not found with id: " + customerAddressDTO.getCustomerIdentifier()));
        entity.setCustomerIdentifier(customerDetail);
        entity.setCustomerAddressType(customerAddressDTO.getCustomerAddressType());
        entity.setCustomerAddressValue(customerAddressDTO.getCustomerAddressValue());
        entity.setEffectiveDate(customerAddressDTO.getEffectiveDate());

        CustomerAddressEntity saved = repository.save(entity);

        return toDTO(saved);
    }
    @Override
    public CustomerAddressDTO getCustomerAddressById(Long id) {
        CustomerAddressEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Address not found with id: " + id));

        return toDTO(entity);
    }
    @Override
    public List<CustomerAddressDTO> getAllCustomerAddresses() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    @Override
    public CustomerAddressDTO updateCustomerAddress(Long id, CustomerAddressDTO customerAddressDTO) {
        CustomerAddressEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Address not found with id: " + id));

        existing.setCustomerIdentifier(customerDetailRepository.findById(customerAddressDTO.getCustomerIdentifier())
                .orElseThrow(() -> new RuntimeException("Customer Detail not found with id: " + customerAddressDTO.getCustomerIdentifier())));
        existing.setCustomerAddressType(customerAddressDTO.getCustomerAddressType());
        existing.setCustomerAddressValue(customerAddressDTO.getCustomerAddressValue());
        existing.setEffectiveDate(customerAddressDTO.getEffectiveDate());

        CustomerAddressEntity updated = repository.save(existing);

        return toDTO(updated);
    }
    @Override
    public void deleteCustomerAddress(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Customer Address not found with id: " + id);
        }
        repository.deleteById(id);
    }
    private CustomerAddressDTO toDTO(CustomerAddressEntity entity) {
        CustomerAddressDTO dto = new CustomerAddressDTO();
        dto.setCustomerIdentifier(entity.getCustomerIdentifier().getCustomerIdentifier());
        dto.setCustomerAddressType(entity.getCustomerAddressType());
        dto.setCustomerAddressValue(entity.getCustomerAddressValue());
        dto.setEffectiveDate(entity.getEffectiveDate());
        return dto;
    }

}
