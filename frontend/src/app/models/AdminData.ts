import { RestaurantsForAdmin } from './RestaurantsForAdmin';
import { UsersForAdmin } from './UsersForAdmin';

export class AdminData {
  waitingForApproval: UsersForAdmin[];
  activeUsers: UsersForAdmin[];
  waiters: UsersForAdmin[];
  restaurants: RestaurantsForAdmin[];

  constructor(
    waitingForApproval: UsersForAdmin[],
    activeUsers: UsersForAdmin[],
    waiters: UsersForAdmin[],
    restaurants: RestaurantsForAdmin[]
  ) {
    this.waitingForApproval = waitingForApproval;
    this.activeUsers = activeUsers;
    this.waiters = waiters;
    this.restaurants = restaurants;
  }
}
