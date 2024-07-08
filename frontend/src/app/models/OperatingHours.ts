export class OperatingHours {
  id: string;
  restaurantId: string;
  dayOfWeek: string;
  opensAt: string;
  closesAt: string;

  constructor(
    id: string,
    restaurantId: string,
    dayOfWeek: string,
    opensAt: string,
    closesAt: string
  ) {
    this.id = id;
    this.restaurantId = restaurantId;
    this.dayOfWeek = dayOfWeek;
    this.opensAt = opensAt;
    this.closesAt = closesAt;
  }
}
