import { OrderDishWaiter } from './OrderDishWaiter';

export class DeliveryOrdersWaiter {
  orderId: string;
  orderedWhen: string;
  orderedBy: string;
  contactPhone: string;
  orderDishList: OrderDishWaiter[];

  constructor(
    orderId: string,
    orderedWhen: string,
    orderedBy: string,
    contactPhone: string,
    orderDishList: OrderDishWaiter[]
  ) {
    this.orderId = orderId;
    this.orderedWhen = orderedWhen;
    this.orderedBy = orderedBy;
    this.contactPhone = contactPhone;
    this.orderDishList = orderDishList;
  }
}
