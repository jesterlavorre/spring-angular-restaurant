export class DiagramHistogram {
  monthOfYear: string;
  averageReservationsForMonth: string;

  constructor(monthOfYear: string, averageReservationsForMonth: string) {
    this.monthOfYear = monthOfYear;
    this.averageReservationsForMonth = averageReservationsForMonth;
  }
}
