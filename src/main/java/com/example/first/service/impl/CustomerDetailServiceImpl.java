package com.example.first.service.impl;

import com.example.first.dto.CustomerDetailDTO;
import com.example.first.dto.PageResponseDTO;
import com.example.first.dto.BulkUploadResponseDTO;
import com.example.first.dto.BulkUploadErrorDTO;
import com.example.first.entity.CustomerDetailEntity;
import com.example.first.entity.CustomerClassificationTypeEntity;
import com.example.first.repository.CustomerDetailRepository;
import com.example.first.repository.CustomerClassificationTypeRepository;
import com.example.first.repository.CustomerAddresssRepository;
import com.example.first.repository.CustomerContactInformationRepository;
import com.example.first.repository.CustomerNamesRepository;
import com.example.first.repository.CustomerIdentificationRepository;
import com.example.first.repository.CustomerProofOfIdRepository;
import com.example.first.service.CustomerDetailService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import java.util.List;
import java.util.ArrayList;

@Service
@Transactional
public class CustomerDetailServiceImpl implements CustomerDetailService {

    private final CustomerDetailRepository repository;
    private final CustomerClassificationTypeRepository classificationTypeRepository;
    private final CustomerAddresssRepository addressRepository;
    private final CustomerContactInformationRepository contactInfoRepository;
    private final CustomerNamesRepository namesRepository;
    private final CustomerIdentificationRepository identificationRepository;
    private final CustomerProofOfIdRepository proofOfIdRepository;

    public CustomerDetailServiceImpl(CustomerDetailRepository repository,
            CustomerClassificationTypeRepository classificationTypeRepository,
            CustomerAddresssRepository addressRepository,
            CustomerContactInformationRepository contactInfoRepository,
            CustomerNamesRepository namesRepository,
            CustomerIdentificationRepository identificationRepository,
            CustomerProofOfIdRepository proofOfIdRepository) {
        this.repository = repository;
        this.classificationTypeRepository = classificationTypeRepository;
        this.addressRepository = addressRepository;
        this.contactInfoRepository = contactInfoRepository;
        this.namesRepository = namesRepository;
        this.identificationRepository = identificationRepository;
        this.proofOfIdRepository = proofOfIdRepository;
    }

    @Override
    public CustomerDetailDTO createCustomerDetail(CustomerDetailDTO dto) {
        CustomerDetailEntity entity = new CustomerDetailEntity();
        CustomerClassificationTypeEntity classificationType = classificationTypeRepository
                .findById(dto.getCustomerType())
                .orElseThrow(() -> new RuntimeException(
                        "Customer Classification Type not found with id: " + dto.getCustomerType()));
        entity.setCustomerFullName(dto.getCustomerFullName());
        entity.setCustomerGender(dto.getCustomerGender());
        entity.setCustomerType(classificationType);
        entity.setCustomerDateOfBirth(dto.getCustomerDateOfBirth());
        entity.setCustomerPreferredLanguage(dto.getCustomerPreferredLanguage());
        entity.setCustomerStatus(dto.getCustomerStatus());
        entity.setCustomerCountryOfOrigin(dto.getCustomerCountryOfOrigin());

        CustomerDetailEntity saved = repository.save(entity);

        return toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDetailDTO getCustomerDetailById(Long id) {

        CustomerDetailEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Detail not found with id: " + id));

        return toDTO(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponseDTO<CustomerDetailDTO> getAllCustomerDetails(int page, int size) {
        Page<CustomerDetailEntity> entityPage = repository.findAll(PageRequest.of(page, size));

        List<CustomerDetailDTO> content = entityPage.getContent()
                .stream()
                .map(this::toDTO)
                .toList();

        return new PageResponseDTO<>(
                content,
                entityPage.getTotalElements(),
                entityPage.getTotalPages(),
                entityPage.getSize(),
                entityPage.getNumber());
    }

    @Override
    @Transactional(readOnly = true)
    public long getCustomerCount() {
        return repository.count();
    }

    @Override
    public CustomerDetailDTO updateCustomerDetail(Long id, CustomerDetailDTO dto) {

        CustomerDetailEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer Detail not found with id: " + id));
        CustomerClassificationTypeEntity classificationType = classificationTypeRepository
                .findById(dto.getCustomerType())
                .orElseThrow(() -> new RuntimeException(
                        "Customer Classification Type not found with id: " + dto.getCustomerType()));
        entity.setCustomerFullName(dto.getCustomerFullName());
        entity.setCustomerGender(dto.getCustomerGender());
        entity.setCustomerType(classificationType);
        entity.setCustomerDateOfBirth(dto.getCustomerDateOfBirth());
        entity.setCustomerPreferredLanguage(dto.getCustomerPreferredLanguage());
        entity.setCustomerStatus(dto.getCustomerStatus());
        entity.setCustomerCountryOfOrigin(dto.getCustomerCountryOfOrigin());

        CustomerDetailEntity updated = repository.save(entity);

        return toDTO(updated);
    }

    public void deleteCustomerDetail(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Customer Detail not found with id: " + id);
        }

        // Delete all child records first to avoid FK constraint violations
        addressRepository.deleteByCustomerIdentifier_CustomerIdentifier(id);
        contactInfoRepository.deleteByCustomerIdentifier_CustomerIdentifier(id);
        namesRepository.deleteByCustomerIdentifier_CustomerIdentifier(id);
        identificationRepository.deleteByCustomerIdentifier_CustomerIdentifier(id);
        proofOfIdRepository.deleteByCustomerIdentifier_CustomerIdentifier(id);

        repository.deleteById(id);
    }

    private CustomerDetailDTO toDTO(CustomerDetailEntity entity) {
        CustomerDetailDTO dto = new CustomerDetailDTO();

        dto.setCustomerIdentifier(entity.getCustomerIdentifier());
        dto.setCustomerFullName(entity.getCustomerFullName());
        dto.setCustomerGender(entity.getCustomerGender());

        dto.setCustomerType(
                entity.getCustomerType().getCustomerClassificationId());

        dto.setCustomerDateOfBirth(entity.getCustomerDateOfBirth());
        dto.setCustomerPreferredLanguage(entity.getCustomerPreferredLanguage());
        dto.setCustomerStatus(entity.getCustomerStatus());
        dto.setCustomerCountryOfOrigin(entity.getCustomerCountryOfOrigin());

        return dto;
    }

    @Override
    public BulkUploadResponseDTO bulkUploadCustomers(List<CustomerDetailDTO> customers) {
        int totalRecords = customers.size();
        int successCount = 0;
        int failureCount = 0;
        List<BulkUploadErrorDTO> errors = new ArrayList<>();

        for (int i = 0; i < customers.size(); i++) {
            try {
                CustomerDetailDTO dto = customers.get(i);

                // Validate required fields
                if (dto.getCustomerFullName() == null || dto.getCustomerFullName().trim().isEmpty()) {
                    errors.add(new BulkUploadErrorDTO(i + 2, "Customer full name is required",
                            "Row " + (i + 2)));
                    failureCount++;
                    continue;
                }
                if (dto.getCustomerGender() == null || dto.getCustomerGender().trim().isEmpty()) {
                    errors.add(new BulkUploadErrorDTO(i + 2, "Customer gender is required",
                            "Row " + (i + 2)));
                    failureCount++;
                    continue;
                }
                if (dto.getCustomerType() == null) {
                    errors.add(new BulkUploadErrorDTO(i + 2, "Customer type is required",
                            "Row " + (i + 2)));
                    failureCount++;
                    continue;
                }
                if (dto.getCustomerDateOfBirth() == null) {
                    errors.add(new BulkUploadErrorDTO(i + 2, "Customer date of birth is required",
                            "Row " + (i + 2)));
                    failureCount++;
                    continue;
                }
                if (dto.getCustomerPreferredLanguage() == null || dto.getCustomerPreferredLanguage().trim().isEmpty()) {
                    errors.add(new BulkUploadErrorDTO(i + 2, "Customer preferred language is required",
                            "Row " + (i + 2)));
                    failureCount++;
                    continue;
                }
                if (dto.getCustomerStatus() == null || dto.getCustomerStatus().trim().isEmpty()) {
                    errors.add(new BulkUploadErrorDTO(i + 2, "Customer status is required",
                            "Row " + (i + 2)));
                    failureCount++;
                    continue;
                }
                if (dto.getCustomerCountryOfOrigin() == null || dto.getCustomerCountryOfOrigin().trim().isEmpty()) {
                    errors.add(new BulkUploadErrorDTO(i + 2, "Customer country of origin is required",
                            "Row " + (i + 2)));
                    failureCount++;
                    continue;
                }

                // Check if classification type exists
                CustomerClassificationTypeEntity classificationType = classificationTypeRepository
                        .findById(dto.getCustomerType())
                        .orElse(null);

                if (classificationType == null) {
                    errors.add(new BulkUploadErrorDTO(i + 2,
                            "Customer Classification Type not found with id: " + dto.getCustomerType(),
                            "Row " + (i + 2)));
                    failureCount++;
                    continue;
                }

                // Create and save customer
                CustomerDetailEntity entity = new CustomerDetailEntity();
                entity.setCustomerFullName(dto.getCustomerFullName());
                entity.setCustomerGender(dto.getCustomerGender());
                entity.setCustomerType(classificationType);
                entity.setCustomerDateOfBirth(dto.getCustomerDateOfBirth());
                entity.setCustomerPreferredLanguage(dto.getCustomerPreferredLanguage());
                entity.setCustomerStatus(dto.getCustomerStatus());
                entity.setCustomerCountryOfOrigin(dto.getCustomerCountryOfOrigin());

                repository.save(entity);
                successCount++;

            } catch (Exception e) {
                failureCount++;
                errors.add(new BulkUploadErrorDTO(i + 2, "Error: " + e.getMessage(),
                        "Row " + (i + 2)));
            }
        }

        String message = String.format("Bulk upload completed: %d success, %d failed out of %d records",
                successCount, failureCount, totalRecords);

        return new BulkUploadResponseDTO(totalRecords, successCount, failureCount, errors, message);
    }
}
