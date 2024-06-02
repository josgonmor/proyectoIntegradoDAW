import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfertaFormularioComponent } from './oferta-formulario.component';

describe('OfertaFormularioComponent', () => {
  let component: OfertaFormularioComponent;
  let fixture: ComponentFixture<OfertaFormularioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OfertaFormularioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(OfertaFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
