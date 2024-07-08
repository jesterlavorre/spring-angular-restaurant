import { Ingredient } from './Ingredient';

export class Dishes {
  id: string;
  name: string;
  price: string;
  picture: string;
  ingredients: Ingredient[];
  orderAmount: string;
  constructor(
    id: string,
    name: string,
    price: string,
    picture: string,
    ingredients: Ingredient[],
    orderAmount: string
  ) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.picture = picture;
    this.ingredients = ingredients;
    this.orderAmount = orderAmount;
  }
}
