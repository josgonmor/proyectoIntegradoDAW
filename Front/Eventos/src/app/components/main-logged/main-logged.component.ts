import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-main-logged',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './main-logged.component.html',
  styleUrl: './main-logged.component.css'
})
export class MainLoggedComponent {

  ofertante:boolean = true;
  constructor(){
    const opt = <string>localStorage.getItem('rol')
    if(opt!="OFERTANTE"){
      this.ofertante = false
    }
  }
}
