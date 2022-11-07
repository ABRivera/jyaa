import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Credentials } from '../credentials';
import { AuthServiceService } from '../auth-service.service';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {
  credential: Credentials = new Credentials();

  constructor(private authService: AuthServiceService, private router: Router) { }
//https://blog.angular-university.io/angular-jwt-authentication/
  ngOnInit() {
  }

  onSubmit() : void {
    if (this.isMailValid(this.credential.email)) {
      this.authService.login(this.credential.email, this.credential.password).subscribe((res) =>{
        if (this.authService.isAuthenticated) {
          this.router.navigateByUrl('inicio');
        }
        this.router.navigateByUrl('login');
      });
      //susbcribe> if isLoggedIn this.router.navigateByUrl('home') else refresh login+message error
      //succesful > to corresponding panel, else, login again
    }
  }

  isMailValid(email : string) : boolean {
    if (email.length > 5) {
      return true;
    } else {
      return false;
    }
  }

}
