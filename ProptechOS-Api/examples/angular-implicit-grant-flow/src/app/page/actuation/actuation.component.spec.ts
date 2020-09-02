import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ActuationComponent } from './actuation.component';

describe('ActuationComponent', () => {
  let component: ActuationComponent;
  let fixture: ComponentFixture<ActuationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ActuationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ActuationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
