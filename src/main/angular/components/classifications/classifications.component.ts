import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-classifications',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <div class="row mb-4">
      <div class="col-12">
        <h1 class="mb-2">Customer Classifications</h1>
        <p class="text-muted">Manage customer classification types</p>
      </div>
    </div>

    <div class="row" *ngIf="!showForm; else formSection">
      <div class="col-12">
        <div class="card">
          <div class="card-header d-flex justify-content-between align-items-center">
            <span><i class="bi bi-tags"></i> Classifications List</span>
            <button class="btn btn-sm btn-primary" (click)="toggleForm()">
              <i class="bi bi-plus"></i> Add Classification
            </button>
          </div>
          <div class="card-body">
            <div *ngIf="successMessage" class="alert alert-success fade-in">
              <i class="bi bi-check-circle"></i> {{ successMessage }}
            </div>
            <div *ngIf="errorMessage" class="alert alert-danger fade-in">
              <i class="bi bi-exclamation-circle"></i> {{ errorMessage }}
            </div>

            <div *ngIf="classifications.length === 0" class="empty-state">
              <div class="empty-state-icon">📭</div>
              <div class="empty-state-text">No classifications found</div>
              <button class="btn btn-sm btn-primary" (click)="toggleForm()">
                Create First Classification
              </button>
            </div>

            <table class="table table-striped table-hover" *ngIf="classifications.length > 0">
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Type</th>
                  <th>Value</th>
                  <th>Effective Date</th>
                  <th>Actions</th>
                </tr>
              </thead>
              <tbody>
                <tr *ngFor="let classification of classifications" class="fade-in">
                  <td><span class="badge badge-info">{{ classification.classificationId }}</span></td>
                  <td>{{ classification.classificationType }}</td>
                  <td>{{ classification.classificationValue }}</td>
                  <td>{{ classification.effectiveDate }}</td>
                  <td>
                    <button class="btn btn-sm btn-warning" (click)="edit(classification)">
                      <i class="bi bi-pencil"></i> Edit
                    </button>
                    <button class="btn btn-sm btn-danger ms-2" (click)="delete(classification.classificationId!)">
                      <i class="bi bi-trash"></i> Delete
                    </button>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>

    <ng-template #formSection>
      <div class="row">
        <div class="col-md-8 offset-md-2">
          <div class="card">
            <div class="card-header d-flex justify-content-between align-items-center">
              <span *ngIf="!editingId"><i class="bi bi-plus-circle"></i> Add New Classification</span>
              <span *ngIf="editingId"><i class="bi bi-pencil"></i> Edit Classification</span>
              <button class="btn btn-sm btn-secondary" (click)="toggleForm()">
                <i class="bi bi-x"></i> Cancel
              </button>
            </div>
            <div class="card-body">
              <form (ngSubmit)="submit()" *ngIf="formData">
                <div class="mb-3">
                  <label for="classificationType" class="form-label">Classification Type *</label>
                  <input
                    type="text"
                    class="form-control"
                    id="classificationType"
                    [(ngModel)]="formData.classificationType"
                    name="classificationType"
                    placeholder="e.g., PremiumStatus"
                    required>
                  <small class="text-muted">Enter the classification type</small>
                </div>
                <div class="mb-3">
                  <label for="classificationValue" class="form-label">Classification Value *</label>
                  <input
                    type="text"
                    class="form-control"
                    id="classificationValue"
                    [(ngModel)]="formData.classificationValue"
                    name="classificationValue"
                    placeholder="e.g., Premium, Standard, Basic"
                    required>
                  <small class="text-muted">Enter the classification value</small>
                </div>
                <div class="mb-3">
                  <label for="effectiveDate" class="form-label">Effective Date *</label>
                  <input
                    type="date"
                    class="form-control"
                    id="effectiveDate"
                    [(ngModel)]="formData.effectiveDate"
                    name="effectiveDate"
                    required>
                </div>
                <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                  <button type="reset" class="btn btn-secondary">Clear</button>
                  <button type="submit" class="btn btn-success" [disabled]="isSubmitting">
                    <span *ngIf="!isSubmitting && !editingId"><i class="bi bi-check"></i> Save Classification</span>
                    <span *ngIf="!isSubmitting && editingId"><i class="bi bi-check"></i> Update Classification</span>
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
export class ClassificationsComponent implements OnInit {
  classifications: any[] = [];
  formData: any = null;
  showForm = false;
  editingId: number | null = null;
  successMessage = '';
  errorMessage = '';
  isSubmitting = false;

  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.loadClassifications();
    this.apiService.refresh$.subscribe(() => {
      this.loadClassifications();
    });
  }

  loadClassifications() {
    this.apiService.getClassifications().subscribe({
      next: (data) => {
        this.classifications = data;
        this.clearMessages();
      },
      error: (err) => {
        this.errorMessage = 'Failed to load classifications';
        console.error(err);
      }
    });
  }

  toggleForm() {
    this.showForm = !this.showForm;
    if (this.showForm) {
      if (!this.editingId) {
        this.formData = {
          classificationType: '',
          classificationValue: '',
          effectiveDate: new Date().toISOString().split('T')[0]
        };
      }
      this.clearMessages();
    } else {
      this.editingId = null;
      this.formData = null;
    }
  }

  submit() {
    if (!this.formData.classificationType.trim()) {
      this.errorMessage = 'Please enter a classification type';
      return;
    }
    if (!this.formData.classificationValue.trim()) {
      this.errorMessage = 'Please enter a classification value';
      return;
    }

    this.isSubmitting = true;

    if (this.editingId) {
      // Update existing classification
      this.apiService.updateClassification(this.editingId, this.formData).subscribe({
        next: (response) => {
          this.successMessage = 'Classification updated successfully!';
          this.showForm = false;
          this.editingId = null;
          this.formData = null;
          this.isSubmitting = false;
          this.loadClassifications();
        },
        error: (err) => {
          this.errorMessage = 'Failed to update classification: ' + (err.error?.message || err.message);
          this.isSubmitting = false;
          console.error(err);
        }
      });
    } else {
      // Create new classification
      this.apiService.createClassification(this.formData).subscribe({
        next: (response) => {
          this.successMessage = 'Classification added successfully!';
          this.showForm = false;
          this.formData = null;
          this.isSubmitting = false;
          this.loadClassifications();
        },
        error: (err) => {
          this.errorMessage = 'Failed to add classification: ' + (err.error?.message || err.message);
          this.isSubmitting = false;
          console.error(err);
        }
      });
    }
  }

  edit(classification: any) {
    console.log('Editing classification:', classification);
    this.editingId = classification.classificationId;
    this.formData = { ...classification };
    this.showForm = true;
    this.clearMessages();
  }

  delete(id: number | undefined) {
    if (!id) {
      console.error('Delete called without ID');
      return;
    }
    console.log('Attempting to delete classification with ID:', id);
    if (confirm('Are you sure you want to delete this classification?')) {
      this.apiService.deleteClassification(id).subscribe({
        next: () => {
          this.successMessage = 'Classification deleted successfully!';
          this.loadClassifications();
        },
        error: (err) => {
          this.errorMessage = 'Failed to delete: ' + (err.error?.message || 'This classification is currently in use by active customers and cannot be deleted.');
          console.error('Delete error:', err);
        }
      });
    }
  }

  clearMessages() {
    this.successMessage = '';
    this.errorMessage = '';
  }
}
