interface Kitchen {
  x: number;
  y: number;
  width: number;
  height: number;
  color: string;
}

interface Toilet {
  x: number;
  y: number;
  size: number;
  color: string;
}

interface Table {
  x: number;
  y: number;
  radius: number;
  number: string;
  color: string;
}

interface Layout {
  width: number;
  height: number;
  kitchen: Kitchen;
  toilet: Toilet;
  tables: Table[];
}
export class WorksAtRestaurant {
  restaurantId: string;
  layout: string;
  tablesSelected: string;
  constructor(restaurantId: string, layout: string, tablesSelected: string) {
    this.restaurantId = restaurantId;
    this.layout = layout;
    this.tablesSelected = tablesSelected;
  }
}
