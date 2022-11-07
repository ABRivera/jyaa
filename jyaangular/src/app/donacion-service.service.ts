import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Donacion } from './donacion';

const httpOptions = {
  headers : new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class DonacionServiceService {

  apiurl = "http://localhost:8080/Rivera-ent6/rest/donaciones";

  constructor(private http: HttpClient) { }

  getDonations(): Observable<Donacion[]> {
    return this.http.get<Donacion[]>(this.apiurl);
  }

  getAllUnretiredDonations(){}

  getDonationsByUser(id_user: number, retired: boolean){}

  getDonation(id: number): Observable<Donacion> {
    const uridon = `${this.apiurl}/${id}`;
    return this.http.get<Donacion>(uridon);
  }

  addDonation(donacion: Donacion): Observable<Donacion> {
    return this.http.post<Donacion>(this.apiurl, donacion, httpOptions);
  }

  deleteDonation(id: number): Observable<{}> {
    const uridon = `${this.apiurl}/${id}`;
    return this.http.delete(uridon, httpOptions);
  }
//add product? or is update
  updateDonation(donacion: Donacion): Observable<Donacion>{
    return this.http.put<Donacion>(this.apiurl, donacion, httpOptions);
  }

  retireDonation(donacion: Donacion): Observable<Donacion> {
    donacion.is_retired = true;
    return this.updateDonation(donacion);
  }

}
