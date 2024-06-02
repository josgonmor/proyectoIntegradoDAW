import { Component } from '@angular/core';
import { UsuariosService } from '../../services/usuarios.service';
import { Usuario } from '../../models/usuario';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { OfertasService } from '../../services/ofertas.service';
import { DemandaService } from '../../services/demanda.service';
import { Oferta } from '../../models/oferta';
import { Demanda } from '../../models/demanda';
import { CompetenciasService } from '../../services/competencias.service';
import { Competencia } from '../../models/competencia';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './perfil.component.html',
  styleUrl: './perfil.component.css'
})
export class PerfilComponent {

  usuario: Usuario = <Usuario>{}
  self: boolean = false;
  ofertas: Oferta[] = []
  demandas: Demanda[] = []
  competencias: Competencia[]= []
  rol: string = ""
  constructor(private us: UsuariosService, private ruta: ActivatedRoute, private os: OfertasService, private ds: DemandaService, private route: Router, private cs:CompetenciasService) {
    const idParam = ruta.snapshot.paramMap.get("id")
    const roleParam = ruta.snapshot.paramMap.get("rol")
    this.rol = <string>localStorage.getItem("rol")
    if (idParam) {
      let idParamNumber: Number = -1
      idParamNumber = parseInt(idParam)
      if (roleParam == "OFERTANTE") {
        this.us.getOfertanteById(idParamNumber).subscribe({
          next: result => {
            console.log(result)
            this.usuario = result.usuario
            this.loadListas("OFERTANTE", this.usuario.usuario)
          }
        })
      } else {
        this.us.getDemandanteById(idParamNumber).subscribe({
          next: result => {
            console.log(result)
            this.usuario = result.usuario
            this.loadListas("DEMANDANTE", this.usuario.usuario)
          }
        })
      }
    } else {
      this.self = true;
      const usuario = <string>localStorage.getItem('usuario')
      const rol = <string>localStorage.getItem('rol')
      if (usuario && rol) {
        this.us.getUsuarioByUsuario(usuario).subscribe({
          next: result => {
            this.usuario = result;
            this.loadListas(rol, usuario)
          }
        })
      }
    }

  }
  detalleDemanda(demanda: Demanda) {
    if (!localStorage.getItem('jwt')) {
      this.route.navigate(["/registro/-1"])
    } else {
      this.route.navigate(["/demanda/" + demanda.id])
    }
  }
  loadListas(rol: string, usuario: string) {
    if (rol == "OFERTANTE") {
      this.os.getByUsuario(usuario).subscribe({
        next: result => {
          this.ofertas = result;
          if(this.usuario.id){
            this.cs.getByOfertanteId(this.usuario.id).subscribe({
              next: result=>this.competencias=result
            })
          }
        },
        error: error => console.error(error)

      })
    } else {
      this.ds.getByUsuario(usuario).subscribe({
        next: result => {
          this.demandas = result
          console.log(this.demandas)
        },
        error: error => console.error(error)
      })
    }
  }
  deleteOferta(id:Number){
    this.os.delele(id).subscribe({
      next: result=>{
        this.os.getByUsuario(this.usuario.usuario).subscribe({
          next: result=> this.ofertas =result
        })
      }
    })
  }
  deleteDemanda(id:Number){
    this.ds.delele(id).subscribe({
      next: result=>{
        this.ds.getByUsuario(this.usuario.usuario).subscribe({
          next: result=> this.demandas = result
        })
      }
    })
  }
/*   detalleOferta(oferta: Oferta) {
    if (!localStorage.getItem('jwt')) {
      this.route.navigate(["/registro/-1"])
    } else {
      this.route.navigate(["/oferta/" + oferta.id])
    }
  } */
}
