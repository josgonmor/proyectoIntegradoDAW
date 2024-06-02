import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { CommonService } from './common.service';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuariosService {

  private url = environment.API_URL+"eventos/"
  constructor(private http:HttpClient, private commonService:CommonService) { }


  getOfertanteById(id:Number){
    let pa = JSON.stringify({
      id: id
    })
    return this.http.post<any>(this.url+"ofertante/getById", pa, {headers: this.commonService.getHeaders()})
  }
  getDemandanteById(id:Number){
    let pa = JSON.stringify({
      id: id
    })
    return this.http.post<any>(this.url+"demandante/getById", pa, {headers: this.commonService.getHeaders()})
  }
  getUsuarioByUsuario(usuario:string){
    console.log(usuario)
    let pa = JSON.stringify({
      usuario: usuario
    })
    return this.http.post<Usuario>(this.url+"usuario/getByUsuario", pa, {headers: this.commonService.getHeaders()})
  }
  update(usuario:Usuario){
    let pa = JSON.stringify({
      usuario: usuario.usuario,
      password: usuario.password,
      nombre: usuario.nombre,
      apellidos: usuario.apellidos,
      email: usuario.email,
    })
    return this.http.post<any>(this.url+"usuario/update", pa, {headers: this.commonService.getHeaders()})
  }
}
