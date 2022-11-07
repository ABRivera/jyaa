import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from  'rxjs/operators';
import { Observable, BehaviorSubject } from  'rxjs';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Usuario } from './usuario';
import { UserSession } from './user-session';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
	apiurl = "http://localhost:8080/Rivera-ent6/rest/login";
	authSubject  =  new  BehaviorSubject(false);//Trackea el estado de autenticacion. false = no autenticado.
  jwtHelper= new JwtHelperService();
  constructor(private router: Router, private http: HttpClient) { }
//constructor(private router: Router, private http: HttpClient, public jwtHelper: JwtHelperService) { }
//TAP[ex "do()""] o MAP?? dif
  /*Si el login fue exitoso
  *Recibe en el cuerpo de la respuesta un JSON con:
  * access_token, type(Bearer) and expires_in*/
  login(email:string, password:string ) {
		return this.http.post<any>(this.apiurl, { email, password })
            .pipe(tap((userSession: UserSession) => {
                // login exitoso si hay un JWT en la respuesta
                if (userSession && userSession.access_token) {
                  // Guarda JWT en local storage
                  localStorage.setItem("ACCESS_TOKEN", userSession.access_token);
                  localStorage.setItem("TYPE", userSession.type);
                  localStorage.setItem("EXPIRES_IN", userSession.expires_in);
                  this.authSubject.next(true);
                }
             })
             );
             //redireccion al Home:this.router.navigate(['inicio']);
  }

	logout() {
		// Saca el token de Local Storage
		localStorage.removeItem("ACCESS_TOKEN");
		localStorage.removeItem("TYPE");
		localStorage.removeItem("EXPIRES_IN");
		this.authSubject.next(false);
	}
  
	isAuthenticated() {
		return  this.authSubject.asObservable();
	}
	
	getAuthorizationToken() {
		return localStorage.getItem('ACCESS_TOKEN');
	}
	
	getTokenType(){
		return localStorage.getItem('TYPE');
	}
  

  isAuthorized(allowedRoles: string[]): boolean {
    // Si no hay restriccion de roles(array vacio), autorizar acceso
    if (allowedRoles == null || allowedRoles.length === 0) {
      return true;
    }
    // Obtener Token de Local Storage y decodificarlo para leer el payload
	  const token = this.getAuthorizationToken();
    const decodeToken = this.jwtHelper.decodeToken(token);
  	// Si la decodificacion falló, el token es invalido -> se deniega el acceso
    if (!decodeToken) {
      return false;
    }
    	//CHECKEAR EXPIRACION de Token!! o lo hace el parser?
  	//Checkea si el rol esta en la lista de permitidos para otorgar acceso
    return allowedRoles.includes(decodeToken['rol']);
  }
}
