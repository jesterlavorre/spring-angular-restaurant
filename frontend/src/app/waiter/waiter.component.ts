import {
  Component,
  AfterViewInit,
  ViewChild,
  ElementRef,
  OnInit,
  HostListener,
} from '@angular/core';
import { User } from '../models/User';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { DomSanitizer } from '@angular/platform-browser';
import { ReservationsWaiter } from '../models/ReservationsWaiter';

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
  taken: boolean;
  numberOfReservations: number;
}

interface Layout {
  width: number;
  height: number;
  kitchen: Kitchen;
  toilet: Toilet;
  tables: Table[];
}

@Component({
  selector: 'app-waiter',
  templateUrl: './waiter.component.html',
  styleUrls: ['./waiter.component.css'],
})
export class WaiterComponent implements OnInit, AfterViewInit {
  waiterReservations: ReservationsWaiter[] = [];
  layout!: Layout;
  denyingReservationId: string | null = null;
  denyReason: string = '';

  @ViewChild('restaurantCanvas', { static: false })
  restaurantCanvas!: ElementRef<HTMLCanvasElement>;

  private ctx!: CanvasRenderingContext2D;

  constructor(
    private http: HttpClient,
    private router: Router,
    public services: BackendService,
    private sanitizer: DomSanitizer
  ) {}
  goToDeliveryOrdersWaiter() {
    this.router.navigate(['waiter-orders-accept']);
  }
  goToDiagrams() {
    this.router.navigate(['diagrams']);
  }
  ngOnInit() {
    this.services
      .getAllWaiterReservations(this.services.activeUser.username)
      .subscribe((response) => {
        this.waiterReservations = response;
      });
  }

  ngAfterViewInit(): void {
    this.services
      .getWorksAtRestaurant(this.services.activeUser.username)
      .subscribe((response) => {
        this.services.waiterWorkAtRestaurant = response.restaurantId;
        this.layout = JSON.parse(response.layout);
        this.services.selectedTable = JSON.parse(response.tablesSelected);
        this.restaurantCanvas.nativeElement.width = this.layout.width;
        this.restaurantCanvas.nativeElement.height = this.layout.height;
        this.drawLayout(this.layout);
      });
  }
  @HostListener('window:resize')
  onResize() {
    const canvas = this.restaurantCanvas.nativeElement;
    const parent = canvas.parentElement;
    if (parent) {
      this.layout.width = parent.clientWidth;
      this.layout.height = parent.clientHeight;
    }

    this.drawLayout(this.layout);
  }

  drawLayout(layout: Layout): void {
    const canvas = this.restaurantCanvas.nativeElement;
    this.ctx = canvas.getContext('2d')!;
    this.ctx.clearRect(0, 0, canvas.width, canvas.height);
    this.drawKitchen(
      layout.kitchen.x,
      layout.kitchen.y,
      layout.kitchen.width,
      layout.kitchen.height,
      layout.kitchen.color
    );
    this.drawToilet(
      layout.toilet.x,
      layout.toilet.y,
      layout.toilet.size,
      layout.toilet.color
    );
    layout.tables.forEach((table) => {
      this.drawTable(
        table.x,
        table.y,
        table.radius,
        table.number,
        table.color,
        table.taken
      );
    });
  }

  drawKitchen(
    x: number,
    y: number,
    width: number,
    height: number,
    color: string
  ): void {
    this.ctx.strokeStyle = '#000';
    this.ctx.lineWidth = 2;
    this.ctx.strokeRect(x, y, width, height);
    this.ctx.fillStyle = color;
    this.ctx.fillRect(x, y, width, height);
    this.ctx.fillStyle = '#000';
    this.ctx.font = '20px Arial';
    this.ctx.fillText('Kitchen', x + 20, y + height / 2);
  }

  drawToilet(x: number, y: number, size: number, color: string): void {
    this.ctx.strokeStyle = '#000';
    this.ctx.lineWidth = 2;
    this.ctx.strokeRect(x, y, size, size);
    this.ctx.fillStyle = color;
    this.ctx.fillRect(x, y, size, size);
    this.ctx.fillStyle = '#000';
    this.ctx.font = '20px Arial';
    this.ctx.fillText('Toilet', x + 20, y + size / 2);
  }

  drawTable(
    x: number,
    y: number,
    radius: number,
    number: string,
    color: string,
    taken: boolean
  ): void {
    this.ctx.beginPath();
    this.ctx.arc(x, y, radius, 0, Math.PI * 2);
    this.ctx.strokeStyle = '#000';
    this.ctx.lineWidth = 2;
    this.ctx.stroke();
    this.ctx.fillStyle = taken ? 'red' : color;
    this.ctx.fill();
    this.ctx.fillStyle = '#000';
    this.ctx.font = '20px Arial';
    this.ctx.fillText(number, x - 5, y + 5);
  }

  goToDeliveryView() {
    this.router.navigate(['delivery-view']);
  }

  goToHome() {
    this.router.navigate(['waiter']);
  }

  goToReservarionView() {
    this.router.navigate(['reservation-view']);
  }

  goToChangePassword() {
    this.router.navigate(['change-password']);
  }

  goToProfile() {
    this.router.navigate(['profile']);
  }

  toggleBasket() {
    this.router.navigate(['basket']);
  }

  goToRestaurants() {
    this.router.navigate(['restaurant-view']);
  }

  logout() {
    this.services.activeUser = new User(
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
    this.router.navigate(['']);
  }

  acceptReservation(reservationId: string) {
    console.log(`Reservation ${reservationId} accepted`);
    const tableNumber = this.services.selectedTable[reservationId];
    const table = this.layout.tables.find((t) => t.number === tableNumber);

    if (table) {
      table.taken = true;
      table.numberOfReservations++;
      console.log(table.numberOfReservations);
      this.drawLayout(this.layout);
    }
    this.services
      .confirmReservation(
        reservationId,
        this.services.activeUser.username,
        '10',
        ''
      )
      .subscribe((response) => {
        this.services
          .getAllWaiterReservations(this.services.activeUser.username)
          .subscribe((response) => {
            this.waiterReservations = response;
          });

        const reservation = this.waiterReservations.find(
          (res) => res.id === reservationId
        );
        if (reservation) {
          const tableNumber = this.services.selectedTable[reservationId];
          const table = this.layout.tables.find(
            (t) => t.number === tableNumber
          );

          if (table) {
            table.taken = true;
            this.drawLayout(this.layout);
          }
          this.services
            .updateLayout(
              this.services.waiterWorkAtRestaurant,
              JSON.stringify(this.layout),

              JSON.stringify(this.services.selectedTable)
            )
            .subscribe((response) => {});
        }
      });
  }

  toggleDenyReason(reservationId: string) {
    if (this.denyingReservationId === reservationId) {
      this.denyingReservationId = null;
    } else {
      this.denyingReservationId = reservationId;
    }
    this.denyReason = '';
  }

  denyReservation(reservationId: string) {
    if (this.denyReason.trim()) {
      console.log(
        `Reservation ${reservationId} denied for reason: ${this.denyReason}`
      );
      this.services
        .confirmReservation(
          reservationId,
          this.services.activeUser.username,
          '70',
          this.denyReason
        )
        .subscribe((response) => {
          this.services
            .getAllWaiterReservations(this.services.activeUser.username)
            .subscribe((response) => {
              this.waiterReservations = response;
            });
        });
      this.denyingReservationId = null;
      this.denyReason = '';
    } else {
      alert('Please provide a reason for denial.');
    }
  }

  customerDidNotShowUp(reservationId: string) {
    const reservation = this.waiterReservations.find(
      (res) => res.id === reservationId
    );
    if (reservation) {
      const now = new Date();
      const arrivalTime = new Date(reservation.arriveWhen);
      const timeDiff = now.getTime() - arrivalTime.getTime();

      const minutesDiff = Math.floor(timeDiff / (1000 * 60));
      console.log(minutesDiff);
      if (minutesDiff < 0) {
        alert('Customer arrival time is in the future.');
        return;
      } else if (minutesDiff > 30) {
        console.log(
          `Automatically marking reservation ${reservationId} as no-show because more than 30 minutes have passed.`
        );
        this.updateReservationStatus(reservationId, '30');
        const tableNumber = this.services.selectedTable[reservationId];
        const table = this.layout.tables.find((t) => t.number === tableNumber);
        if (table) {
          table.numberOfReservations--;
          if (table.numberOfReservations === 0) {
            table.taken = false;
            this.drawLayout(this.layout);
          }
        }
        this.services
          .updateLayout(
            this.services.waiterWorkAtRestaurant,
            JSON.stringify(this.layout),
            JSON.stringify(this.services.selectedTable)
          )
          .subscribe((response) => {});
      } else {
        console.log(
          `Customer did not show up for reservation ${reservationId}`
        );
        const tableNumber = this.services.selectedTable[reservationId];
        const table = this.layout.tables.find((t) => t.number === tableNumber);
        if (table) {
          table.numberOfReservations--;
          if (table.numberOfReservations === 0) {
            table.taken = false;
            this.drawLayout(this.layout);
          }
        }
        this.services
          .updateLayout(
            this.services.waiterWorkAtRestaurant,
            JSON.stringify(this.layout),
            JSON.stringify(this.services.selectedTable)
          )
          .subscribe((response) => {});
        this.updateReservationStatus(reservationId, '30');
      }
    }
  }

  customerShowedUp(reservationId: string) {
    const reservation = this.waiterReservations.find(
      (res) => res.id === reservationId
    );
    if (reservation) {
      const now = new Date();
      const arrivalTime = new Date(reservation.arriveWhen);
      const timeDiff = now.getTime() - arrivalTime.getTime();
      const minutesDiff = Math.floor(timeDiff / (1000 * 60));
      console.log(now.getTime() + ' ' + arrivalTime.getTime());
      console.log(minutesDiff);
      if (minutesDiff < 0) {
        alert('Customer arrival time is in the future.');
        return;
      } else if (minutesDiff > 30) {
        alert(
          'Customer cannot be marked as showed up because more than 30 minutes have passed, makring as no show.'
        );
        this.updateReservationStatus(reservationId, '30');
        const tableNumber = this.services.selectedTable[reservationId];
        const table = this.layout.tables.find((t) => t.number === tableNumber);
        if (table) {
          table.taken = false;
          this.drawLayout(this.layout);
        }
        this.services
          .updateLayout(
            this.services.waiterWorkAtRestaurant,
            JSON.stringify(this.layout),
            JSON.stringify(this.services.selectedTable)
          )
          .subscribe((response) => {});
        this.updateReservationStatus(reservationId, '60');
        return;
      } else {
        console.log(`Customer showed up for reservation ${reservationId}`);
        const tableNumber = this.services.selectedTable[reservationId];
        const table = this.layout.tables.find((t) => t.number === tableNumber);

        if (table) {
          table.taken = true;
          this.drawLayout(this.layout);
        }
        this.services
          .updateLayout(
            this.services.waiterWorkAtRestaurant,
            JSON.stringify(this.layout),
            JSON.stringify(this.services.selectedTable)
          )
          .subscribe((response) => {});
        this.updateReservationStatus(reservationId, '50');
      }
    }
  }

  customerHasLeft(reservationId: string) {
    console.log(`Customer has left for reservation ${reservationId}`);
    const tableNumber = this.services.selectedTable[reservationId];
    const table = this.layout.tables.find((t) => t.number === tableNumber);
    if (table) {
      console.log(table.numberOfReservations);
      table.numberOfReservations--;
      if (table.numberOfReservations === 0) {
        table.taken = false;
      }

      this.drawLayout(this.layout);
    }
    this.services
      .updateLayout(
        this.services.waiterWorkAtRestaurant,
        JSON.stringify(this.layout),
        JSON.stringify(this.services.selectedTable)
      )
      .subscribe((response) => {});
    this.updateReservationStatus(reservationId, '60');
  }

  updateReservationStatus(reservationId: string, status: string) {
    this.services
      .confirmReservation(
        reservationId,
        this.services.activeUser.username,
        status,
        ''
      )
      .subscribe((response) => {
        this.services
          .getAllWaiterReservations(this.services.activeUser.username)
          .subscribe((response) => {
            this.waiterReservations = response;
          });
      });
  }

  getAvailableTables() {
    return this.layout.tables;
    // .filter((table) => !table.taken);
  }
}
