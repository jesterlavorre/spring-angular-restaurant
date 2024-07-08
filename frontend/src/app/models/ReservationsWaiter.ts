// src/app/models/reservations-waiter.model.ts

export class ReservationsWaiter {
  id: string;
  numberOfGuests: string;
  madeWhen: string;
  customerExtraComment: string;
  arriveWhen: string;
  status: string;

  constructor(
    id: string,
    numberOfGuests: string,
    madeWhen: string,
    customerExtraComment: string,
    arriveWhen: string,
    status: string
  ) {
    this.id = id;
    this.numberOfGuests = numberOfGuests;
    this.madeWhen = madeWhen;
    this.customerExtraComment = customerExtraComment;
    this.arriveWhen = arriveWhen;
    this.status = status;
  }
}
