import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InputEditorAndReviewerComponent } from './input-editor-and-reviewer.component';

describe('InputEditorAndReviewerComponent', () => {
  let component: InputEditorAndReviewerComponent;
  let fixture: ComponentFixture<InputEditorAndReviewerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InputEditorAndReviewerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InputEditorAndReviewerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
