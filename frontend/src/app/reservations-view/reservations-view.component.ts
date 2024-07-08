import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ReservationsView } from '../models/ReservationsView';
import { User } from '../models/User';

@Component({
  selector: 'app-reservations-view',
  templateUrl: './reservations-view.component.html',
  styleUrls: ['./reservations-view.component.css'],
})
export class ReservationsViewComponent {
  constructor(
    private http: HttpClient,
    private router: Router,
    public services: BackendService,
    private sanitizer: DomSanitizer
  ) {}
  goToDeliveryView() {
    this.router.navigate(['delivery-view']);
  }
  goToHome() {
    this.router.navigate(['customer']);
  }
  goToReservarionView() {
    this.router.navigate(['reservation-view']);
  }
  goToChangePassword() {
    this.router.navigate(['change-password']);
  }

  goToProfile() {
    this.router.navigate(['profile']);
  }
  toggleBasket() {
    this.router.navigate(['basket']);
  }
  goToRestaurants() {
    this.router.navigate(['restaurant-view']);
  }
  logout() {
    this.services.activeUser = new User(
      '',
      '',
      '',
      '',
      '',
      '',
      '',
      '',
      '',
      '',
      '',
      '',
      '',
      '',
      ''
    );
    this.router.navigate(['']);
  }
  resView!: ReservationsView;
  ngOnInit() {
    this.services
      .getReservationView(this.services.activeUser.username)
      .subscribe((response) => {
        this.resView = response;
      });
  }
}
