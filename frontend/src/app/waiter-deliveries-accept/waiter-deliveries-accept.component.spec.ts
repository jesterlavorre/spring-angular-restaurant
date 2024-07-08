import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WaiterDeliveriesAcceptComponent } from './waiter-deliveries-accept.component';

describe('WaiterDeliveriesAcceptComponent', () => {
  let component: WaiterDeliveriesAcceptComponent;
  let fixture: ComponentFixture<WaiterDeliveriesAcceptComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WaiterDeliveriesAcceptComponent]
    });
    fixture = TestBed.createComponent(WaiterDeliveriesAcceptComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
