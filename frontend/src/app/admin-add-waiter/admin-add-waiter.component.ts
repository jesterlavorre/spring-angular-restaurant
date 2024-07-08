import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { DomSanitizer } from '@angular/platform-browser';
import { User } from '../models/User';

@Component({
  selector: 'app-admin-add-waiter',
  templateUrl: './admin-add-waiter.component.html',
  styleUrls: ['./admin-add-waiter.component.css'],
})
export class AdminAddWaiterComponent {
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
  message: string = '';
  statusId: string = '';
  creditCardNo: string = '';
  profilePicture: string = '';

  email: string = '';
  contactPhone: string = '';
  sex: string = '';
  lastName: string = '';
  firstName: string = '';
  securityA: string = '';
  securityQ: string = '';
  accountTypeId: string = '';
  password: string = '';
  passwordClear: string = '';
  username: string = '';
  address: string = '';
  worksAt: string = '';
  profilePictureWidth: number = 300;
  profilePictureHeight: number = 300;

  userExists: boolean = true;

  existingUser: User | undefined;

  emailPattern: RegExp = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

  isEmailValid(): boolean {
    return this.emailPattern.test(this.email);
  }
  onFileSelected(event: any) {
    this.message = '';
    const file: File = event.target.files[0];

    if (file) {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => {
        const image = new Image();
        image.src = reader.result as string;
        image.onload = () => {
          this.profilePicture = reader.result as string;
        };
      };
    }
  }

  register() {
    this.services
      .createUser(
        new User(
          '1',
          this.creditCardNo,
          this.profilePicture,
          this.email,
          this.contactPhone,
          this.sex,
          this.lastName,
          this.firstName,
          this.securityA,
          this.securityQ,
          '2',
          this.password,
          this.password,
          this.username,
          this.address
        )
      )
      .subscribe((response) => {
        this.services
          .linkWaiterRestaurant(response.status, this.worksAt)
          .subscribe((response) => {});
      });
  }
}
