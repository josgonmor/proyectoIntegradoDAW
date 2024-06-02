import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainLoggedComponent } from './main-logged.component';

describe('MainLoggedComponent', () => {
  let component: MainLoggedComponent;
  let fixture: ComponentFixture<MainLoggedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MainLoggedComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MainLoggedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
