import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CustomerComponent } from './customer/customer.component';
import { AdminComponent } from './admin/admin.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { ForgottenPasswordComponent } from './forgotten-password/forgotten-password.component';
import { ChangePasswordComponent } from './change-password/change-password.component';
import { ProfileComponent } from './profile/profile.component';
import { RestaurantsViewComponent } from './restaurants-view/restaurants-view.component';
import { OrderFoodComponent } from './order-food/order-food.component';
import { BasketComponent } from './basket/basket.component';
import { ReservationsViewComponent } from './reservations-view/reservations-view.component';
import { DeliveryViewComponent } from './delivery-view/delivery-view.component';
import { WaiterComponent } from './waiter/waiter.component';
import { WaiterDeliveriesAcceptComponent } from './waiter-deliveries-accept/waiter-deliveries-accept.component';
import { DiagramsComponent } from './diagrams/diagrams.component';
import { AdminAddRestaurantComponent } from './admin-add-restaurant/admin-add-restaurant.component';
import { AdminAddWaiterComponent } from './admin-add-waiter/admin-add-waiter.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'customer', component: CustomerComponent },
  { path: 'admin', component: AdminComponent },
  { path: 'adminLogin', component: AdminLoginComponent },
  { path: 'forgotten', component: ForgottenPasswordComponent },
  { path: 'change-password', component: ChangePasswordComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'restaurant-view', component: RestaurantsViewComponent },
  { path: 'order-food', component: OrderFoodComponent },
  { path: 'basket', component: BasketComponent },
  { path: 'reservation-view', component: ReservationsViewComponent },
  { path: 'delivery-view', component: DeliveryViewComponent },
  { path: 'waiter', component: WaiterComponent },
  { path: 'waiter-orders-accept', component: WaiterDeliveriesAcceptComponent },
  { path: 'diagrams', component: DiagramsComponent },
  { path: 'admin-add-restaurant', component: AdminAddRestaurantComponent },
  { path: 'admin-add-waiter', component: AdminAddWaiterComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
