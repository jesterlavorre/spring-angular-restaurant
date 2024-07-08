export class Order {
  madeByUsername: string;
  dishId: string;
  amount: string;
  restaurantId: string;
  restaurantName: string;
  constructor(
    madeByUsername: string,
    dishId: string,
    amount: string,
    restaurantId: string,
    restaurantName: string
  ) {
    this.madeByUsername = madeByUsername;
    this.dishId = dishId;
    this.amount = amount;
    this.restaurantId = restaurantId;
    this.restaurantName = restaurantName;
  }
}
