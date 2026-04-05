import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-customers',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="row mb-4">
      <div class="col-12">
        <h1 class="mb-2">Customers</h1>
        <p class="text-muted">Manage customer information</p>
      </div>
    </div>

    <div class="row" *ngIf="!showForm && !showBulkUpload && !showAddressSection; else displayForm">
      <div class="col-12">
        <div class="card">
          <div class="card-header d-flex justify-content-between align-items-center">
            <span><i class="bi bi-people"></i> Customers List</span>
            <div>
              <button class="btn btn-sm btn-primary me-2" (click)="toggleForm()">
                <i class="bi bi-plus"></i> Add Customer
              </button>
              <button class="btn btn-sm btn-success me-2" (click)="toggleAddressSection()">
                <i class="bi bi-geo-alt-fill"></i> Add Address
              </button>
              <button class="btn btn-sm btn-info" (click)="toggleBulkUpload()">
                <i class="bi bi-upload"></i> Bulk Upload
              </button>
            </div>
          </div>
          <div class="card-body">
            <div *ngIf="successMessage" class="alert alert-success fade-in">
              <i class="bi bi-check-circle"></i> {{ successMessage }}
            </div>
            <div *ngIf="errorMessage" class="alert alert-danger fade-in">
              <i class="bi bi-exclamation-circle"></i> {{ errorMessage }}
            </div>

            <div *ngIf="customers.length === 0" class="empty-state">
              <div class="empty-state-icon">👥</div>
              <div class="empty-state-text">No customers found</div>
              <button class="btn btn-sm btn-primary" (click)="toggleForm()">
                Register First Customer
              </button>
            </div>

            <table class="table table-striped table-hover" *ngIf="customers.length > 0">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Full Name</th>
                  <th>Gender</th>
                  <th>Status</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let customer of customers" class="fade-in">
                  <td><span class="badge badge-info">{{ customer.customerIdentifier }}</span></td>
                  <td>{{ customer.customerFullName }}</td>
                  <td>{{ customer.customerGender }}</td>
                  <td>
                    <span class="badge" [ngClass]="customer.customerStatus === 'ACTIVE' ? 'bg-success' : 'bg-warning'">
                      {{ customer.customerStatus }}
                    </span>
                  </td>
                  <td>
                    <button class="btn btn-sm btn-warning" (click)="edit(customer)">
                      <i class="bi bi-pencil"></i> Edit
                    </button>
                    <button class="btn btn-sm btn-danger ms-2" (click)="delete(customer.customerIdentifier!)">
                      <i class="bi bi-trash"></i> Delete
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>

            <!-- Pagination Controls -->
            <div *ngIf="customers.length > 0" class="d-flex justify-content-between align-items-center mt-3">
              <div class="text-muted small">
                Showing {{ currentPage * pageSize + 1 }} to {{ Math.min((currentPage + 1) * pageSize, totalElements) }} of {{ totalElements }} customers
              </div>
              <nav aria-label="Customer list pagination">
                <ul class="pagination pagination-sm mb-0">
                  <li class="page-item" [class.disabled]="currentPage === 0">
                    <button class="page-link" (click)="onPageChange(currentPage - 1)" [disabled]="currentPage === 0">
                      <i class="bi bi-chevron-left"></i> Previous
                    </button>
                  </li>
                  <li class="page-item disabled">
                    <span class="page-link text-dark">Page {{ currentPage + 1 }} of {{ totalPages }}</span>
                  </li>
                  <li class="page-item" [class.disabled]="currentPage >= totalPages - 1">
                    <button class="page-link" (click)="onPageChange(currentPage + 1)" [disabled]="currentPage >= totalPages - 1">
                      Next <i class="bi bi-chevron-right"></i>
                    </button>
                  </li>
                </ul>
              </nav>
              <div class="d-flex align-items-center">
                <span class="small text-muted me-2">Rows per page:</span>
                <select class="form-select form-select-sm" style="width: auto;" [(ngModel)]="pageSize" (change)="onPageSizeChange()">
                  <option [ngValue]="5">5</option>
                  <option [ngValue]="10">10</option>
                  <option [ngValue]="20">20</option>
                  <option [ngValue]="50">50</option>
                </select>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <ng-template #bulkUploadSection>
      <div class="row">
        <div class="col-12">
          <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
              <span><i class="bi bi-file-earmark-csv"></i> Bulk Customer Upload</span>
              <button class="btn btn-sm btn-secondary" (click)="toggleBulkUpload()">
                <i class="bi bi-x"></i> Cancel
              </button>
            </div>
            <div class="card-body">
              <!-- Upload Step -->
              <div *ngIf="!csvData.length" class="mb-4">
                <div class="mb-3">
                  <label for="csvFile" class="form-label">Upload CSV File *</label>
                  <input 
                    type="file" 
                    class="form-control" 
                    id="csvFile" 
                    accept=".csv"
                    (change)="onFileSelected($event)">
                  <small class="text-muted">Upload a CSV file with columns: customerFullName, customerGender, customerType, customerDateOfBirth, customerPreferredLanguage, customerStatus, customerCountryOfOrigin</small>
                </div>
                <div *ngIf="errorMessage" class="alert alert-danger fade-in">
                  <i class="bi bi-exclamation-circle"></i> {{ errorMessage }}
                </div>
              </div>

              <!-- Preview Step -->
              <div *ngIf="csvData.length > 0">
                <div class="mb-3">
                  <h5>Preview Data ({{ csvData.length }} records)</h5>
                  <div class="table-responsive">
                    <table class="table table-sm table-striped">
                      <thead>
                        <tr>
                          <th>Full Name</th>
                          <th>Gender</th>
                          <th>Type</th>
                          <th>DOB</th>
                          <th>Language</th>
                          <th>Status</th>
                          <th>Country</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr *ngFor="let row of csvData.slice(0, 5)">
                          <td>{{ row.customerFullName }}</td>
                          <td>{{ row.customerGender }}</td>
                          <td>{{ row.customerType }}</td>
                          <td>{{ row.customerDateOfBirth }}</td>
                          <td>{{ row.customerPreferredLanguage }}</td>
                          <td>{{ row.customerStatus }}</td>
                          <td>{{ row.customerCountryOfOrigin }}</td>
                        </tr>
                        <tr *ngIf="csvData.length > 5">
                          <td colspan="7" class="text-center text-muted">... and {{ csvData.length - 5 }} more records</td>
                        </tr>
                      </tbody>
                    </table>
                  </div>
                </div>

                <!-- Upload Results -->
                <div *ngIf="uploadResults">
                  <div class="alert" [ngClass]="uploadResults.failureCount === 0 ? 'alert-success' : 'alert-warning'">
                    <h5><i class="bi bi-check-circle"></i> Upload Summary</h5>
                    <p class="mb-0">
                      <strong>Total:</strong> {{ uploadResults.totalRecords }} | 
                      <strong class="text-success">Success:</strong> {{ uploadResults.successCount }} | 
                      <strong class="text-danger">Failed:</strong> {{ uploadResults.failureCount }}
                    </p>
                    <small class="text-muted d-block mt-2">{{ uploadResults.message }}</small>
                  </div>

                  <div *ngIf="uploadResults.errors && uploadResults.errors.length > 0" class="mb-3">
                    <h6>Errors:</h6>
                    <div class="alert alert-danger" *ngFor="let error of uploadResults.errors">
                      <strong>Row {{ error.rowNumber }}:</strong> {{ error.errorMessage }}
                    </div>
                  </div>
                </div>

                <!-- Action Buttons -->
                <div class="d-grid gap-2 d-md-flex justify-content-md-between">
                  <button class="btn btn-secondary" (click)="clearCsvData()">
                    <i class="bi bi-arrow-clockwise"></i> Choose Different File
                  </button>
                  <button type="button" class="btn btn-success" (click)="processBulkUpload()" [disabled]="isSubmitting || uploadResults">
                    <span *ngIf="!isSubmitting"><i class="bi bi-cloud-upload"></i> Upload {{ csvData.length }} Customers</span>
                    <span *ngIf="isSubmitting"><span class="spinner-border spinner-border-sm me-2"></span>Uploading...</span>
                  </button>
                </div>

                <div *ngIf="uploadResults" class="mt-3 text-center">
                  <button class="btn btn-primary" (click)="finalizeBulkUpload()">
                    <i class="bi bi-check"></i> Done
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </ng-template>

    <!-- Address Management Section (standalone, selected from all customers) -->
    <ng-template #addressSection>
      <div class="row">
        <div class="col-md-10 offset-md-1">
          <div class="card mb-4">
            <div class="card-header d-flex justify-content-between align-items-center">
              <span><i class="bi bi-geo-alt-fill"></i> Manage Customer Addresses</span>
              <button class="btn btn-sm btn-secondary" (click)="toggleAddressSection()">
                <i class="bi bi-x"></i> Close
              </button>
            </div>
            <div class="card-body">
              <!-- Customer selector -->
              <div class="mb-4">
                <label for="addrCustomerSelect" class="form-label fw-bold">Select Customer *</label>
                <select class="form-select" id="addrCustomerSelect"
                  [(ngModel)]="selectedAddressCustomerId"
                  (ngModelChange)="onAddressCustomerChange($event)"
                  name="addrCustomerSelect">
                  <option [ngValue]="null">-- Select a customer --</option>
                  <option *ngFor="let c of customers" [ngValue]="c.customerIdentifier">
                    #{{ c.customerIdentifier }} – {{ c.customerFullName }}
                  </option>
                </select>
              </div>

              <!-- Address list for selected customer -->
              <div *ngIf="selectedAddressCustomerId">
                <div *ngIf="addressMessage" class="alert alert-success fade-in mb-3">
                  <i class="bi bi-check-circle"></i> {{ addressMessage }}
                </div>
                <div *ngIf="addressErrorMessage" class="alert alert-danger fade-in mb-3">
                  <i class="bi bi-exclamation-circle"></i> {{ addressErrorMessage }}
                </div>

                <!-- Add new address form -->
                <div *ngIf="showAddressForm" class="mb-4 p-3 bg-light rounded border">
                  <h6><i class="bi bi-plus-circle"></i> New Address</h6>
                  <form (ngSubmit)="submitAddress()" *ngIf="addressFormData">
                    <div class="mb-3">
                      <label class="form-label">Address Type *</label>
                      <select class="form-select" [(ngModel)]="addressFormData.customerAddressType" name="addressType" required>
                        <option value="">-- Select Type --</option>
                        <option value="HOME">Home</option>
                        <option value="WORK">Work</option>
                        <option value="BILLING">Billing</option>
                        <option value="SHIPPING">Shipping</option>
                        <option value="OTHER">Other</option>
                      </select>
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Address *</label>
                      <textarea class="form-control" [(ngModel)]="addressFormData.customerAddressValue" name="addressValue" rows="3" placeholder="Enter full address" required></textarea>
                    </div>
                    <div class="mb-3">
                      <label class="form-label">Effective Date *</label>
                      <input type="date" [max]="today" class="form-control" [(ngModel)]="addressFormData.effectiveDate" name="addrEffectiveDate" required>
                    </div>
                    <div class="d-flex gap-2">
                      <button type="submit" class="btn btn-success btn-sm" [disabled]="isSubmittingAddress">
                        <span *ngIf="!isSubmittingAddress"><i class="bi bi-check"></i> Save Address</span>
                        <span *ngIf="isSubmittingAddress"><span class="spinner-border spinner-border-sm me-1"></span>Saving...</span>
                      </button>
                      <button type="button" class="btn btn-secondary btn-sm" (click)="cancelAddressForm()">Cancel</button>
                    </div>
                  </form>
                </div>

                <!-- Add address button -->
                <div class="d-flex justify-content-between align-items-center mb-3" *ngIf="!showAddressForm">
                  <h6 class="mb-0">Addresses for selected customer</h6>
                  <button class="btn btn-sm btn-primary" (click)="openAddressForm()">
                    <i class="bi bi-plus"></i> Add New Address
                  </button>
                </div>

                <!-- Addresses list -->
                <div *ngIf="customerAddresses.length === 0 && !showAddressForm" class="text-center py-4 text-muted">
                  <i class="bi bi-info-circle"></i> No addresses found for this customer
                </div>
                <div *ngIf="customerAddresses.length > 0" class="list-group">
                  <div *ngFor="let addr of customerAddresses" class="list-group-item">
                    <div class="d-flex justify-content-between align-items-start">
                      <div>
                        <span class="badge bg-info me-2">{{ addr.customerAddressType }}</span>
                        <small class="text-muted">Effective: {{ addr.effectiveDate }}</small>
                        <p class="mb-0 mt-1">{{ addr.customerAddressValue }}</p>
                      </div>
                      <button class="btn btn-sm btn-danger" (click)="deleteAddress(addr.addressId)">
                        <i class="bi bi-trash"></i>
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </ng-template>

    <ng-template #displayForm>
      <ng-container *ngIf="showForm; else checkBulkOrAddress">
        <ng-container *ngTemplateOutlet="formSection"></ng-container>
      </ng-container>
    </ng-template>
    <ng-template #checkBulkOrAddress>
      <ng-container *ngIf="showBulkUpload; else addressSection">
        <ng-container *ngTemplateOutlet="bulkUploadSection"></ng-container>
      </ng-container>
    </ng-template>

    <ng-template #formSection>
      <div class="row">
        <div class="col-md-10 offset-md-1">
          <!-- Customer Form Card -->
          <div class="card mb-4">
            <div class="card-header d-flex justify-content-between align-items-center">
              <span *ngIf="!editingId"><i class="bi bi-person-plus"></i> Add New Customer</span>
              <span *ngIf="editingId"><i class="bi bi-pencil"></i> Edit Customer</span>
              <button class="btn btn-sm btn-secondary" (click)="toggleForm()">
                <i class="bi bi-x"></i> Cancel
              </button>
            </div>
            <div class="card-body">
              <form (ngSubmit)="submit()" *ngIf="formData">
                <div class="mb-3">
                  <label for="customerFullName" class="form-label">Full Name *</label>
                  <input
                    type="text"
                    class="form-control"
                    id="customerFullName"
                    [(ngModel)]="formData.customerFullName"
                    name="customerFullName"
                    placeholder="Customer full name"
                    required>
                </div>
                <div class="mb-3">
                  <label for="customerGender" class="form-label">Gender *</label>
                  <select
                    class="form-select"
                    id="customerGender"
                    [(ngModel)]="formData.customerGender"
                    name="customerGender"
                    required>
                    <option value="">-- Select Gender --</option>
                    <option value="MALE">Male</option>
                    <option value="FEMALE">Female</option>
                    <option value="OTHER">Other</option>
                  </select>
                </div>
                <div class="mb-3">
                  <label for="customerDateOfBirth" class="form-label">Date of Birth *</label>
                  <input
                    type="date"
                    [max]="today"
                    class="form-control"
                    id="customerDateOfBirth"
                    [(ngModel)]="formData.customerDateOfBirth"
                    name="customerDateOfBirth"
                    required>
                </div>
                <div class="mb-3">
                  <label for="customerPreferredLanguage" class="form-label">Preferred Language *</label>
                  <input
                    type="text"
                    class="form-control"
                    id="customerPreferredLanguage"
                    [(ngModel)]="formData.customerPreferredLanguage"
                    name="customerPreferredLanguage"
                    placeholder="e.g., English, Spanish"
                    required>
                </div>
                <div class="mb-3">
                  <label for="customerStatus" class="form-label">Status *</label>
                  <select
                    class="form-select"
                    id="customerStatus"
                    [(ngModel)]="formData.customerStatus"
                    name="customerStatus"
                    required>
                    <option value="">-- Select Status --</option>
                    <option value="ACTIVE">Active</option>
                    <option value="INACTIVE">Inactive</option>
                  </select>
                </div>
                <div class="mb-3">
                  <label for="customerCountryOfOrigin" class="form-label">Country of Origin *</label>
                  <input
                    type="text"
                    class="form-control"
                    id="customerCountryOfOrigin"
                    [(ngModel)]="formData.customerCountryOfOrigin"
                    name="customerCountryOfOrigin"
                    placeholder="e.g., United States"
                    required>
                </div>
                <div class="mb-3">
                  <label for="customerType" class="form-label">Customer Type *</label>
                  <input
                    type="number"
                    class="form-control"
                    id="customerType"
                    [(ngModel)]="formData.customerType"
                    name="customerType"
                    placeholder="Customer type ID"
                    required>
                </div>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                  <button type="reset" class="btn btn-secondary">Clear</button>
                  <button type="submit" class="btn btn-success" [disabled]="isSubmitting">
                    <span *ngIf="!isSubmitting && !editingId"><i class="bi bi-check"></i> Save Customer</span>
                    <span *ngIf="!isSubmitting && editingId"><i class="bi bi-check"></i> Update Customer</span>
                    <span *ngIf="isSubmitting"><span class="spinner-border spinner-border-sm me-2"></span>{{ editingId ? 'Updating...' : 'Saving...' }}</span>
                  </button>
                </div>
              </form>
            </div>
          </div>


        </div>
      </div>
    </ng-template>
  `,
  styles: []
})
export class CustomersComponent implements OnInit {
  customers: any[] = [];
  formData: any = null;
  showForm = false;
  editingId: number | null = null;
  successMessage = '';
  errorMessage = '';
  isSubmitting = false;
  showBulkUpload = false;
  csvData: any[] = [];
  uploadResults: any = null;
  Math = Math; // To use in template
  today: string = new Date().toISOString().split('T')[0];

  // Pagination properties
  currentPage = 0;
  pageSize = 10;
  totalElements = 0;
  totalPages = 0;

  // Address management properties
  customerAddresses: any[] = [];
  addressFormData: any = null;
  showAddressForm = false;
  addressMessage = '';
  addressErrorMessage = '';
  isSubmittingAddress = false;
  editingAddressId: number | null = null;
  showAddressSection = false;
  selectedAddressCustomerId: number | null = null;

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.loadCustomers();
    this.apiService.refresh$.subscribe(() => {
      this.loadCustomers();
    });
  }

  loadCustomers() {
    this.apiService.getCustomerDetails(this.currentPage, this.pageSize).subscribe({
      next: (response) => {
        this.customers = response.content || [];
        this.totalElements = response.totalElements || 0;
        this.totalPages = response.totalPages || 0;
        this.clearMessages();
      },
      error: (err) => {
        this.errorMessage = 'Failed to load customers';
        console.error(err);
      }
    });
  }

  onPageChange(page: number) {
    if (page >= 0 && page < this.totalPages) {
      this.currentPage = page;
      this.loadCustomers();
    }
  }

  onPageSizeChange() {
    this.currentPage = 0;
    this.loadCustomers();
  }

  toggleForm() {
    this.showForm = !this.showForm;
    if (this.showForm) {
      if (!this.editingId) {
        this.formData = {
          customerFullName: '',
          customerGender: 'MALE',
          customerDateOfBirth: new Date().toISOString().split('T')[0],
          customerPreferredLanguage: 'English',
          customerStatus: 'ACTIVE',
          customerCountryOfOrigin: '',
          customerType: 1
        };
        this.customerAddresses = [];
        this.showAddressForm = false;
      }
      this.clearMessages();
    } else {
      this.editingId = null;
      this.formData = null;
      this.customerAddresses = [];
      this.showAddressForm = false;
      this.clearAddressMessages();
    }
  }

  submit() {
    if (!this.formData.customerFullName.trim()) {
      this.errorMessage = 'Please enter customer full name';
      return;
    }
    if (!this.formData.customerGender) {
      this.errorMessage = 'Please select a gender';
      return;
    }

    this.isSubmitting = true;

    if (this.editingId) {
      // Update existing customer
      this.apiService.updateCustomerDetail(this.editingId, this.formData).subscribe({
        next: (response) => {
          this.successMessage = 'Customer updated successfully!';
          this.showForm = false;
          this.editingId = null;
          this.formData = null;
          this.customerAddresses = [];
          this.showAddressForm = false;
          this.isSubmitting = false;
          this.loadCustomers();
        },
        error: (err) => {
          this.errorMessage = 'Failed to update customer: ' + (err.error?.message || err.message);
          this.isSubmitting = false;
          console.error(err);
        }
      });
    } else {
      // Create new customer
      this.apiService.createCustomerDetail(this.formData).subscribe({
        next: (response) => {
          this.successMessage = 'Customer added successfully!';
          this.showForm = false;
          this.formData = null;
          this.isSubmitting = false;
          this.loadCustomers();
        },
        error: (err) => {
          this.errorMessage = 'Failed to add customer: ' + (err.error?.message || err.message);
          this.isSubmitting = false;
          console.error(err);
        }
      });
    }
  }

  edit(customer: any) {
    this.editingId = customer.customerIdentifier;
    this.formData = { ...customer };
    this.showForm = true;
    this.showAddressForm = false;
    this.customerAddresses = [];
    this.editingAddressId = null;
    this.clearMessages();
    this.loadCustomerAddresses();
  }

  delete(id: number) {
    if (confirm('Are you sure you want to delete this customer?')) {
      this.apiService.deleteCustomerDetail(id).subscribe({
        next: () => {
          this.successMessage = 'Customer deleted successfully!';
          this.loadCustomers();
        },
        error: (err) => {
          this.errorMessage = 'Failed to delete customer: ' + (err.error?.message || err.message);
          console.error(err);
        }
      });
    }
  }

  // Address Management Methods
  loadCustomerAddresses() {
    const id = this.selectedAddressCustomerId || this.editingId;
    if (!id) return;

    this.apiService.getAddressesByCustomer(id).subscribe({
      next: (data) => {
        this.customerAddresses = data || [];
        this.clearAddressMessages();
      },
      error: (err) => {
        this.addressErrorMessage = 'Failed to load addresses';
        console.error(err);
        this.customerAddresses = [];
      }
    });
  }

  toggleAddressSection() {
    this.showAddressSection = !this.showAddressSection;
    if (!this.showAddressSection) {
      this.selectedAddressCustomerId = null;
      this.customerAddresses = [];
      this.showAddressForm = false;
      this.addressFormData = null;
      this.clearAddressMessages();
    } else {
      this.clearAddressMessages();
    }
  }

  onAddressCustomerChange(customerId: number) {
    this.selectedAddressCustomerId = customerId;
    this.customerAddresses = [];
    this.showAddressForm = false;
    this.addressFormData = null;
    this.clearAddressMessages();
    if (customerId) {
      this.loadCustomerAddresses();
    }
  }

  openAddressForm() {
    this.showAddressForm = true;
    this.addressFormData = {
      customerAddressType: '',
      customerAddressValue: '',
      effectiveDate: new Date().toISOString().split('T')[0]
    };
    this.clearAddressMessages();
  }

  cancelAddressForm() {
    this.showAddressForm = false;
    this.addressFormData = null;
    this.clearAddressMessages();
  }

  toggleAddressForm() {
    this.showAddressForm = !this.showAddressForm;
    if (this.showAddressForm) {
      this.addressFormData = {
        customerAddressType: '',
        customerAddressValue: '',
        effectiveDate: new Date().toISOString().split('T')[0]
      };
      this.editingAddressId = null;
      this.clearAddressMessages();
    } else {
      this.addressFormData = null;
      this.editingAddressId = null;
    }
  }

  submitAddress() {
    const customerId = this.selectedAddressCustomerId || this.editingId;
    if (!customerId) {
      this.addressErrorMessage = 'Please select a customer first';
      return;
    }

    if (!this.addressFormData.customerAddressType) {
      this.addressErrorMessage = 'Please select an address type';
      return;
    }

    if (!this.addressFormData.customerAddressValue?.trim()) {
      this.addressErrorMessage = 'Please enter an address';
      return;
    }

    this.isSubmittingAddress = true;
    const addressData = {
      ...this.addressFormData,
      customerIdentifier: customerId
    };

    this.apiService.createCustomerAddress(addressData).subscribe({
      next: () => {
        this.addressMessage = 'Address added successfully!';
        this.showAddressForm = false;
        this.addressFormData = null;
        this.isSubmittingAddress = false;
        this.loadCustomerAddresses();
      },
      error: (err) => {
        this.addressErrorMessage = 'Failed to add address: ' + (err.error?.message || err.message);
        this.isSubmittingAddress = false;
        console.error(err);
      }
    });
  }

  editAddress(address: any) {
    this.editingAddressId = address.addressId;
    this.addressFormData = {
      customerAddressType: address.customerAddressType,
      customerAddressValue: address.customerAddressValue,
      effectiveDate: address.effectiveDate
    };
    this.showAddressForm = true;
    this.clearAddressMessages();
  }

  deleteAddress(addressId: number) {
    if (confirm('Are you sure you want to delete this address?')) {
      this.apiService.deleteCustomerAddress(addressId).subscribe({
        next: () => {
          this.addressMessage = 'Address deleted successfully!';
          this.loadCustomerAddresses();
        },
        error: (err) => {
          this.addressErrorMessage = 'Failed to delete address: ' + (err.error?.message || err.message);
          console.error(err);
        }
      });
    }
  }

  clearMessages() {
    this.successMessage = '';
    this.errorMessage = '';
  }

  clearAddressMessages() {
    this.addressMessage = '';
    this.addressErrorMessage = '';
  }

  toggleBulkUpload() {
    this.showBulkUpload = !this.showBulkUpload;
    if (!this.showBulkUpload) {
      this.clearCsvData();
    }
  }

  onFileSelected(event: any) {
    const file: File = event.target.files[0];
    if (!file) return;

    if (!file.name.endsWith('.csv')) {
      this.errorMessage = 'Please upload a valid CSV file';
      return;
    }

    const reader = new FileReader();
    reader.onload = (e: any) => {
      try {
        const csv = e.target.result;
        this.parseCsv(csv);
      } catch (error: any) {
        this.errorMessage = 'Error reading file: ' + error.message;
      }
    };
    reader.readAsText(file);
  }

  parseCsv(csv: string) {
    const lines = csv.split('\n').filter(line => line.trim());
    if (lines.length < 2) {
      this.errorMessage = 'CSV file must have header and at least one data row';
      return;
    }

    const headers = lines[0].split(',').map(h => h.trim());
    const expectedHeaders = ['customerFullName', 'customerGender', 'customerType',
      'customerDateOfBirth', 'customerPreferredLanguage',
      'customerStatus', 'customerCountryOfOrigin'];

    // Validate headers (allow any order)
    const hasAllHeaders = expectedHeaders.every(h => headers.includes(h));
    if (!hasAllHeaders) {
      this.errorMessage = `CSV must have headers: ${expectedHeaders.join(', ')}`;
      return;
    }

    // Parse data rows
    this.csvData = [];
    for (let i = 1; i < lines.length; i++) {
      const values = lines[i].split(',').map(v => v.trim());
      const row: any = {};

      headers.forEach((header, index) => {
        row[header] = values[index] || '';
      });

      // Convert customerType to number
      if (row.customerType) {
        row.customerType = parseInt(row.customerType, 10);
      }

      this.csvData.push(row);
    }

    this.errorMessage = '';
    this.successMessage = `Loaded ${this.csvData.length} records from CSV`;
  }

  clearCsvData() {
    this.csvData = [];
    this.uploadResults = null;
    this.errorMessage = '';
    this.successMessage = '';
    // Reset file input
    const fileInput = document.getElementById('csvFile') as HTMLInputElement;
    if (fileInput) {
      fileInput.value = '';
    }
  }

  processBulkUpload() {
    if (!this.csvData.length) {
      this.errorMessage = 'No data to upload';
      return;
    }

    this.isSubmitting = true;
    this.apiService.bulkUploadCustomers(this.csvData).subscribe({
      next: (response) => {
        this.uploadResults = response;
        this.isSubmitting = false;
        this.successMessage = response.message;
      },
      error: (err) => {
        this.errorMessage = 'Bulk upload failed: ' + (err.error?.message || err.message);
        this.isSubmitting = false;
      }
    });
  }

  finalizeBulkUpload() {
    this.showBulkUpload = false;
    this.clearCsvData();
    this.loadCustomers();
  }
}
