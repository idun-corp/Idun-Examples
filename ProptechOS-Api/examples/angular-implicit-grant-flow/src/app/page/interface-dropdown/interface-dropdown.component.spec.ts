import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InterfaceDropdownComponent } from './interface-dropdown.component';

describe('InterfaceDropdownComponent', () => {
  let component: InterfaceDropdownComponent;
  let fixture: ComponentFixture<InterfaceDropdownComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InterfaceDropdownComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InterfaceDropdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
