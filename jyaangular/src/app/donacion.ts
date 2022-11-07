import { Time } from '@angular/common';

export class Donacion {
    id : number;
    address : string;
    coordenadas: string;
    sucursal: string;
    fechaRetiro : Date;
    initHour: Time;
    endHour: Time;
    fechaRetirada: Date;
    horaRetiro: Time;
    is_retired: boolean;
    usuario_id: number;
    recorrido_id: number;

}
