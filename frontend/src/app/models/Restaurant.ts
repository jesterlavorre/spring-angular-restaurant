import { User } from './User';

export class Restaurant {
  id: string;
  name: string;
  address: string;
  type: string;
  rated: string;
  phoneNumber: string;
  waiters: User[];
  embeddedMap: string;
  layout: string;
  constructor(
    id: string,
    name: string,
    address: string,
    type: string,
    rated: string,
    phoneNumber: string,
    waiters: User[],
    embeddedMap: string,
    layout: string
  ) {
    this.id = id;
    this.name = name;
    this.address = address;
    this.type = type;
    this.waiters = waiters;
    this.rated = rated;
    this.phoneNumber = phoneNumber;
    this.embeddedMap = embeddedMap;
    this.layout = layout;
  }
}
