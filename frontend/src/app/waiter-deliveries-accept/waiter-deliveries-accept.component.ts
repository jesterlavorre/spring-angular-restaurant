import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { DomSanitizer } from '@angular/platform-browser';
import { DeliveryOrdersWaiter } from '../models/DeliveryOrdersWaiter';
import { User } from '../models/User';

@Component({
  selector: 'app-waiter-deliveries-accept',
  templateUrl: './waiter-deliveries-accept.component.html',
  styleUrls: ['./waiter-deliveries-accept.component.css'],
})
export class WaiterDeliveriesAcceptComponent {
  constructor(
    private http: HttpClient,
    private router: Router,
    public services: BackendService,
    private sanitizer: DomSanitizer
  ) {}

  deliveryOrders: DeliveryOrdersWaiter[] = [];
  ngOnInit() {
    this.services
      .getOrdersForRestaurantWaiter(this.services.waiterWorkAtRestaurant)
      .subscribe((response) => {
        this.deliveryOrders = response;
      });
  }
  goToDeliveryView() {
    this.router.navigate(['delivery-view']);
  }

  goToHome() {
    this.router.navigate(['waiter']);
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
  goToDeliveryOrdersWaiter() {
    this.router.navigate(['waiter-orders-accept']);
  }
  goToDiagrams() {
    this.router.navigate(['diagrams']);
  }

  acceptingOrderId: string | null = null;
  deliveryTime: string = '';

  acceptOrder(order: DeliveryOrdersWaiter): void {
    this.acceptingOrderId = order.orderId;
    this.deliveryTime = ''; // Clear previous delivery time selection
  }

  denyOrder(order: DeliveryOrdersWaiter): void {
    console.log(`Order ${order.orderId} denied.`);
    this.services
      .updateOrderStatusWaiter('130', '', order.orderId)
      .subscribe((response) => {
        this.services
          .getOrdersForRestaurantWaiter(this.services.waiterWorkAtRestaurant)
          .subscribe((response) => {
            this.deliveryOrders = response;
          });
      });
  }

  confirmAccept(order: DeliveryOrdersWaiter): void {
    if (this.deliveryTime) {
      console.log(
        `Order ${order.orderId} accepted with delivery time: ${this.deliveryTime} minutes.`
      );
      this.services
        .updateOrderStatusWaiter('110', this.deliveryTime, order.orderId)
        .subscribe((response) => {
          this.services
            .getOrdersForRestaurantWaiter(this.services.waiterWorkAtRestaurant)
            .subscribe((response) => {
              this.deliveryOrders = response;
            });
        });
      this.acceptingOrderId = null; // Reset accepting state
      this.deliveryTime = ''; // Clear delivery time selection
    } else {
      alert('Please select a delivery time frame.');
    }
  }
}
