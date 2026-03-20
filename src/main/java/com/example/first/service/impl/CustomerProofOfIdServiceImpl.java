package com.example.first.service.impl;

import com.example.first.dto.CustomerProofOfIdDTO;
import com.example.first.entity.CustomerProofOfIdEntity;
import com.example.first.entity.CustomerDetailEntity;
import com.example.first.repository.CustomerProofOfIdRepository;
import com.example.first.repository.CustomerDetailRepository;
import com.example.first.service.CustomerProofOfIdService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
@Service
@Transactional
public class CustomerProofOfIdServiceImpl implements CustomerProofOfIdService {

    public final CustomerProofOfIdRepository repository;
    public final CustomerDetailRepository customerDetailRepository;
    public CustomerProofOfIdServiceImpl(CustomerProofOfIdRepository repository, CustomerDetailRepository customerDetailRepository) {
        this.repository = repository;
        this.customerDetailRepository = customerDetailRepository;
    }
    @Override
    public CustomerProofOfIdDTO createCustomerProofOfId(CustomerProofOfIdDTO dto) {
        CustomerProofOfIdEntity entity = new CustomerProofOfIdEntity();
        CustomerDetailEntity customerDetail = customerDetailRepository.findById(dto.getCustomerIdentifier())
                .orElseThrow(() -> new RuntimeException("Customer Detail not found with id: " + dto.getCustomerIdentifier()));  
        entity.setCustomerIdentifier(customerDetail);
        entity.setProofOfIdType(dto.getProofOfIdType());
        entity.setProofOfIdValue(dto.getProofOfIdValue());
        entity.setEffectiveDate(dto.getEffectiveDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStartDate(dto.getStartDate());
        CustomerProofOfIdEntity saved = repository.save(entity);
        return toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerProofOfIdDTO getCustomerProofOfIdById(Long id) {
        CustomerProofOfIdEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Proof Of Id not found with id: " + id));
        return toDTO(entity);
    }
    private CustomerProofOfIdDTO toDTO(CustomerProofOfIdEntity entity) {
        CustomerProofOfIdDTO dto = new CustomerProofOfIdDTO();
        CustomerDetailEntity customerDetail = entity.getCustomerIdentifier();
        dto.setProofId(entity.getProofId());
        dto.setCustomerIdentifier(customerDetail.getCustomerIdentifier());
        dto.setProofOfIdType(entity.getProofOfIdType());
        dto.setProofOfIdValue(entity.getProofOfIdValue());
        dto.setEffectiveDate(entity.getEffectiveDate());
        dto.setEndDate(entity.getEndDate());
        dto.setStartDate(entity.getStartDate());
        return dto;
    }
    @Override
    public List<CustomerProofOfIdDTO> getAllCustomerProofOfIds() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    @Override
    public CustomerProofOfIdDTO updateCustomerProofOfId(Long id, CustomerProofOfIdDTO dto) {
        CustomerProofOfIdEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Proof Of Id not found with id: " + id));
        CustomerDetailEntity customerDetail = customerDetailRepository.findById(dto.getCustomerIdentifier())
                .orElseThrow(() -> new RuntimeException("Customer Detail not found with id: " + dto.getCustomerIdentifier()));
        existing.setCustomerIdentifier(customerDetail);
        existing.setProofOfIdType(dto.getProofOfIdType());
        existing.setProofOfIdValue(dto.getProofOfIdValue());
        existing.setEffectiveDate(dto.getEffectiveDate());
        existing.setEndDate(dto.getEndDate());
        existing.setStartDate(dto.getStartDate());
        CustomerProofOfIdEntity updated = repository.save(existing);
        return toDTO(updated);
    }
    @Override
    public void deleteCustomerProofOfId(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Customer Proof Of Id not found with id: " + id);
        }
        repository.deleteById(id);
    }
}
