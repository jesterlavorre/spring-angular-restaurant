import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { User } from '../models/User';
import { Restaurant } from '../models/Restaurant';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
})
export class CustomerComponent {
  constructor(
    private http: HttpClient,
    private router: Router,
    public services: BackendService
  ) {}

  ngOnInit() {
    if (this.services.activeUser.accountTypeId === '') {
      this.router.navigate(['']);
    }
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
  searchName: string = '';
  searchAddress: string = '';
  searchType: string = '';
  filteredRestaurants() {
    return this.services.homeData?.restaurants.filter((restaurant) => {
      return (
        restaurant.name.toLowerCase().includes(this.searchName.toLowerCase()) &&
        restaurant.address
          .toLowerCase()
          .includes(this.searchAddress.toLowerCase()) &&
        restaurant.type.toLowerCase().includes(this.searchType.toLowerCase())
      );
    });
  }

  sortDirection: 'asc' | 'desc' = 'asc'; // Initialize sort direction
  sortColumn: keyof Restaurant | null = null; // Initialize sortColumn with null
  toggleSort(column: keyof Restaurant) {
    if (this.sortColumn === column) {
      // Reverse the sort direction if same column is clicked again
      this.sortDirection = this.sortDirection === 'asc' ? 'desc' : 'asc';
    } else {
      // Set the new column to sort
      this.sortColumn = column;
      this.sortDirection = 'asc'; // Reset sort direction to ascending
    }

    // Perform sorting based on this.sortColumn
    this.sortData();
  }

  sortData() {
    if (this.sortColumn && this.services.homeData?.restaurants) {
      this.services.homeData.restaurants.sort((a, b) => {
        const isAsc = this.sortDirection === 'asc' ? 1 : -1;
        return (a[this.sortColumn!] > b[this.sortColumn!] ? 1 : -1) * isAsc;
      });
    }
  }

  getStarsArray(rating: string): number[] {
    if (!rating || rating.trim() === '') {
      return []; // Return empty array if no rating or rating is empty string
    }

    const ratingNumber = parseFloat(rating); // Convert string to number
    if (isNaN(ratingNumber)) {
      return []; // Return empty array if rating is not a valid number
    }

    const fullStars = Math.floor(ratingNumber); // Get the integer part (number of full stars)
    const halfStars = ratingNumber % 1 >= 0.5 ? 1 : 0; // Check if there should be a half star
    const totalStars = fullStars + halfStars;

    return Array(totalStars)
      .fill(0)
      .map((x, i) => i + 1);
  }

  goToRestaurantDetails(id: string) {
    this.services.currentRestaurantBeingViewedId = id;
    this.router.navigate(['restaurant-view']);
  }
}
