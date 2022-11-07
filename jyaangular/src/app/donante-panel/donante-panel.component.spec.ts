import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DonantePanelComponent } from './donante-panel.component';

describe('DonantePanelComponent', () => {
  let component: DonantePanelComponent;
  let fixture: ComponentFixture<DonantePanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DonantePanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DonantePanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
