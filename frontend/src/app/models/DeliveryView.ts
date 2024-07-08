// src/app/models/delivery-view.model.ts

export class DeliveryView {
  restaurantName: string;
  deliveryPlaced: string;
  status: string;
  estimatedDelivery: string;
  actualDelivery: string;
  constructor(
    restaurantName: string,
    deliveryPlaced: string,
    status: string,
    estimatedDelivery: string,
    actualDelivery: string
  ) {
    this.restaurantName = restaurantName;
    this.deliveryPlaced = deliveryPlaced;
    this.status = status;
    this.estimatedDelivery = estimatedDelivery;
    this.actualDelivery = actualDelivery;
  }
}
