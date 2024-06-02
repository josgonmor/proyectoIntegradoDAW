import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Categoria } from '../models/categoria';
import { CommonService } from './common.service';
import { Oferta } from '../models/oferta';

@Injectable({
  providedIn: 'root'
})
export class CategoriaService {

  private url = environment.API_URL+"eventos/categorias/"
  constructor(private http: HttpClient, private commomService: CommonService) { }

  getAll(){
    return this.http.get<Categoria[]>(this.url+"findAll", {headers: this.commomService.getHeaders()})
  }
  getByOferta(id:Number){
    let pa = JSON.stringify({
      id: id
    })
    return this.http.post<Categoria[]>(this.url+"oferta", pa, {headers: this.commomService.getHeaders()})
  }
}
