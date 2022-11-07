import { AuthServiceService } from './auth-service.service';
import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpInterceptor, HttpEvent, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private authServ: AuthServiceService) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) : Observable<HttpEvent<any>>{
    // Obtener Token de autorización
    const authToken = this.authServ.getAuthorizationToken();
    const typeToken = this.authServ.getTokenType();

    // Clonar Request y reemplazar headers con los clonados
    const authReq = req.clone({
      headers: req.headers.set('Authorization', typeToken+' '+authToken)
    });
    // Enviar Request clonado al prox handler
    return next.handle(authReq);
  }
}
/*

import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';


@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(request).pipe(catchError(err => {
            if ([401, 403].indexOf(err.status) !== -1) {
                // Logout s la API responde con 401 Unauthorized o 403 Forbidden
                this.authenticationService.logout();
                location.reload(true);
            }

            const error = err.error.message || err.statusText;
            return throwError(error);
        }))
    }
}
*/
