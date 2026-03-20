import { Routes } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { ClassificationsComponent } from './components/classifications/classifications.component';
import { CustomersComponent } from './components/customers/customers.component';

export const routes: Routes = [
  { path: '', component: DashboardComponent, data: { title: 'Dashboard' } },
  { path: 'classifications', component: ClassificationsComponent, data: { title: 'Classifications' } },
  { path: 'customers', component: CustomersComponent, data: { title: 'Customers' } },
  { path: '**', redirectTo: '' }
];
