export class DiagramBars {
  dayName: string;
  numberOfReservations: string;
  averageTotalGuests: string;

  constructor(
    dayName: string,
    numberOfReservations: string,
    averageTotalGuests: string
  ) {
    this.dayName = dayName;
    this.numberOfReservations = numberOfReservations;
    this.averageTotalGuests = averageTotalGuests;
  }
}
