import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { DomSanitizer } from '@angular/platform-browser';
import { User } from '../models/User';

@Component({
  selector: 'app-admin-add-restaurant',
  templateUrl: './admin-add-restaurant.component.html',
  styleUrls: ['./admin-add-restaurant.component.css'],
})
export class AdminAddRestaurantComponent {
  constructor(
    private http: HttpClient,
    private router: Router,
    private services: BackendService,
    private sanitizer: DomSanitizer
  ) {}
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
  goToHomeAdmin() {
    this.router.navigate(['admin']);
  }
  addRestaurant() {
    this.router.navigate(['admin-add-restaurant']);
  }

  addWaiter() {
    this.router.navigate(['admin-add-waiter']);
  }

  formData = {
    shortDescription: '',
    closesAt: '',
    opensAt: '',
    maxNumberOfPeople: 0,
    embeddedMap: '',
    phoneNumber: '',
    type: '',
    address: '',
    name: '',
  };

  // Method to handle form submission
  submitForm() {
    this.services.insertNewRestaurant(this.formData).subscribe(() => {});
  }
}
