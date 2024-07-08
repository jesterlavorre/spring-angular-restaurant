import { Component } from '@angular/core';
import { Reservation } from '../models/Reservation';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css'],
})
export class ReservationComponent {
  // reservation!: Reservation;

  constructor(
    private http: HttpClient,
    private router: Router,
    public services: BackendService
  ) {}
  message: string = '';
  reservation: Reservation = {
    numberOfGuests: '',
    when: '',
    extraComment: '',
    madeBy: '',
    forRestaurant: '',
    comment: '',
    insertTime: '',
    address: '',
  };
  submitReservation() {
    this.message = '';
    this.reservation.madeBy = this.services.activeUser.username;
    this.reservation.forRestaurant =
      this.services.currentRestaurantBeingViewedId;
    if (!this.reservation.numberOfGuests || !this.reservation.when) {
      alert('Please fill out all fields.');
      return;
    }

    this.reservation.when = this.formatDateTime(this.reservation.when);

    this.services.makeReservation(this.reservation).subscribe((response) => {
      console.log(response);
      if (response.status === 'OK') {
        alert('Reservation made');
      } else {
        this.message = response.statusMessage;
      }
    });

    this.resetForm();
  }

  // Method to reset the form after submission
  resetForm() {
    this.reservation = {
      numberOfGuests: '',
      when: '',
      extraComment: '',
      madeBy: '',
      forRestaurant: '',
      comment: '',
      insertTime: '',
      address: '',
    };
  }

  private formatDateTime(dateTime: string): string {
    if (!dateTime) return ''; // Return empty string if dateTime is not provided

    const dateObj = new Date(dateTime); // Convert dateTime string to Date object
    const year = dateObj.getFullYear();
    const month = ('0' + (dateObj.getMonth() + 1)).slice(-2);
    const date = ('0' + dateObj.getDate()).slice(-2);
    const hours = ('0' + dateObj.getHours()).slice(-2);
    const minutes = ('0' + dateObj.getMinutes()).slice(-2);
    const seconds = ('0' + dateObj.getSeconds()).slice(-2);

    return `${year}-${month}-${date} ${hours}:${minutes}:${seconds}`;
  }
}
