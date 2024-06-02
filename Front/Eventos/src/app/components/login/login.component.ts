import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Usuario } from '../../models/usuario';
import { FormsModule, NgModel } from '@angular/forms';
import { LoggerService } from '../../services/logger.service';
import { HttpClient, HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [RouterLink, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  usuario: Usuario = <Usuario>{}


  logError:boolean = false

  constructor(private route:Router, private ls:LoggerService){}


  loggin(){
    this.ls.login(this.usuario).subscribe({
      next: result=>{
        console.log(result);
        this.route.navigate(["/inicio"])
      },
      
      error: error=>{
        this.logError=true;
      }
    })
  }


}
