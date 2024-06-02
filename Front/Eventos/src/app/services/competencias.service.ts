import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { CommonService } from './common.service';
import { Competencia } from '../models/competencia';

@Injectable({
  providedIn: 'root'
})
export class CompetenciasService {

  private url = environment.API_URL+"eventos/competencia/"
  constructor(private http:HttpClient, private commonService:CommonService) { }

  getAll(){
    return this.http.get<Competencia[]>(this.url+"getAll", {headers: this.commonService.getHeaders()})
  }

  getByOfertanteId(id:Number){
    let pa = JSON.stringify({
      id: id
    })
    return this.http.post<Competencia[]>(this.url+"ofertante", pa, {headers: this.commonService.getHeaders()})
  }
}
