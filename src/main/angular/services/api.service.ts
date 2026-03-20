import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { tap } from 'rxjs/operators';

const API_URL = 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private refreshNeeded = new BehaviorSubject<void>(undefined);
  public refresh$ = this.refreshNeeded.asObservable();

  constructor(private http: HttpClient) { }

  triggerRefresh() {
    this.refreshNeeded.next();
  }

  // Classification APIs
  getClassifications(): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/customer-classification`);
  }

  getClassificationById(id: number): Observable<any> {
    return this.http.get<any>(`${API_URL}/customer-classification/${id}`);
  }

  createClassification(data: any): Observable<any> {
    return this.http.post<any>(`${API_URL}/customer-classification`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  updateClassification(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${API_URL}/customer-classification/${id}`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  deleteClassification(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/customer-classification/${id}`).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  // Customer Details APIs
  getCustomerDetails(): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/customer-detail`);
  }

  getCustomerDetailById(id: number): Observable<any> {
    return this.http.get<any>(`${API_URL}/customer-detail/${id}`);
  }

  createCustomerDetail(data: any): Observable<any> {
    return this.http.post<any>(`${API_URL}/customer-detail`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  updateCustomerDetail(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${API_URL}/customer-detail/${id}`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  deleteCustomerDetail(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/customer-detail/${id}`).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  bulkUploadCustomers(customers: any[]): Observable<any> {
    return this.http.post<any>(`${API_URL}/customer-detail/bulk-upload`, customers).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  // Customer Names APIs
  getCustomerNames(): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/customer-names`);
  }

  getCustomerNameById(id: number): Observable<any> {
    return this.http.get<any>(`${API_URL}/customer-names/${id}`);
  }

  createCustomerName(data: any): Observable<any> {
    return this.http.post<any>(`${API_URL}/customer-names`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  updateCustomerName(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${API_URL}/customer-names/${id}`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  deleteCustomerName(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/customer-names/${id}`).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  // Customer Address APIs
  getCustomerAddresses(): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/customer-address`);
  }

  getCustomerAddressById(id: number): Observable<any> {
    return this.http.get<any>(`${API_URL}/customer-address/${id}`);
  }

  createCustomerAddress(data: any): Observable<any> {
    return this.http.post<any>(`${API_URL}/customer-address`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  updateCustomerAddress(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${API_URL}/customer-address/${id}`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  deleteCustomerAddress(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/customer-address/${id}`).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  // Customer Contact Information APIs
  getCustomerContacts(): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/customer-contact-information`);
  }

  getCustomerContactById(id: number): Observable<any> {
    return this.http.get<any>(`${API_URL}/customer-contact-information/${id}`);
  }

  createCustomerContact(data: any): Observable<any> {
    return this.http.post<any>(`${API_URL}/customer-contact-information`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  updateCustomerContact(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${API_URL}/customer-contact-information/${id}`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  deleteCustomerContact(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/customer-contact-information/${id}`).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  // Customer Identification APIs
  getCustomerIdentifications(): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/customer-identification`);
  }

  getCustomerIdentificationById(id: number): Observable<any> {
    return this.http.get<any>(`${API_URL}/customer-identification/${id}`);
  }

  createCustomerIdentification(data: any): Observable<any> {
    return this.http.post<any>(`${API_URL}/customer-identification`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  updateCustomerIdentification(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${API_URL}/customer-identification/${id}`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  deleteCustomerIdentification(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/customer-identification/${id}`).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  // Customer Proof of ID APIs
  getCustomerProofOfIds(): Observable<any[]> {
    return this.http.get<any[]>(`${API_URL}/customer-proof-of-id`);
  }

  getCustomerProofOfIdById(id: number): Observable<any> {
    return this.http.get<any>(`${API_URL}/customer-proof-of-id/${id}`);
  }

  createCustomerProofOfId(data: any): Observable<any> {
    return this.http.post<any>(`${API_URL}/customer-proof-of-id`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  updateCustomerProofOfId(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${API_URL}/customer-proof-of-id/${id}`, data).pipe(
      tap(() => this.triggerRefresh())
    );
  }

  deleteCustomerProofOfId(id: number): Observable<void> {
    return this.http.delete<void>(`${API_URL}/customer-proof-of-id/${id}`).pipe(
      tap(() => this.triggerRefresh())
    );
  }
}
