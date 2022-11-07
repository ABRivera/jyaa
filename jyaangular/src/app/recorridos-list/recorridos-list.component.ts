import { Component, OnInit } from '@angular/core';
import { Recorrido } from '../recorrido';
import { RecorridoServiceService } from '../recorrido-service.service';

@Component({
  selector: 'app-recorridos-list',
  templateUrl: './recorridos-list.component.html',
  styleUrls: ['./recorridos-list.component.css']
})
export class RecorridosListComponent implements OnInit {

  recorridos: Recorrido[];
  constructor(private recorridoService: RecorridoServiceService) { }

  ngOnInit() {
    this.getRoutes();
  }

  getRoutes(): void {
    this.recorridoService.getRecorridos()
    .subscribe(recorridos => this.recorridos = recorridos);
  }

}
