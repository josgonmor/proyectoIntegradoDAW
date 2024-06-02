import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandaFormularioComponent } from './demanda-formulario.component';

describe('DemandaFormularioComponent', () => {
  let component: DemandaFormularioComponent;
  let fixture: ComponentFixture<DemandaFormularioComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DemandaFormularioComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DemandaFormularioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
