package com.example.first.controller;

import com.example.first.dto.CustomerAddressDTO;
import com.example.first.service.CustomerAddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/customer-address")
public class CustomerAddressController {
    public final CustomerAddressService service;
    public CustomerAddressController(CustomerAddressService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<CustomerAddressDTO> create(
            @Valid @RequestBody CustomerAddressDTO dto) {
        return ResponseEntity.ok(service.createCustomerAddress(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerAddressDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCustomerAddressById(id));
    }
    @GetMapping
    public ResponseEntity<List<CustomerAddressDTO>> getAll() {
        return ResponseEntity.ok(service.getAllCustomerAddresses());
    }
    @GetMapping("/by-customer/{customerId}")
    public ResponseEntity<List<CustomerAddressDTO>> getByCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(service.getAddressesByCustomerId(customerId));
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerAddressDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerAddressDTO dto) {
        return ResponseEntity.ok(service.updateCustomerAddress(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCustomerAddress(id);
        return ResponseEntity.noContent().build();
    }
}