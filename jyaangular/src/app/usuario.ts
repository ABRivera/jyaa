import { Rol } from './rol';

export class Usuario {
    id: number;
    name: string;
    password: string;
    email: string;
    domicilio: string;
    empresa: string;
    telefono: string;
    horaContactoIni: string;
    horaContactoFin: string;
    rol: Rol = new Rol();;
    //rol_id: number;
}