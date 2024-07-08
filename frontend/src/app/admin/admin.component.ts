import { Component } from '@angular/core';
import { AdminData } from '../models/AdminData';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { DomSanitizer, SafeResourceUrl } from '@angular/platform-browser';
import { UsersForAdmin } from '../models/UsersForAdmin';
import { RestaurantsForAdmin } from '../models/RestaurantsForAdmin';
import { User } from '../models/User';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent {
  adminData!: AdminData;
  ngOnInit() {
    this.services.getAdminData().subscribe((response) => {
      this.adminData = response;
      console.log(this.adminData);
    });
  }
  getSanitizedMapUrl(embeddedMapUrl: string): SafeResourceUrl {
    return this.sanitizer.bypassSecurityTrustHtml(embeddedMapUrl);
  }

  goToHomeAdmin() {
    this.router.navigate(['admin']);
  }
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
  rejectUser(userId: string) {
    this.services.updateUserStatus(userId, '4').subscribe((response) => {
      this.services.getAdminData().subscribe((response) => {
        this.adminData = response;
        console.log(this.adminData);
      });
    });
    console.log(`User ${userId} rejected.`);
  }

  // Method to approve user
  approveUser(userId: string) {
    this.services.updateUserStatus(userId, '1').subscribe((response) => {
      this.services.getAdminData().subscribe((response) => {
        this.adminData = response;
        console.log(this.adminData);
      });
    });
    console.log(`User ${userId} approved.`);
  }

  banUser(userId: string) {
    this.services.updateUserStatus(userId, '3').subscribe((response) => {
      this.services.getAdminData().subscribe((response) => {
        this.adminData = response;
        console.log(this.adminData);
      });
    });
    console.log(`User ${userId} banned.`);
  }
  editingUserId!: string;
  editingUser!: UsersForAdmin;

  // Method to handle entering edit mode
  toggleEdit(user: UsersForAdmin) {
    if (this.editingUserId === user.id) {
      this.editingUserId = '';
      this.editingUser = new UsersForAdmin(
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
        '',
        '',
        ''
      );
    } else {
      this.editingUserId = user.id;
      this.editingUser = { ...user };
    }
  }

  onProfilePictureChange(event: Event) {
    const input = event.target as HTMLInputElement;
    if (input && input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.editingUser.profilePicture = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }

  saveChanges() {
    // Check if the user is in activeUsers
    const activeUserIndex = this.adminData.activeUsers.findIndex(
      (user) => user.id === this.editingUser.id
    );
    if (activeUserIndex !== -1) {
      this.adminData.activeUsers[activeUserIndex] = { ...this.editingUser };
      console.log(this.editingUser);
    } else {
      // Check if the user is in waiters
      const waiterIndex = this.adminData.waiters.findIndex(
        (user) => user.id === this.editingUser.id
      );
      if (waiterIndex !== -1) {
        this.adminData.waiters[waiterIndex] = { ...this.editingUser };
        console.log(this.editingUser);
      }
    }
    this.services.editUserData(this.editingUser).subscribe((response) => {});
    this.editingUserId = '';
  }

  addRestaurant() {
    this.router.navigate(['admin-add-restaurant']);
  }

  addWaiter() {
    this.router.navigate(['admin-add-waiter']);
  }

  editingRestaurant!: RestaurantsForAdmin;

  toggleEdit1(restaurant: RestaurantsForAdmin): void {
    if (this.editingRestaurant && this.editingRestaurant.id === restaurant.id) {
      this.editingRestaurant = new RestaurantsForAdmin(
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
        []
      );
    } else {
      this.editingRestaurant = { ...restaurant };
    }
  }

  saveChanges1(): void {
    const restaurantIndex = this.adminData.restaurants.findIndex(
      (restaurant) => restaurant.id === this.editingRestaurant!.id
    );

    if (restaurantIndex !== -1) {
      this.adminData.restaurants[restaurantIndex] = {
        ...this.editingRestaurant,
      };
      console.log('Saved changes:', this.editingRestaurant);
      this.services
        .editRestaurantData(this.editingRestaurant)
        .subscribe((response) => {});
    } else {
      console.error('Restaurant not found for saving changes.');
    }

    // Here you would typically call a service to save changes to backend
    // Example: this.restaurantService.updateRestaurant(this.editingRestaurant);

    this.editingRestaurant = new RestaurantsForAdmin(
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
      []
    );
  }

  onOperatingHoursChange(event: Event, index: number, field: string): void {
    if (!this.editingRestaurant) {
      return;
    }

    const value = (event.target as HTMLInputElement).value;
    (this.editingRestaurant.operationgHours[index] as any)[field] = value;
  }

  // Method to handle file upload for layout
  onLayoutFileChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input && input.files && input.files.length > 0) {
      const file = input.files[0];
      const reader = new FileReader();
      reader.onload = () => {
        try {
          const json = JSON.parse(reader.result as string);
          if (json) {
            this.editingRestaurant.layout = JSON.stringify(json); // Assuming layout is a string field
          } else {
            console.error('Invalid JSON format.');
          }
        } catch (error) {
          console.error('Error parsing JSON file:', error);
        }
      };
      reader.readAsText(file);
    }
  }
}
