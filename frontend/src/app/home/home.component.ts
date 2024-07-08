import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { Restaurant } from '../models/Restaurant';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  constructor(
    private http: HttpClient,
    private router: Router,
    public services: BackendService
  ) {}

  ngOnInit() {
    this.services.getHomeData().subscribe((response) => {
      this.services.homeData = response;
    });
    if (this.services.activeUser.accountTypeId === '1') {
      this.router.navigate(['customer']);
    }
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

  goToLogin() {
    this.router.navigate(['login']);
  }
  goToRegister() {
    this.router.navigate(['register']);
  }
  // goToHome() {
  //   this.router.navigate(['home']);
  // }
}
