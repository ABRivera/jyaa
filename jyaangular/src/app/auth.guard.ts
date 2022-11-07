import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthServiceService } from './auth-service.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router, private authService: AuthServiceService) { }
  
  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      const allowedRoles = next.data.allowedRoles;//data property from route
      const isAuthorized = this.authService.isAuthorized(allowedRoles);
      //Si esta logueado, chequea roles. Sino, redirecciona al Login
      if (!isAuthorized) {
          this.router.navigate(['unauthorized']);//REDIRECT TO  LOGIN IF NOT SIgNED IN
        }
      
      return isAuthorized;
  }
  
  /*ROUTES:
  const routes: Routes = [
    { 
      path: 'admin', 
      component: AdminComponent, 
      canActivate: [AuthGuard], 
      data: { 
        allowedRoles: ['admin', 'voluntario']
      }
    }
   ];
  */
  
}
