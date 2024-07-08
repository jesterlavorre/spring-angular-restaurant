import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { User } from '../models/User';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent {
  constructor(
    private http: HttpClient,
    private router: Router,
    public services: BackendService
  ) {}
  goToDiagrams() {
    this.router.navigate(['diagrams']);
  }
  goToDeliveryView() {
    this.router.navigate(['delivery-view']);
  }
  goToReservarionView() {
    this.router.navigate(['reservation-view']);
  }
  goToDeliveryOrdersWaiter() {
    this.router.navigate(['waiter-orders-accept']);
  }
  goToHome() {
    if (this.services.activeUser.accountTypeId === '2') {
      this.router.navigate(['waiter']);
    } else {
      this.router.navigate(['customer']);
    }
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
  isEditMode: boolean = false;
  editedUser: User = this.services.activeUser;

  message: string = '';
  toggleEdit() {
    this.isEditMode = !this.isEditMode;
    // Initialize editedUser with current activeUser data
    this.editedUser = { ...this.services.activeUser };
  }
  saveChanges() {
    console.log('Saving changes:', this.editedUser);

    this.services.activeUser = { ...this.editedUser };
    this.isEditMode = false; // Hide edit form after saving

    this.services
      .updateUser(this.services.activeUser)
      .subscribe((response: User) => {
        console.log('Update successful:', response);
      });

    this.router.navigate(['customer']);
  }
  profilePictureWidth: number = 300;
  profilePictureHeight: number = 300;
  onEditFileSelected(event: any) {
    this.message = '';
    const file: File = event.target.files[0];

    if (file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        const image = new Image();
        image.src = reader.result as string;
        image.onload = () => {
          const width = image.width;
          const height = image.height;
          // console.log(reader.result as string);
          console.log(width + '    ' + height);
          if (width >= 100 && height >= 100 && width <= 300 && height <= 300) {
            this.editedUser.profilePicture = reader.result as string;
            this.profilePictureWidth = width;
            this.profilePictureHeight = height;
            console.log(this.editedUser);
          } else {
            console.log(reader.result as string);
            this.profilePictureWidth = width;
            this.profilePictureHeight = height;
            this.message = 'slika je ili premala ili prevelika';
          }
        };
      };
    }
  }
}
