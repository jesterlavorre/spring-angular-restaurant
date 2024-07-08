import { Restaurant } from './Restaurant'; // Import Restaurant class if it's in a separate file

export class Home {
  numberOfRestaurants: string;
  numberOfRegisteredUsers: string;
  numberOfWaitingUsers: string;
  numberOfReservations24h: string;
  numberOfReservations7d: string;
  numberOfReservations30d: string;
  restaurants: Restaurant[];
  constructor(
    numberOfRestaurants: string,
    numberOfRegisteredUsers: string,
    numberOfWaitingUsers: string,
    numberOfReservations24h: string,
    numberOfReservations7d: string,
    numberOfReservations30d: string,
    restaurants: Restaurant[]
  ) {
    this.numberOfRestaurants = numberOfRestaurants;
    this.numberOfRegisteredUsers = numberOfRegisteredUsers;
    this.numberOfWaitingUsers = numberOfWaitingUsers;
    this.numberOfReservations24h = numberOfReservations24h;
    this.numberOfReservations7d = numberOfReservations7d;
    this.numberOfReservations30d = numberOfReservations30d;
    this.restaurants = restaurants;
  }
}
