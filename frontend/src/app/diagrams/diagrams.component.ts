import { AfterViewInit, Component, ElementRef, ViewChild } from '@angular/core';
import Chart from 'chart.js/auto';
import { Diagrams } from '../models/Diagrams';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { BackendService } from '../services/backend.service';
import { DomSanitizer } from '@angular/platform-browser';
import { User } from '../models/User';
@Component({
  selector: 'app-diagrams',
  templateUrl: './diagrams.component.html',
  styleUrls: ['./diagrams.component.css'],
})
export class DiagramsComponent {
  @ViewChild('myChartCanvas') myChartCanvas!: ElementRef<HTMLCanvasElement>;
  chart: Chart | null = null;
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
  goToDeliveryOrdersWaiter() {
    this.router.navigate(['waiter-orders-accept']);
  }
  goToDiagrams() {
    this.router.navigate(['diagrams']);
  }
  constructor(
    private http: HttpClient,
    private router: Router,
    public services: BackendService,
    private sanitizer: DomSanitizer
  ) {}
  diagrams!: Diagrams;
  barChart: any;
  pieChart: any;
  histogramChart: any;
  ngOnInit() {
    this.services
      .getDiagrams(
        this.services.activeUser.username,
        this.services.waiterWorkAtRestaurant
      )
      .subscribe((response) => {
        this.diagrams = response;
        this.createBarChart();
        this.createPieChart();
        this.createHistogramChart();
      });
  }

  createBarChart() {
    const labels = this.diagrams.diagramBars.map((bar) => bar.dayName);
    const data = this.diagrams.diagramBars.map((bar) =>
      parseFloat(bar.averageTotalGuests)
    );

    this.barChart = new Chart('barCanvas', {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Average Total Guests',
            data: data,
            backgroundColor: 'rgba(54, 162, 235, 0.6)', // Blue color with transparency
            borderColor: 'rgba(54, 162, 235, 1)', // Blue color
            borderWidth: 1,
          },
        ],
      },
      options: {
        scales: {
          y: {
            beginAtZero: true,
          },
        },
      },
    });
  }

  createPieChart() {
    const labels = this.diagrams.diagramPie.map((pie) => pie.waiterName);
    const data = this.diagrams.diagramPie.map((pie) =>
      parseFloat(pie.numberOfGuests)
    );

    this.pieChart = new Chart('pieCanvas', {
      type: 'pie',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Waiter Guests',
            data: data,
            backgroundColor: [
              'rgba(255, 99, 132, 0.6)', // Red with transparency
              'rgba(54, 162, 235, 0.6)', // Blue with transparency
            ],
            hoverOffset: 4,
          },
        ],
      },
      options: {
        plugins: {
          tooltip: {
            callbacks: {
              label: (tooltipItem: any) => {
                const dataset = tooltipItem.dataset;
                const total = dataset.data.reduce(
                  (acc: number, value: number) => acc + value,
                  0
                );
                const currentValue = dataset.data[tooltipItem.dataIndex];
                const percentage = Math.round((currentValue / total) * 100);
                return `${labels[tooltipItem.dataIndex]}: ${percentage}%`;
              },
            },
          },
        },
      },
    });
  }

  createHistogramChart() {
    const labels = this.diagrams.diagramHistogram.map(
      (hist) => hist.monthOfYear
    );
    const data = this.diagrams.diagramHistogram.map((hist) =>
      parseFloat(hist.averageReservationsForMonth)
    );

    this.histogramChart = new Chart('histogramCanvas', {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [
          {
            label: 'Average Reservations per Month (Last 24 months)',
            data: data,
            backgroundColor: 'rgba(255, 159, 64, 0.6)', // Orange color with transparency
            borderColor: 'rgba(255, 159, 64, 1)', // Orange color
            borderWidth: 1,
          },
        ],
      },
      options: {
        categoryPercentage: 1.0, // here
        barPercentage: 1.0, // here
        indexAxis: 'x', // Ensures bars are horizontal
        elements: {
          bar: {
            borderWidth: 12, // Use 'flex' for dynamic bar thickness
          },
        },
        scales: {
          y: {
            beginAtZero: true,
          },
          x: {
            stacked: true,
          },
        },
        plugins: {
          legend: {
            display: true,
            position: 'top',
          },
        },
      } as any,
    });
  }
}
