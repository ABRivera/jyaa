import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { VoluntarioPanelComponent } from './voluntario-panel.component';

describe('VoluntarioPanelComponent', () => {
  let component: VoluntarioPanelComponent;
  let fixture: ComponentFixture<VoluntarioPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ VoluntarioPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(VoluntarioPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
