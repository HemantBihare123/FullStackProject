import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmployeeSearchComponentComponent } from './employee-search-component.component';

describe('EmployeeSearchComponentComponent', () => {
  let component: EmployeeSearchComponentComponent;
  let fixture: ComponentFixture<EmployeeSearchComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EmployeeSearchComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EmployeeSearchComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
