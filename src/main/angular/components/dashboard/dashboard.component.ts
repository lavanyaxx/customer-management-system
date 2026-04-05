import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div class="row mb-4">
      <div class="col-12">
        <h1 class="mb-2">Dashboard</h1>
        <p class="text-muted">Welcome to the Customer Management System</p>
      </div>
    </div>

    <div class="row">
      <div class="col-md-6 col-lg-4">
        <div class="stat-card">
          <div class="stat-label">Total Customers</div>
          <div class="stat-number">{{ customerCount }}</div>
          <small class="text-muted">Active Records</small>
        </div>
      </div>
      <div class="col-md-6 col-lg-4">
        <div class="stat-card">
          <div class="stat-label">Total Classifications</div>
          <div class="stat-number">{{ classificationCount }}</div>
          <small class="text-muted">Categories</small>
        </div>
      </div>

    </div>

    <div class="row mt-4">
      <div class="col-12">
        <div class="card">
          <div class="card-header">
            <i class="bi bi-info-circle"></i> System Information
          </div>
          <div class="card-body">
            <div class="row">
              <div class="col-md-6">
                <h6 class="text-primary">Backend Status</h6>
                <p>
                  <span class="badge badge-info">Spring Boot 4.0.2</span>
                  <span class="badge badge-info">Java 21</span>
                  <span class="badge badge-info">H2 Database</span>
                </p>
              </div>
              <div class="col-md-6">
                <h6 class="text-primary">Frontend Status</h6>
                <p>
                  <span class="badge badge-info">Angular 17</span>
                  <span class="badge badge-info">TypeScript 5.2</span>
                  <span class="badge badge-info">Bootstrap 5.3</span>
                </p>
              </div>
            </div>
            <hr>
            <p class="small text-muted mb-0">
              <strong>API Base URL:</strong> http://localhost:8080
            </p>
          </div>
        </div>
      </div>
    </div>
  `,
  styles: []
})
export class DashboardComponent implements OnInit {
  customerCount = 0;
  classificationCount = 0;


  constructor(private apiService: ApiService) { }

  ngOnInit() {
    this.loadStatistics();
    this.apiService.refresh$.subscribe(() => {
      this.loadStatistics();
    });
  }

  loadStatistics() {
    this.apiService.getCustomerCount().subscribe({
      next: (count) => {
        this.customerCount = count;
      },
      error: (err) => {
        console.error('Error loading customer count:', err);
        this.customerCount = 0;
      }
    });

    this.apiService.getClassifications().subscribe({
      next: (data) => {
        this.classificationCount = data.length;
      },
      error: (err) => {
        console.error('Error loading classifications:', err);
        this.classificationCount = 0;
      }
    });


  }
}
