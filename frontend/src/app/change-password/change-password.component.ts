import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { User } from '../models/User';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css'],
})
export class ChangePasswordComponent {
  constructor(
    private http: HttpClient,
    private router: Router,
    private services: BackendService
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
  oldPassword: string = '';
  newPassword: string = '';
  confirmNewPassword: string = '';
  message: string = '';

  changePassword() {
    this.message = '';
    if (this.newPassword !== this.confirmNewPassword) {
      this.message = 'New passwords do not match.';
    } else if (this.oldPassword === this.newPassword) {
      this.message = 'New password cannot be the same as the old password.';
    } else {
      if (this.oldPassword !== this.services.activeUser?.passwordClear) {
        this.message = 'Current password does not match the one entered';
      } else {
        this.message = 'Password changed successfully.';
        this.services
          .changePassword(this.services.activeUser.username, this.newPassword)
          .subscribe((response) => {
            this.services.activeUser.passwordClear = this.newPassword;
            this.services.activeUser.password = this.newPassword;
          });
        this.router.navigate(['customer']);
      }
    }
  }
}
