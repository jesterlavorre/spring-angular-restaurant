import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { Dishes } from '../models/Dishes';
import { Order } from '../models/Order';
import { User } from '../models/User';

@Component({
  selector: 'app-order-food',
  templateUrl: './order-food.component.html',
  styleUrls: ['./order-food.component.css'],
})
export class OrderFoodComponent {
  constructor(
    private http: HttpClient,
    private router: Router,
    public services: BackendService
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
  allDishes: Dishes[] = [];

  orderAmount: string = '';
  ngOnInit() {
    this.services
      .getDished(this.services.currentRestaurantBeingViewedId)
      .subscribe((response) => {
        this.allDishes = response;
      });
  }

  orderDish(dishId: string) {
    const dish = this.allDishes.find((d) => d.id === dishId);

    if (dish) {
      this.services
        .getRestaurantInfo(this.services.currentRestaurantBeingViewedId)
        .subscribe((response) => {
          // Check if there are any orders in the list
          if (this.services.order.length > 0) {
            // Get the restaurantId of the first order
            const currentRestaurantId = this.services.order[0].restaurantId;

            // Check if the current restaurant being viewed matches the restaurantId of the existing orders
            if (
              currentRestaurantId !==
              this.services.currentRestaurantBeingViewedId
            ) {
              alert(
                'You cannot buy from multiple restaurants at the same time.'
              );
              return;
            }
          }

          // Check if an order with the same dishId already exists
          const existingOrder = this.services.order.find(
            (order) => order.dishId === dish.id
          );

          if (existingOrder) {
            // Update the existing order's amount
            existingOrder.amount = (
              +existingOrder.amount + +dish.orderAmount
            ).toString();
          } else {
            // If no existing order, create a new one and add it to the list
            this.services.order.push(
              new Order(
                this.services.activeUser.username,
                dish.id,
                dish.orderAmount,
                this.services.currentRestaurantBeingViewedId,
                response.restaurant.name
              )
            );
          }

          alert('Dish added');
          console.log(this.services.order);
        });
    }
  }

  toggleBasket() {
    this.router.navigate(['basket']);
  }
}
