package com.example.first.service.impl;

import com.example.first.dto.CustomerContactInformationDTO;
import com.example.first.entity.CustomerContactInformationEntity;
import com.example.first.entity.CustomerDetailEntity;
import com.example.first.repository.CustomerContactInformationRepository;
import com.example.first.repository.CustomerDetailRepository;
import com.example.first.service.CustomerContactInformationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerContactInformationServiceImpl implements CustomerContactInformationService {

    private final CustomerContactInformationRepository repository;
    private final CustomerDetailRepository customerDetailRepository;
    public CustomerContactInformationServiceImpl(CustomerContactInformationRepository repository, CustomerDetailRepository customerDetailRepository) {
        this.repository = repository;
        this.customerDetailRepository = customerDetailRepository;
    }

    @Override
    public CustomerContactInformationDTO createCustomerContactInformation(CustomerContactInformationDTO dto) {
        CustomerContactInformationEntity entity = new CustomerContactInformationEntity();
        CustomerDetailEntity customerDetail = customerDetailRepository.findById(dto.getCustomerIdentifier())
                .orElseThrow(() -> new RuntimeException("Customer Detail not found with id: " + dto.getCustomerIdentifier()));
        entity.setContactInformationId(dto.getContactInformationId());
        entity.setCustomerIdentifier(customerDetail);
        entity.setCustomerContactType(dto.getCustomerContactType());
        entity.setCustomerContactValue(dto.getCustomerContactValue());
        entity.setEffectiveDate(dto.getEffectiveDate());
        entity.setEndDate(dto.getEndDate());
        entity.setStartDate(dto.getStartDate());

        CustomerContactInformationEntity saved = repository.save(entity);

        return toDTO(saved);
    }
    @Override
    @Transactional(readOnly = true)
    public CustomerContactInformationDTO getCustomerContactInformationById(Long id) {

        CustomerContactInformationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Contact Information not found with id: " + id));

        return toDTO(entity);
    }
    @Override
    @Transactional(readOnly = true)
    public List<CustomerContactInformationDTO> getAllCustomerContactInformations() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }
    @Override
    public CustomerContactInformationDTO updateCustomerContactInformation(Long id, CustomerContactInformationDTO dto)
    {
        CustomerDetailEntity customerDetail = customerDetailRepository.findById(dto.getCustomerIdentifier())
                .orElseThrow(() -> new RuntimeException("Customer Detail not found with id: " + dto.getCustomerIdentifier()));

        CustomerContactInformationEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contact Information not found: " + id));

        existing.setCustomerIdentifier(customerDetail);
        existing.setCustomerContactType(dto.getCustomerContactType());
        existing.setCustomerContactValue(dto.getCustomerContactValue());
        existing.setEffectiveDate(dto.getEffectiveDate());
        existing.setEndDate(dto.getEndDate());
        existing.setStartDate(dto.getStartDate());

        CustomerContactInformationEntity updated = repository.save(existing);

        return toDTO(updated);
    }
    @Override
    public void deleteCustomerContactInformation(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Contact Information not found: " + id);
        }
        repository.deleteById(id);
    }
    private CustomerContactInformationDTO toDTO(CustomerContactInformationEntity entity) {
        CustomerContactInformationDTO dto = new CustomerContactInformationDTO();
        dto.setContactInformationId(entity.getContactInformationId());
        dto.setCustomerIdentifier(entity.getCustomerIdentifier().getCustomerIdentifier());
        dto.setCustomerContactType(entity.getCustomerContactType());
        dto.setCustomerContactValue(entity.getCustomerContactValue());
        dto.setEffectiveDate(entity.getEffectiveDate());
        dto.setEndDate(entity.getEndDate());
        dto.setStartDate(entity.getStartDate());
        return dto;
    }

}
