import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive],
  template: `
    <nav class="navbar navbar-expand-lg">
      <div class="container-lg">
        <a class="navbar-brand" href="/">
          <i class="bi bi-shop"></i> Customer Manager
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
          <ul class="navbar-nav ms-auto">
            <li class="nav-item">
              <a class="nav-link" 
                 routerLink="/" 
                 [routerLinkActive]="'active'"
                 [routerLinkActiveOptions]="{ exact: true }">
                <i class="bi bi-house"></i> Dashboard
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" 
                 routerLink="/classifications" 
                 routerLinkActive="active">
                <i class="bi bi-tags"></i> Classifications
              </a>
            </li>
            <li class="nav-item">
              <a class="nav-link" 
                 routerLink="/customers" 
                 routerLinkActive="active">
                <i class="bi bi-people"></i> Customers
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  `,
  styles: []
})
export class NavbarComponent {
  @Input() currentRoute = '';
}
