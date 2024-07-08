import { Component } from '@angular/core';
import { User } from '../models/User';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css'],
})
export class AdminLoginComponent {
  username: string = '';
  password: string = '';
  message: string = '';

  constructor(
    private http: HttpClient,
    private router: Router,
    private services: BackendService
  ) {}
  ngOnInit() {}
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
          if (response.accountTypeId === '3') {
            this.services.activeUser = response;
            this.router.navigate(['admin']);
          } else {
            this.message = 'You no admin';
          }
        }
      });
  }
}
