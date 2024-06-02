import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { LoggerService } from '../../services/logger.service';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {

  public log:boolean = false
  public usuario:string = ""
  public rol:string = ""
  constructor(private route:Router, private ls:LoggerService){
    this.ls.authStatus$.subscribe(status => {
      this.log = status;
      this.usuario = <string>localStorage.getItem('usuario')
      this.rol = <string>localStorage.getItem('rol')
    });
    this.log = this.ls.isAuthenticated();
  }
  
  logger(){
    if(this.log){
      this.ls.logout()
      this.route.navigate([""])
    }else{
    this.route.navigate(["login"])
    }
  }

}
