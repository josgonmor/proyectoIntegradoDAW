import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { CommonService } from './common.service';
import { Oferta } from '../models/oferta';
import { Categoria } from '../models/categoria';

@Injectable({
  providedIn: 'root'
})
export class OfertasService {

  private url = environment.API_URL+"eventos/oferta/"
  constructor(private http:HttpClient, private commomService:CommonService) { }

  getAll(){
    return this.http.get<Oferta[]>(this.url+"findAll", {headers: this.commomService.getHeaders()})
  }

  getByCategorias(categorias:Categoria[]){
    let pa = JSON.stringify({categorias})
    return this.http.post<Oferta[]>(this.url+"categoria", categorias, {headers: this.commomService.getHeaders()})
  }
  getById(id: Number){
    let pa = JSON.stringify({
      id : id
    })
    return this.http.post<Oferta>(this.url+"findById", pa, {headers: this.commomService.getHeaders()})
  }
  getByUsuario(usuario:string){
    let pa = JSON.stringify({
      usuario : usuario
    })
    return this.http.post<Oferta[]>(this.url+"ofertante", pa, {headers: this.commomService.getHeaders()})
  }
  addOrUpdateOferta(oferta:Oferta, id:Number){
    let pa = JSON.stringify({
      id : oferta.id,
      title: oferta.title,
      descripcion: oferta.descripcion,
      date: oferta.date,
      localizacion: oferta.localizacion,
      price: oferta.price,
      idOfertante: id
    })
    return this.http.post<Oferta>(this.url+"addUpdate", pa, {headers: this.commomService.getHeaders()})
  }
  addUpdateCategoriasOferta(oferta:Oferta, categorias:Categoria[]){
    let pa = JSON.stringify({
      id: oferta.id,
      categorias: categorias
    })
    return this.http.post<any>(this.url+"addUpdateCategorias", pa, {headers: this.commomService.getHeaders()})
  }

  delele(id:Number){
    let pa = JSON.stringify({
      id : id
    })
    return this.http.post<any>(this.url+"delete", pa, {headers: this.commomService.getHeaders()})
  }

  apuntarse(oferta: Oferta, idDemandante:number){
    let pa = JSON.stringify({
      id : oferta.id,
      idDemandante: idDemandante
    })
    return this.http.post<any>(this.url+"apuntarOferta", pa, {headers: this.commomService.getHeaders()})
  }
}
