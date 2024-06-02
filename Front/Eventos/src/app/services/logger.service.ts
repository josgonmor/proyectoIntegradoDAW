import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Usuario } from '../models/usuario';
import { CommonService } from './common.service';
import { environment } from '../../environments/environment';
import { BehaviorSubject, Observable, Subject, tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoggerService {

  private url = environment.API_URL+"auth/"

  
  private authStatusSubject = new BehaviorSubject<boolean>(false);
  authStatus$: Observable<boolean> = this.authStatusSubject.asObservable();
  constructor(private http:HttpClient, private commomService:CommonService) { }

  login(usuario:Usuario){
    let pa = JSON.stringify({
      usuario: usuario.usuario,
      password: usuario.password
    })
    return this.http.post<any>(this.url+"login", pa, {headers: this.commomService.getHeaders()}).pipe(
      tap(response=>{
        if(response.status=="OK"){
          console.log(response.usuario)
          localStorage.setItem('jwt', response.jwt.accessToken)
          localStorage.setItem('rol', response.rol[0].authority)
          localStorage.setItem('usuario', response.usuario)
          this.authStatusSubject.next(true)
        }
      })
    )
  }
  registro(usuario:Usuario, opt:String){
    let pa = JSON.stringify({
      usuario: usuario.usuario,
      password: usuario.password,
      nombre: usuario.nombre,
      apellidos: usuario.apellidos,
      email: usuario.email,
      role: opt
    })
    console.log(pa)
    return this.http.post<any>(this.url+"registro", pa, {headers: this.commomService.getHeaders()})
  }

  
  logout() {
    // let token = localStorage.getItem('jwt')
    // let header = this.getHeaders()
    // header = header.set('Authorization', "Bearer "+token);
    // return this.http.post<any>(this.url+"logout", {}, {headers: header}).pipe(
    //   tap(response=>{
    //     console.log(response)

    //     localStorage.removeItem('jwt');
    //     localStorage.removeItem('rol')
    //     this.authStatusSubject.next(false);
    //   })
    // )
    localStorage.removeItem('jwt')
    localStorage.removeItem('rol')
    localStorage.removeItem('usuario')
    this.authStatusSubject.next(false)
  }

  isAuthenticated(): boolean {
    return !!localStorage.getItem('jwt');
  }




}
