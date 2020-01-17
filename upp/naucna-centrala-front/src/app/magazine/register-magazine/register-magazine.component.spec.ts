import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RegisterMagazineComponent } from './register-magazine.component';

describe('RegisterMagazineComponent', () => {
  let component: RegisterMagazineComponent;
  let fixture: ComponentFixture<RegisterMagazineComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RegisterMagazineComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterMagazineComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
