package com.example.first.controller;
import com.example.first.dto.CustomerDetailDTO;
import com.example.first.dto.BulkUploadResponseDTO;
import com.example.first.service.CustomerDetailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/customer-detail")
public class CustomerDetailController {

    private final CustomerDetailService service;

    public CustomerDetailController(CustomerDetailService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CustomerDetailDTO> create(
            @Valid @RequestBody CustomerDetailDTO dto) {

        return ResponseEntity.ok(service.createCustomerDetail(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDetailDTO> getById(@PathVariable Long id) {

        return ResponseEntity.ok(service.getCustomerDetailById(id));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDetailDTO>> getAll() {

        return ResponseEntity.ok(service.getAllCustomerDetails());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDetailDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody CustomerDetailDTO dto) {

        return ResponseEntity.ok(service.updateCustomerDetail(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.deleteCustomerDetail(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/bulk-upload")
    public ResponseEntity<BulkUploadResponseDTO> bulkUpload(@RequestBody List<CustomerDetailDTO> customers) {
        BulkUploadResponseDTO response = service.bulkUploadCustomers(customers);
        return ResponseEntity.ok(response);
    }
}