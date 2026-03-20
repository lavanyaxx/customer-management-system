package com.example.first.controller;

import com.example.first.dto.CustomerIdentificationDTO;
import com.example.first.service.CustomerIdentificationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/customer-identification")
public class CustomerIdentificationController {
    private final CustomerIdentificationService service;

    public CustomerIdentificationController(CustomerIdentificationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerIdentificationDTO> createCustomerIdentification(@Valid @RequestBody CustomerIdentificationDTO dto) {
        CustomerIdentificationDTO created = service.createCustomerIdentification(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerIdentificationDTO> getCustomerIdentificationById(@PathVariable Long id) {
        CustomerIdentificationDTO dto = service.getCustomerIdentificationById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerIdentificationDTO>> getAllCustomerIdentifications() {
        List<CustomerIdentificationDTO> dtos = service.getAllCustomerIdentifications();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerIdentificationDTO> updateCustomerIdentification(@PathVariable Long id, @Valid @RequestBody CustomerIdentificationDTO dto) {
        CustomerIdentificationDTO updated = service.updateCustomerIdentification(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerIdentification(@PathVariable Long id) {
        service.deleteCustomerIdentification(id);
        return ResponseEntity.noContent().build();
    }
}