import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { CustomerComponent } from './customer/customer.component';
import { AdminComponent } from './admin/admin.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { ForgottenPasswordComponent } from './forgotten-password/forgotten-password.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

import { RestaurantsViewComponent } from './restaurants-view/restaurants-view.component';
import { ProfileComponent } from './profile/profile.component';
import { ReservationComponent } from './reservation/reservation.component';
import { OrderFoodComponent } from './order-food/order-food.component';
import { BasketComponent } from './basket/basket.component';
import { ReservationsViewComponent } from './reservations-view/reservations-view.component';
import { DeliveryViewComponent } from './delivery-view/delivery-view.component';
import { WaiterComponent } from './waiter/waiter.component';
import { WaiterDeliveriesAcceptComponent } from './waiter-deliveries-accept/waiter-deliveries-accept.component';
import { DiagramsComponent } from './diagrams/diagrams.component';
import { AdminAddRestaurantComponent } from './admin-add-restaurant/admin-add-restaurant.component';
import { AdminAddWaiterComponent } from './admin-add-waiter/admin-add-waiter.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    CustomerComponent,
    AdminComponent,
    AdminLoginComponent,
    ForgottenPasswordComponent,
    ChangePasswordComponent,
    RestaurantsViewComponent,
    ProfileComponent,
    ReservationComponent,
    OrderFoodComponent,
    BasketComponent,
    ReservationsViewComponent,
    DeliveryViewComponent,
    WaiterComponent,
    WaiterDeliveriesAcceptComponent,
    DiagramsComponent,
    AdminAddRestaurantComponent,
    AdminAddWaiterComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, HttpClientModule, FormsModule],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
