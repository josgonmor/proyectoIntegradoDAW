import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Demanda } from '../models/demanda';
import { Categoria } from '../models/categoria';
import { CommonService } from './common.service';

@Injectable({
  providedIn: 'root'
})
export class DemandaService {

  private url = environment.API_URL+"eventos/demanda/"
  constructor(private http: HttpClient, private commomService:CommonService) { }

  getAll(){
    return this.http.get<Demanda[]>(this.url+"findAll", {headers: this.commomService.getHeaders()})
  }

  getByCategorias(categorias:Categoria[]){
    let pa = JSON.stringify({categorias})
    return this.http.post<Demanda[]>(this.url+"categoria", categorias, {headers: this.commomService.getHeaders()})
  }

  getById(id: Number){
    let pa = JSON.stringify({
      id : id
    })
    return this.http.post<Demanda>(this.url+"findById", pa, {headers: this.commomService.getHeaders()})
  }
  getByUsuario(usuario:string){
    let pa = JSON.stringify({
      usuario : usuario
    })
    return this.http.post<Demanda[]>(this.url+"demandante", pa, {headers: this.commomService.getHeaders()})
  }
  
  addOrUpdateDemanda(demanda:Demanda, id:Number){
    let pa = JSON.stringify({
      id : demanda.id,
      title: demanda.title,
      descripcion: demanda.descripcion,
      idDemandante: id
    })
    return this.http.post<Demanda>(this.url+"addUpdate", pa, {headers: this.commomService.getHeaders()})
  }

  delele(id:Number){
    let pa = JSON.stringify({
      id : id
    })
    return this.http.post<any>(this.url+"delete", pa, {headers: this.commomService.getHeaders()})
  }
}
