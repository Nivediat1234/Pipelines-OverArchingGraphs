import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { D3AppComponent } from './d3-app.component';

describe('D3AppComponent', () => {
  let component: D3AppComponent;
  let fixture: ComponentFixture<D3AppComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ D3AppComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(D3AppComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
