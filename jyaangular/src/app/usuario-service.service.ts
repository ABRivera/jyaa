import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from './usuario';
import { AuthServiceService } from './auth-service.service';

const httpOptions = {
    headers : new HttpHeaders({
      'Content-Type': 'application/json'
    })
};

@Injectable({
  providedIn: 'root'
})
export class UsuarioServiceService {

  apiurl = "http://localhost:8080/Rivera-ent6/rest/usuarios";

  constructor(private http: HttpClient, private authService: AuthServiceService) { }

  getUsers(): Observable<Usuario[]> {
      return this.http.get<Usuario[]>(this.apiurl);
  }

  getBankUsers(): Observable<Usuario[]> {
    const uriuser = `${this.apiurl}/voluntarios`;
    return this.http.get<Usuario[]>(uriuser);
  }

  getDonorUsers(): Observable<Usuario[]> {
    const uriuser = `${this.apiurl}/donantes`;
    return this.http.get<Usuario[]>(uriuser);
  }

  getUser(id: number): Observable<Usuario> {
    const uriuser = `${this.apiurl}/${id}`;
    return this.http.get<Usuario>(uriuser);
  }

  addUser(usuario: Usuario): Observable<Usuario> {
    return this.http.post<Usuario>(this.apiurl, usuario, httpOptions);
  }

  deleteUser(id: number): Observable<{}> {
    const uriuser = `${this.apiurl}/${id}`;
    return this.http.delete(uriuser, httpOptions);
  }

  updateUser(usuario: Usuario): Observable<Usuario>{
    return this.http.put<Usuario>(this.apiurl, usuario, httpOptions);
  }
}
