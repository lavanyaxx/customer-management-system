package com.example.first.controller;

import com.example.first.dto.CustomerProofOfIdDTO;
import com.example.first.service.CustomerProofOfIdService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/customer-proof-of-id")
public class CustomerProofOfIdController {
    private final CustomerProofOfIdService service;

    public CustomerProofOfIdController(CustomerProofOfIdService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerProofOfIdDTO> createCustomerProofOfId(@Valid @RequestBody CustomerProofOfIdDTO dto) {
        CustomerProofOfIdDTO created = service.createCustomerProofOfId(dto);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerProofOfIdDTO> getCustomerProofOfIdById(@PathVariable Long id) {
        CustomerProofOfIdDTO dto = service.getCustomerProofOfIdById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<CustomerProofOfIdDTO>> getAllCustomerProofOfIds() {
        List<CustomerProofOfIdDTO> dtos = service.getAllCustomerProofOfIds();
        return ResponseEntity.ok(dtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerProofOfIdDTO> updateCustomerProofOfId(@PathVariable Long id, @Valid @RequestBody CustomerProofOfIdDTO dto) {
        CustomerProofOfIdDTO updated = service.updateCustomerProofOfId(id, dto);
        return ResponseEntity.ok(updated);
    }
}
