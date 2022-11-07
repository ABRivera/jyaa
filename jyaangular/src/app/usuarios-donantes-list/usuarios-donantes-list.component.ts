import { Component, OnInit } from '@angular/core';
import { Usuario } from '../usuario';
import { UsuarioServiceService } from '../usuario-service.service';

@Component({
  selector: 'app-usuarios-donantes-list',
  templateUrl: './usuarios-donantes-list.component.html',
  styleUrls: ['./usuarios-donantes-list.component.css']
})
export class UsuariosDonantesListComponent implements OnInit {

  users: Usuario[];
  constructor(private usuarioService: UsuarioServiceService) { }

  ngOnInit() {
    this.getUsers()
  }
  
  getUsers(): void {
    this.usuarioService.getDonorUsers()
    .subscribe(users => this.users = users);
  }

  deleteUser(usuario: Usuario): void {
    this.users = this.users.filter(u => u !== usuario); //deleting user from the users array
    this.usuarioService.deleteUser(usuario.id)
    .subscribe();
  }

}
