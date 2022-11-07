import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UsuarioServiceService } from '../usuario-service.service';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-registro-form',
  templateUrl: './registro-form.component.html',
  styleUrls: ['./registro-form.component.css']
})
export class RegistroFormComponent implements OnInit {
  user: Usuario = new Usuario();
  horaContactoIni: any;

  constructor(private usuarioService: UsuarioServiceService, private router: Router) { }

  ngOnInit() {
  }

  onSubmit() : void {
    this.user.rol.id = 2;//"rol":{"perfil":"Voluntario","id":1}
    this.user.rol.perfil = 'Donante';
    if (this.user.horaContactoIni.length < 8) {
      this.user.horaContactoIni = this.user.horaContactoIni + ':00';
    }
    if (this.user.horaContactoFin.length < 8) {
      this.user.horaContactoFin = this.user.horaContactoFin + ':00';
    }
    this.usuarioService.addUser(this.user)
    .subscribe((res) =>{
      this.router.navigateByUrl('inicio');
    });
    //if ok registry, them sign in the user and redirect to home/panel
  }

  get diagnostic() { return JSON.stringify(this.user); }

}
