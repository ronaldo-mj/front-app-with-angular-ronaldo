import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/model/customer';
import { API_CONFIG } from '../config/appi.config';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  url : string = API_CONFIG.urlAPI;

  constructor(private http: HttpClient) { }

  save(customer: Customer): Observable<Customer[]> {
    return this.http.post<Customer[]>(this.url+'/customer/create', customer);
  }
  list() : Observable<Customer[]> {
    return this.http.get<Customer[]>(this.url+'/customer/list');
  }

  delete(idCustomer: any): Observable<Customer> {
    return this.http.delete<Customer>(`${this.url}/customer/delete/${idCustomer}`);
  }

  


findById(idCustomer: any): Observable<Customer> {
  return this.http.get<Customer>(`${this.url}/findCustomer/${idCustomer}`);
}

}
