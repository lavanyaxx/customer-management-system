package com.example.first.service.impl;

import com.example.first.dto.CustomerClassificationTypeDTO;
import com.example.first.entity.CustomerClassificationTypeEntity;
import com.example.first.repository.CustomerClassificationTypeRepository;
import com.example.first.service.CustomerClassificationTypeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerClassificationTypeServiceImpl implements CustomerClassificationTypeService {

    private final CustomerClassificationTypeRepository repository;

    public CustomerClassificationTypeServiceImpl(CustomerClassificationTypeRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerClassificationTypeDTO createCustomerClassificationType(CustomerClassificationTypeDTO dto) {

        CustomerClassificationTypeEntity entity = new CustomerClassificationTypeEntity();

        entity.setCustomerClassificationType(dto.getClassificationType());
        entity.setCustomerClassificationValue(dto.getClassificationValue());
        entity.setEffectiveDate(dto.getEffectiveDate());

        CustomerClassificationTypeEntity saved = repository.save(entity);

        return toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerClassificationTypeDTO getCustomerClassificationTypeById(Long id) {

        CustomerClassificationTypeEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Classification Type not found with id: " + id));

        return toDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CustomerClassificationTypeDTO> getAllCustomerClassificationTypes() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    @Override
    public CustomerClassificationTypeDTO updateCustomerClassificationType(Long id, CustomerClassificationTypeDTO dto) {

        CustomerClassificationTypeEntity existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classification not found: " + id));

        if (dto.getClassificationType() != null)
            existing.setCustomerClassificationType(dto.getClassificationType());

        if (dto.getClassificationValue() != null)
            existing.setCustomerClassificationValue(dto.getClassificationValue());

        if (dto.getEffectiveDate() != null)
            existing.setEffectiveDate(dto.getEffectiveDate());

        return toDTO(repository.save(existing));
    }

    @Override
    public void deleteCustomerClassificationType(Long id) {
        if (!repository.existsById(id)) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "Classification not found: " + id);
        }
        try {
            repository.deleteById(id);
            repository.flush();
        } catch (Exception e) {
            throw new org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.BAD_REQUEST,
                    "Cannot delete classification as it is currently in use by one or more customers.");
        }
    }

    private CustomerClassificationTypeDTO toDTO(CustomerClassificationTypeEntity e) {

        return new CustomerClassificationTypeDTO(
                e.getCustomerClassificationId(),
                e.getCustomerClassificationType(),
                e.getCustomerClassificationValue(),
                e.getEffectiveDate());
    }
}
