import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActuatorInfoComponent } from './actuator-info.component';

describe('ActuatorInfoComponent', () => {
  let component: ActuatorInfoComponent;
  let fixture: ComponentFixture<ActuatorInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActuatorInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActuatorInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
