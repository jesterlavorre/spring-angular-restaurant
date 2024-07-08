export class Reservation {
  madeBy: string;
  forRestaurant: string;
  extraComment: string;
  numberOfGuests: string;
  when: string;
  comment: string;
  insertTime: string;
  address: string;
  constructor(
    madeBy: string,
    forRestaurant: string,
    extraComment: string,
    numberOfGuests: string,
    when: string,
    comment: string,
    insertTime: string,
    address: string
  ) {
    this.madeBy = madeBy;
    this.forRestaurant = forRestaurant;
    this.extraComment = extraComment;
    this.numberOfGuests = numberOfGuests;
    this.when = when;
    this.comment = comment;
    this.insertTime = insertTime;
    this.address = address;
  }
}
