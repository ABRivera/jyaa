import { Component, OnInit } from '@angular/core';
import { UsuarioServiceService } from '../usuario-service.service';
import { Usuario } from '../usuario';

@Component({
  selector: 'app-register-bank-user',
  templateUrl: './register-bank-user.component.html',
  styleUrls: ['./register-bank-user.component.css']
})
export class RegisterBankUserComponent implements OnInit {
  user: Usuario = new Usuario();

  constructor(private usuarioService: UsuarioServiceService) { }

  ngOnInit() {
  }

  onSubmit() : void {
    this.user.rol.id = 1;//"rol":{"perfil":"Voluntario","id":1}
    this.user.rol.perfil = 'Voluntario';
    //validar los datos!!
    this.usuarioService.addUser(this.user)
    .subscribe();
  }

  get diagnostic() { return JSON.stringify(this.user); }


}
