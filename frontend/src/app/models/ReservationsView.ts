// src/app/models/reservations-view.model.ts

import { Reservation } from './Reservation';

export class ReservationsView {
  activeReservations: Reservation[];
  finishedReservations: Reservation[];

  constructor(
    activeReservations: Reservation[],
    finishedReservations: Reservation[]
  ) {
    this.activeReservations = activeReservations;
    this.finishedReservations = finishedReservations;
  }
}
