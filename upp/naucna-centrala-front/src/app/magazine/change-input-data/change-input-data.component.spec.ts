import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeInputDataComponent } from './change-input-data.component';

describe('ChangeInputDataComponent', () => {
  let component: ChangeInputDataComponent;
  let fixture: ComponentFixture<ChangeInputDataComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChangeInputDataComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChangeInputDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
