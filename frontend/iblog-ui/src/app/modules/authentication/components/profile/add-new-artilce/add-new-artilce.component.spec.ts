import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewArtilceComponent } from './add-new-artilce.component';

describe('AddNewArtilceComponent', () => {
  let component: AddNewArtilceComponent;
  let fixture: ComponentFixture<AddNewArtilceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddNewArtilceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddNewArtilceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
