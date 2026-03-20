package com.example.first.controller;

import com.example.first.dto.CustomerClassificationTypeDTO;
import com.example.first.service.CustomerClassificationTypeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-classification")
public class CustomerClassificationTypeController {

    private final CustomerClassificationTypeService service;

    public CustomerClassificationTypeController(CustomerClassificationTypeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerClassificationTypeDTO> create(
            @Valid @RequestBody CustomerClassificationTypeDTO dto) {

        return ResponseEntity.ok(service.createCustomerClassificationType(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerClassificationTypeDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(service.getCustomerClassificationTypeById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerClassificationTypeDTO>> getAll() {

        return ResponseEntity.ok(service.getAllCustomerClassificationTypes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerClassificationTypeDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerClassificationTypeDTO dto) {

        return ResponseEntity.ok(service.updateCustomerClassificationType(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.deleteCustomerClassificationType(id);
        return ResponseEntity.noContent().build();
    }
}
