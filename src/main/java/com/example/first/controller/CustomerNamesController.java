package com.example.first.controller;

import com.example.first.dto.CustomerNamesDTO;
import com.example.first.service.CustomerNamesService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer-names")
public class CustomerNamesController {
    private final CustomerNamesService service;

    public CustomerNamesController(CustomerNamesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerNamesDTO> createCustomerName(@Valid @RequestBody CustomerNamesDTO dto) {
        CustomerNamesDTO created = service.createCustomerName(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerNamesDTO> getCustomerNameById(@PathVariable Long id) {
        CustomerNamesDTO dto = service.getCustomerNameById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerNamesDTO>> getAllCustomerNames() {
        List<CustomerNamesDTO> dtos = service.getAllCustomerNames();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerNamesDTO> updateCustomerName(@PathVariable Long id, @Valid @RequestBody CustomerNamesDTO dto) {
        CustomerNamesDTO updated = service.updateCustomerName(id, dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerName(@PathVariable Long id) {
        service.deleteCustomerName(id);
        return ResponseEntity.noContent().build();
    }
}
