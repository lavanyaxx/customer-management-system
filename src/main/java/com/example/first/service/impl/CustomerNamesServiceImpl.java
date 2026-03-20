package com.example.first.service.impl;

import com.example.first.dto.CustomerNamesDTO;
import com.example.first.entity.CustomerNamesEntity;
import com.example.first.entity.CustomerDetailEntity;
import com.example.first.repository.CustomerNamesRepository;
import com.example.first.repository.CustomerDetailRepository;
import com.example.first.service.CustomerNamesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerNamesServiceImpl implements CustomerNamesService {

    public final CustomerNamesRepository repository;
    public final CustomerDetailRepository customerDetailRepository;
    public CustomerNamesServiceImpl(CustomerNamesRepository repository, CustomerDetailRepository customerDetailRepository) {
        this.repository = repository;
        this.customerDetailRepository = customerDetailRepository;
    }
    @Override
    public CustomerNamesDTO createCustomerName(CustomerNamesDTO dto) {
        CustomerNamesEntity entity = new CustomerNamesEntity();
        CustomerDetailEntity customerDetail = customerDetailRepository.findById(dto.getCustomerIdentifier())
                .orElseThrow(() -> new RuntimeException("Customer Detail not found with id: " + dto.getCustomerIdentifier()));
        entity.setCustomerIdentifier(customerDetail);
        entity.setCustomerNameType(dto.getCustomerNameType());
        entity.setCustomerNameValue(dto.getCustomerNameValue());
        entity.setEffectiveDate(dto.getEffectiveDate());
        CustomerNamesEntity saved = repository.save(entity);
        return toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerNamesDTO getCustomerNameById(Long id) {
        CustomerNamesEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Name not found with id: " + id));
        return toDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerNamesDTO> getAllCustomerNames() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    @Override
    public CustomerNamesDTO updateCustomerName(Long id, CustomerNamesDTO dto) {
        CustomerNamesEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Name not found with id: " + id));
        existing.setCustomerNameType(dto.getCustomerNameType());
        existing.setCustomerNameValue(dto.getCustomerNameValue());
        existing.setEffectiveDate(dto.getEffectiveDate());
        CustomerNamesEntity updated = repository.save(existing);
        return toDTO(updated);
    }
    @Override
    public void deleteCustomerName(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Customer Name not found with id: " + id);
        }
        repository.deleteById(id);
    }
    private CustomerNamesDTO toDTO(CustomerNamesEntity entity) {
        CustomerNamesDTO dto = new CustomerNamesDTO();
        dto.setCustomerIdentifier(entity.getCustomerIdentifier().getCustomerIdentifier());
        dto.setCustomerNameType(entity.getCustomerNameType());
        dto.setCustomerNameValue(entity.getCustomerNameValue());
        dto.setEffectiveDate(entity.getEffectiveDate());
        return dto;
    }

}