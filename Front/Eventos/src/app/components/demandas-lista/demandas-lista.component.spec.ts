import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DemandasListaComponent } from './demandas-lista.component';

describe('DemandasListaComponent', () => {
  let component: DemandasListaComponent;
  let fixture: ComponentFixture<DemandasListaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DemandasListaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DemandasListaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
