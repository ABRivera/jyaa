import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { AuthGuard } from './auth.guard';
import { InicioComponent } from './inicio/inicio.component';
import { AdminPanelComponent } from './admin-panel/admin-panel.component';
import { VoluntarioPanelComponent } from './voluntario-panel/voluntario-panel.component';
import { DonantePanelComponent } from './donante-panel/donante-panel.component';
import { UserListComponent } from './user-list/user-list.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { RegistroFormComponent } from './registro-form/registro-form.component';
import { EditUserComponent } from './edit-user/edit-user.component';
import { RegisterBankUserComponent } from './register-bank-user/register-bank-user.component';
import { AppMenuComponent } from './app-menu/app-menu.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { UsuariosDonantesListComponent } from './usuarios-donantes-list/usuarios-donantes-list.component';
import { RecorridosListComponent } from './recorridos-list/recorridos-list.component';
import { DonacionesListComponent } from './donaciones-list/donaciones-list.component';
import { DonacionDetailComponent } from './donacion-detail/donacion-detail.component';
import { DonacionFormComponent } from './donacion-form/donacion-form.component';
import { RecorridoFormComponent } from './recorrido-form/recorrido-form.component';
import { RecorridoEditComponent } from './recorrido-edit/recorrido-edit.component';
import { RecorridoDetailComponent } from './recorrido-detail/recorrido-detail.component';
import { UnauthorizedComponent } from './unauthorized/unauthorized.component';
import { AuthInterceptor } from './auth-interceptor';

const routes: Routes = [
  {path: '', redirectTo: 'inicio', pathMatch: 'full'},
  {path: 'inicio', component: InicioComponent},
  {path: 'notfound', component: PageNotFoundComponent},
  {path: 'unauthorized', component: UnauthorizedComponent},
  {path: 'admin', component: AdminPanelComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Admin']
  }},
  {path: 'voluntario', component: VoluntarioPanelComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Voluntario']
  }},
  {path: 'donante', component: DonantePanelComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Donante']
  }},
  {path: 'registro', component: RegistroFormComponent},
  {path: 'registrovoluntario', component: RegisterBankUserComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Admin']
  }},
  {path: 'editarusuario/:id', component: EditUserComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Admin', 'Voluntario']
  }},
  {path: 'usuarios', component: UsuariosDonantesListComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Voluntario']
  }},
  {path: 'usuariosbanco', component: UserListComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Admin']
  }},
  {path: 'recorridos', component: RecorridosListComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Admin', 'Voluntario', 'Donante']
  }},
  {path: 'recorrido/:id', component: RecorridoDetailComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Admin', 'Voluntario', 'Donante']
  }},
  {path: 'nuevorecorrido', component: RecorridoFormComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Voluntario']
  }},
  {path: 'editrecorrido', component: RecorridoEditComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Voluntario']
  }},
  {path: 'donaciones', component: DonacionesListComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Admin', 'Voluntario', 'Donante']
  }},
  {path: 'donacion/:id', component: DonacionDetailComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Admin', 'Voluntario', 'Donante']
  }},
  {path: 'nuevadonacion', component: DonacionFormComponent, canActivate: [AuthGuard], data: { 
    allowedRoles: ['Donante']
  }},
  {path: 'login', component: LoginFormComponent},
  {path: '**', component: PageNotFoundComponent}
];

@NgModule({
  declarations: [
    AppComponent,
    InicioComponent,
    AdminPanelComponent,
    VoluntarioPanelComponent,
    DonantePanelComponent,
    UserListComponent,
    PageNotFoundComponent,
    RegistroFormComponent,
    EditUserComponent,
    RegisterBankUserComponent,
    AppMenuComponent,
    LoginFormComponent,
    UsuariosDonantesListComponent,
    RecorridosListComponent,
    DonacionesListComponent,
    DonacionDetailComponent,
    DonacionFormComponent,
    RecorridoFormComponent,
    RecorridoEditComponent,
    RecorridoDetailComponent,
    UnauthorizedComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HttpClientModule,
    FormsModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
//providers: [AuthService, AuthGuard]??