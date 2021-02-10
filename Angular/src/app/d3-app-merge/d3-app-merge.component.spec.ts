import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { D3AppMergeComponent } from './d3-app-merge.component';

describe('D3AppMergeComponent', () => {
  let component: D3AppMergeComponent;
  let fixture: ComponentFixture<D3AppMergeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ D3AppMergeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(D3AppMergeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
