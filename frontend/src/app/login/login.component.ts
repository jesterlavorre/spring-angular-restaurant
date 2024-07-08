import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { User } from '../models/User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  message: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private services: BackendService
  ) {}
  ngOnInit() {
    this.services.getHomeData().subscribe((response) => {
      this.services.homeData = response;
    });
    if (this.services.activeUser.accountTypeId === '1') {
      this.router.navigate(['customer']);
    }
  }
  goToForgotten() {
    this.router.navigate(['forgotten']);
  }
  login() {
    this.services
      .login(this.username, this.password, "1','2")
      .subscribe((response: User) => {
        if (response == null) {
          this.message = 'Credentials entered contain a mistake';
        } else {
          console.log(response);
          if (response.statusId == '0') {
            this.message = 'User is waiting for admin approval';
          } else {
            if (response.accountTypeId == '1') {
              this.services.activeUser = response;
              this.router.navigate(['customer']);
            } else if (response.accountTypeId == '2') {
              this.services.activeUser = response;
              this.router.navigate(['waiter']);
            } else {
              this.message = 'Credentials entered contain a mistake';
            }
          }
        }
      });
  }
}
