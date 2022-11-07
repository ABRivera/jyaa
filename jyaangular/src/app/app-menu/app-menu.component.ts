import { Component, OnInit } from '@angular/core';
import { AuthServiceService } from '../auth-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-app-menu',
  templateUrl: './app-menu.component.html',
  styleUrls: ['./app-menu.component.css']
})
export class AppMenuComponent implements OnInit {

  constructor(private authService: AuthServiceService, private router: Router) { }

  ngOnInit() {
  }

  //"signin_btn" visible only when not loggedin, "signout_btn" only when signed in
  logout(){
    this.authService.logout;
    this.router.navigateByUrl("inicio");
    //redirigir a una pagina de logout
  }

}
