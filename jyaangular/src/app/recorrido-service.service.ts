import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Recorrido } from './recorrido';
import { Comentario } from './comentario';

const httpOptions = {
  headers : new HttpHeaders({
    'Content-Type': 'application/json'
  })
};

@Injectable({
  providedIn: 'root'
})
export class RecorridoServiceService {

  apiurl = "http://localhost:8080/Rivera-ent6/rest/recorridos";

  constructor(private http: HttpClient) { }

  getRecorridos(): Observable<Recorrido[]> {
    return this.http.get<Recorrido[]>(this.apiurl);
  }

  getPendingRecorridos(){}

  getRecorridosByDate(id_user: number, retired: boolean){}

  getRecorrido(id: number): Observable<Recorrido> {
    const urirec = `${this.apiurl}/${id}`;
    return this.http.get<Recorrido>(urirec);
  }

  getComentariosDeRecorrido(id: number): Observable<Comentario> {
    const urirec = `${this.apiurl}/${id}`;
    return this.http.get<Recorrido>(urirec);//CAMBIAR
  }

  addComentToRoute(id: number, comentario: Comentario): void {
    
  }

  addRecorrido(recorrido: Recorrido): Observable<Recorrido> {
    return this.http.post<Recorrido>(this.apiurl, recorrido, httpOptions);
  }

  deleteRecorrido(id: number): Observable<{}> {
    const urirec = `${this.apiurl}/${id}`;
    return this.http.delete(urirec, httpOptions);
  }

  updateRecorrido(recorrido: Recorrido): Observable<Recorrido>{
    return this.http.put<Recorrido>(this.apiurl, recorrido, httpOptions);
  }
}
