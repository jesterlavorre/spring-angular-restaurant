import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { User } from '../models/User';
import { DeliveryView } from '../models/DeliveryView';

@Component({
  selector: 'app-delivery-view',
  templateUrl: './delivery-view.component.html',
  styleUrls: ['./delivery-view.component.css'],
})
export class DeliveryViewComponent {
  constructor(
    private http: HttpClient,
    private router: Router,
    public services: BackendService
  ) {}

  deliveryView: DeliveryView[] = [];

  ngOnInit() {
    this.services
      .getAllDeliveries(this.services.activeUser.username)
      .subscribe((response) => {
        this.deliveryView = response;
      });
  }
  goToHome() {
    this.router.navigate(['customer']);
  }
  goToDeliveryView() {
    this.router.navigate(['delivery-view']);
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
  toggleBasket() {
    this.router.navigate(['basket']);
  }
}
