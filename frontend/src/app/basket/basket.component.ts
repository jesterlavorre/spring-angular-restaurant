import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { DomSanitizer } from '@angular/platform-browser';
import { User } from '../models/User';

@Component({
  selector: 'app-basket',
  templateUrl: './basket.component.html',
  styleUrls: ['./basket.component.css'],
})
export class BasketComponent {
  ngOnInit(): void {}
  constructor(
    private http: HttpClient,
    private router: Router,
    public services: BackendService,
    private sanitizer: DomSanitizer
  ) {}
  goToDeliveryView() {
    this.router.navigate(['delivery-view']);
  }
  goToReservarionView() {
    this.router.navigate(['reservation-view']);
  }
  goToHome() {
    this.router.navigate(['customer']);
  }
  goToChangePassword() {
    this.router.navigate(['change-password']);
  }

  goToProfile() {
    this.router.navigate(['profile']);
  }
  goToRestaurantDetails(id: string) {
    this.services.currentRestaurantBeingViewedId = id;
    this.router.navigate(['restaurant-view']);
  }
  toggleBasket() {
    this.router.navigate(['basket']);
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
  placeOrder(): void {
    console.log('Order placed:', this.services.order);
    this.services.makeOrder(this.services.order).subscribe((response) => {
      this.services.order = [];
      this.router.navigate(['customer']);
    });
  }
}
