import { Comment } from './Comment';
import { Restaurant } from './Restaurant';

export class RestaurantInfo {
  restaurant: Restaurant;
  comments: Comment[];

  constructor(restaurant: Restaurant, comments: Comment[]) {
    this.restaurant = restaurant;
    this.comments = comments;
  }
}
