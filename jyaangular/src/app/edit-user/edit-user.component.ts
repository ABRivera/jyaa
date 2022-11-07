import { Component, OnInit } from '@angular/core';
import { UsuarioServiceService } from '../usuario-service.service';
import { Usuario } from '../usuario';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {

  //user: Usuario;
  user: Usuario = new Usuario();
  constructor(private route: ActivatedRoute, private usuarioService: UsuarioServiceService) { }

  ngOnInit(): void {
    this.getUser();
  }

  getUser(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.usuarioService.getUser(id)
    .subscribe(user => this.user = user);
  }

  onSubmit() : void {
    /*if (this.user.horaContactoIni.length < 8) {
      this.user.horaContactoIni = this.user.horaContactoIni + ':00';
    }
    if (this.user.horaContactoFin.length < 8) {
      this.user.horaContactoFin = this.user.horaContactoFin + ':00';
    }*/
    this.usuarioService.updateUser(this.user)
    .subscribe();
  }

  get diagnostic() { return JSON.stringify(this.user); }

}
