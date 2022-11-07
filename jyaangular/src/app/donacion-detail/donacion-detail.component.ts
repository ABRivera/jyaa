import { Component, OnInit } from '@angular/core';
import { Donacion } from '../donacion';
import { DonacionServiceService } from '../donacion-service.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-donacion-detail',
  templateUrl: './donacion-detail.component.html',
  styleUrls: ['./donacion-detail.component.css']
})
export class DonacionDetailComponent implements OnInit {

  donacion: Donacion = new Donacion();
  constructor(private route: ActivatedRoute, private donacionService: DonacionServiceService) { }

  ngOnInit() {
    this.getDonation();
  }

  getDonation(): void {
    const id = +this.route.snapshot.paramMap.get('id'); //gets Id from route parameters
    this.donacionService.getDonation(id)
    .subscribe(donacion => this.donacion = donacion);
  }

  marsAsRetired(donacion: Donacion): void {
    this.donacionService.retireDonation(donacion)
    .subscribe();
  }

  addToRoute(donacion): void {
    //gets and shows availabls rutes?? and then adds it to selected one????
  }

}
