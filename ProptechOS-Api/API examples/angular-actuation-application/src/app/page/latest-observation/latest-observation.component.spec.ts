import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LatestObservationComponent } from './latest-observation.component';

describe('LatestObservationComponent', () => {
  let component: LatestObservationComponent;
  let fixture: ComponentFixture<LatestObservationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LatestObservationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LatestObservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
