import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultPicoPlacaComponent } from './consult-pico-placa.component';

describe('ConsultPicoPlacaComponent', () => {
  let component: ConsultPicoPlacaComponent;
  let fixture: ComponentFixture<ConsultPicoPlacaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConsultPicoPlacaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConsultPicoPlacaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
