import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AxiomInfoComponent } from './axiom-info.component';

describe('AxiomInfoComponent', () => {
  let component: AxiomInfoComponent;
  let fixture: ComponentFixture<AxiomInfoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AxiomInfoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AxiomInfoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
