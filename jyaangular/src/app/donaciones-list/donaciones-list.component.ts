import { Component, OnInit } from '@angular/core';
import { Donacion } from '../donacion';
import { DonacionServiceService } from '../donacion-service.service';

@Component({
  selector: 'app-donaciones-list',
  templateUrl: './donaciones-list.component.html',
  styleUrls: ['./donaciones-list.component.css']
})
export class DonacionesListComponent implements OnInit {

  donaciones: Donacion[];
  constructor(private donacionService: DonacionServiceService) { }

  ngOnInit() {
    this.getDonations();
  }

  getDonations(): void {
    this.donacionService.getDonations()
    .subscribe(donaciones => this.donaciones = donaciones);
  }

  marsAsRetired(donacion: Donacion): void {
    this.donaciones = this.donaciones.filter(d => d !== donacion); //deleting donation from the array
    this.donacionService.retireDonation(donacion)
    .subscribe();
  }

}
