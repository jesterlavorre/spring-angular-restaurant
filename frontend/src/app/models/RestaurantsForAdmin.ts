import { OperatingHours } from './OperatingHours';

export class RestaurantsForAdmin {
  id: string;
  name: string;
  address: string;
  type: string;
  phoneNumber: string;
  embeddedMap: string;
  maxNumberOfPeople: string;
  opensAt: string;
  closesAt: string;
  layout: string;
  tablesTaken: string;
  shortDescription:string;
  operationgHours: OperatingHours[];
  constructor(
    id: string,
    name: string,
    address: string,
    type: string,
    phoneNumber: string,
    embeddedMap: string,
    maxNumberOfPeople: string,
    opensAt: string,
    closesAt: string,
    layout: string,
    tablesTaken: string,
    shortDescription:string,
    operationgHours: OperatingHours[]
  ) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.type = type;
    this.phoneNumber = phoneNumber;
    this.embeddedMap = embeddedMap;
    this.maxNumberOfPeople = maxNumberOfPeople;
    this.opensAt = opensAt;
    this.closesAt = closesAt;
    this.layout = layout;
    this.tablesTaken = tablesTaken;
    this.operationgHours = operationgHours;
    this.shortDescription = shortDescription
  }
}
