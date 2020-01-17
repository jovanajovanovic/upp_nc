import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AcceptReviewerComponent } from './accept-reviewer.component';

describe('AcceptReviewerComponent', () => {
  let component: AcceptReviewerComponent;
  let fixture: ComponentFixture<AcceptReviewerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AcceptReviewerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AcceptReviewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
