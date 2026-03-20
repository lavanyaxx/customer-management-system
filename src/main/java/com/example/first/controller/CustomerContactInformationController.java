package com.example.first.controller;
import com.example.first.dto.CustomerContactInformationDTO;
import com.example.first.service.CustomerContactInformationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer-contact-information")
public class CustomerContactInformationController {
    private final CustomerContactInformationService service;
    public CustomerContactInformationController(CustomerContactInformationService service) {
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<CustomerContactInformationDTO> create(
            @Valid @RequestBody CustomerContactInformationDTO dto) {
        return ResponseEntity.ok(service.createCustomerContactInformation(dto));
    }
    @GetMapping("/{id}")
    public ResponseEntity<CustomerContactInformationDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getCustomerContactInformationById(id));
    }
    @GetMapping
    public ResponseEntity<List<CustomerContactInformationDTO>> getAll() {
        return ResponseEntity.ok(service.getAllCustomerContactInformations());
    }
    @PutMapping("/{id}")
    public ResponseEntity<CustomerContactInformationDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerContactInformationDTO dto) {
        return ResponseEntity.ok(service.updateCustomerContactInformation(id, dto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCustomerContactInformation(id);
        return ResponseEntity.noContent().build();
    }

}
