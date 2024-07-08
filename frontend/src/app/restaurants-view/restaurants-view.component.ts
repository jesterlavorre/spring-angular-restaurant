import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { RestaurantInfo } from '../models/RestaurantInfo';
import { Restaurant } from '../models/Restaurant';
import { User } from '../models/User';
import {
  DomSanitizer,
  SafeHtml,
  SafeResourceUrl,
} from '@angular/platform-browser';
import { Reservation } from '../models/Reservation';

@Component({
  selector: 'app-restaurants-view',
  templateUrl: './restaurants-view.component.html',
  styleUrls: ['./restaurants-view.component.css'],
})
export class RestaurantsViewComponent {
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
  restaurantInfo!: RestaurantInfo;
  iframeHtml!: SafeHtml;
  ngOnInit() {
    this.services
      .getRestaurantInfo(this.services.currentRestaurantBeingViewedId)
      .subscribe((response) => {
        this.restaurantInfo = response;
        this.iframeHtml = this.sanitizer.bypassSecurityTrustHtml(
          response.restaurant.embeddedMap
        );
      });
  }

  goToOrderFood() {
    this.router.navigate(['order-food']);
  }
  toggleBasket() {
    this.router.navigate(['basket']);
  }
}
