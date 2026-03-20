package com.example.first.service.impl;

import com.example.first.dto.CustomerIdentificationDTO;
import com.example.first.entity.CustomerIdentificationEntity;
import com.example.first.entity.CustomerDetailEntity;
import com.example.first.repository.CustomerIdentificationRepository;
import com.example.first.repository.CustomerDetailRepository;
import com.example.first.service.CustomerIdentificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


@Service
@Transactional
public class CustomerIdentificationServiceImpl implements CustomerIdentificationService {
    public final CustomerIdentificationRepository repository;
    public final CustomerDetailRepository customerDetailRepository;
    public CustomerIdentificationServiceImpl(CustomerIdentificationRepository repository, CustomerDetailRepository customerDetailRepository) {
        this.repository = repository;
        this.customerDetailRepository = customerDetailRepository;
    }
    @Override
    public CustomerIdentificationDTO createCustomerIdentification(CustomerIdentificationDTO dto) {
        CustomerIdentificationEntity entity = new CustomerIdentificationEntity();
        CustomerDetailEntity customerDetail = customerDetailRepository.findById(dto.getCustomerIdentifier())
                .orElseThrow(() -> new RuntimeException("Customer Detail not found with id: " + dto.getCustomerIdentifier()));
        entity.setCustomerIdentifier(customerDetail);
        entity.setCustomerIdentificationType(dto.getCustomerIdentificationType());
        entity.setCustomerIdentificationItem(dto.getCustomerIdentificationItem());
        entity.setEffectiveDate(dto.getEffectiveDate());
        CustomerIdentificationEntity saved = repository.save(entity);
        return toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerIdentificationDTO getCustomerIdentificationById(Long id) {
        CustomerIdentificationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Identification not found with id: " + id));
        return toDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerIdentificationDTO> getAllCustomerIdentifications() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public CustomerIdentificationDTO updateCustomerIdentification(Long id, CustomerIdentificationDTO dto) {
        CustomerIdentificationEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Identification not found with id: " + id));
        existing.setCustomerIdentificationType(dto.getCustomerIdentificationType());
        existing.setCustomerIdentificationItem(dto.getCustomerIdentificationItem());
        existing.setEffectiveDate(dto.getEffectiveDate());
        CustomerIdentificationEntity updated = repository.save(existing);
        return toDTO(updated);
    }

    @Override
    public void deleteCustomerIdentification(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Customer Identification not found with id: " + id);
        }
        repository.deleteById(id);
    }
    private CustomerIdentificationDTO toDTO(CustomerIdentificationEntity entity) {
        CustomerIdentificationDTO dto = new CustomerIdentificationDTO();
        dto.setCustomerIdentifier(entity.getCustomerIdentifier().getCustomerIdentifier());
        dto.setCustomerIdentificationType(entity.getCustomerIdentificationType());
        dto.setCustomerIdentificationItem(entity.getCustomerIdentificationItem());
        dto.setEffectiveDate(entity.getEffectiveDate());
        return dto;
    }   
}
