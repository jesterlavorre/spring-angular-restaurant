import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { User } from '../models/User';
import { Home } from '../models/Home';
import { RestaurantInfo } from '../models/RestaurantInfo';
import { Reservation } from '../models/Reservation';
import { BasicResponse } from '../models/BasicResponse';
import { Dishes } from '../models/Dishes';
import { Order } from '../models/Order';
import { ReservationsView } from '../models/ReservationsView';
import { DeliveryView } from '../models/DeliveryView';
import { ReservationsWaiter } from '../models/ReservationsWaiter';
import { WorksAtRestaurant } from '../models/WorksAtRestaurant';
import { DeliveryOrdersWaiter } from '../models/DeliveryOrdersWaiter';
import { Diagrams } from '../models/Diagrams';
import { AdminData } from '../models/AdminData';
import { UsersForAdmin } from '../models/UsersForAdmin';
import { RestaurantsForAdmin } from '../models/RestaurantsForAdmin';

@Injectable({
  providedIn: 'root',
})
export class BackendService {
  constructor(private http: HttpClient, private router: Router) {}
  selectedTable: { [reservationId: string]: string } = {};
  order: Order[] = [];
  activeUser: User = new User(
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    '',
    ''
  );
  homeData: Home | undefined;

  currentRestaurantBeingViewedId: string = '';

  waiterWorkAtRestaurant: string = '';

  updateUser(newUser: User) {
    return this.http.put<User>(
      'http://localhost:8080/controller/update-user',
      newUser
    );
  }

  getHomeData() {
    return this.http.get<Home>('http://localhost:8080/controller/home-page');
  }
  login(username: string, password: string, userType: string) {
    const data = {
      u: username,
      p: password,
      t: userType,
    };
    console.log('ovo je pozvi na bek ' + username);
    console.log('ovo je pozvi na bek ' + password);
    console.log('ovo je pozvi na bek ' + userType);
    return this.http.post<User>('http://localhost:8080/controller/login', data);
  }

  getUserByEmailOrUsername(email: string, username: string) {
    return this.http.get<User>(
      'http://localhost:8080/controller/user-exists?param1=' +
        email +
        '&param2=' +
        username
    );
  }

  createUser(newUser: User) {
    return this.http.post<BasicResponse>(
      'http://localhost:8080/controller/new-user',
      newUser
    );
  }

  changePassword(username: string, newPassword: string) {
    const data = {
      username: username,
      newPassword: newPassword,
    };
    return this.http.post<User>(
      'http://localhost:8080/controller/change-password',
      data
    );
  }

  getRestaurantInfo(id: string) {
    return this.http.get<RestaurantInfo>(
      'http://localhost:8080/controller/restaurant-info-comments?param=' + id
    );
  }

  makeReservation(reser: Reservation) {
    return this.http.post<BasicResponse>(
      'http://localhost:8080/controller/make-reservation',
      reser
    );
  }
  getDished(restId: string) {
    return this.http.get<Dishes[]>(
      'http://localhost:8080/controller/all-dishes?restaurantId=' + restId
    );
  }

  makeOrder(order: Order[]) {
    return this.http.post<BasicResponse>(
      'http://localhost:8080/controller/make-order',
      order
    );
  }

  getReservationView(username: string) {
    return this.http.get<ReservationsView>(
      'http://localhost:8080/controller/user-reservations?username=' + username
    );
  }
  getAllDeliveries(username: string) {
    return this.http.get<DeliveryView[]>(
      'http://localhost:8080/controller/delivery-view?username=' + username
    );
  }
  getAllWaiterReservations(username: string) {
    return this.http.get<ReservationsWaiter[]>(
      'http://localhost:8080/controller/waiter-reservations?username=' +
        username
    );
  }

  confirmReservation(
    reservationId: string,
    username: string,
    status: string,
    denyReason: string
  ) {
    // String restaurantId;
    // String username;
    // String denyReason;
    const data = {
      reservatioId: reservationId,
      username: username,
      denyReason: denyReason,
      status: status,
    };
    return this.http.post<BasicResponse>(
      'http://localhost:8080/controller/waiter-confirm-reservation',
      data
    );
  }

  getWorksAtRestaurant(username: string) {
    return this.http.get<WorksAtRestaurant>(
      'http://localhost:8080/controller/works-at-restaurant?username=' +
        username
    );
  }
  updateLayout(restaurantId: string, layout: string, tablesSelected: string) {
    const data = {
      restaurantId: restaurantId,
      layout: layout,
      tablesSelected: tablesSelected,
    };
    return this.http.post<BasicResponse>(
      'http://localhost:8080/controller/update-layout',
      data
    );
  }

  getOrdersForRestaurantWaiter(restaurantId: string) {
    return this.http.get<DeliveryOrdersWaiter[]>(
      'http://localhost:8080/controller/waiter-delivery-orders?restaurantId=' +
        restaurantId
    );
  }
  updateOrderStatusWaiter(
    status: string,
    estimatedDelivery: string,
    orderId: string
  ) {
    // String status;
    // String estimatedDelivery;
    // String orderId;
    const data = {
      status: status,
      estimatedDelivery: estimatedDelivery,
      orderId: orderId,
    };
    return this.http.post<BasicResponse>(
      'http://localhost:8080/controller/update-order-waiter',
      data
    );
  }

  getDiagrams(username: string, restaurantId: string) {
    return this.http.get<Diagrams>(
      'http://localhost:8080/controller/diagrams?restaurantId=' +
        restaurantId +
        '&username=' +
        username
    );
  }

  getAdminData() {
    return this.http.get<AdminData>(
      'http://localhost:8080/controller/admin-data'
    );
  }
  updateUserStatus(userId: string, status: string) {
    const data = {
      userId: userId,
      status: status,
    };
    return this.http.post<BasicResponse>(
      'http://localhost:8080/controller/update-user-status',
      data
    );
  }
  editUserData(user: UsersForAdmin) {
    // const data = {
    //   entity: user,
    // };
    return this.http.post<BasicResponse>(
      'http://localhost:8080/controller/edit-user-data',
      user
    );
  }
  editRestaurantData(res: RestaurantsForAdmin) {
    return this.http.post<BasicResponse>(
      'http://localhost:8080/controller/edit-restaurant-admin',
      res
    );
  }

  insertNewRestaurant(res: any) {
    return this.http.post<BasicResponse>(
      'http://localhost:8080/controller/insert-restaurant',
      res
    );
  }

  linkWaiterRestaurant(waiterId: string, restaurantId: string) {
    // String waiterId;
    // String restaurantId;
    // link-waiter-restaurant
    const data = {
      waiterId: waiterId,
      restaurantId: restaurantId,
    };
    return this.http.post<BasicResponse>(
      'http://localhost:8080/controller/link-waiter-restaurant',
      data
    );
  }
}
