import { Component } from '@angular/core';
import { Oferta } from '../../models/oferta';
import { ActivatedRoute, Router } from '@angular/router';
import { OfertasService } from '../../services/ofertas.service';
import { UsuariosService } from '../../services/usuarios.service';

@Component({
  selector: 'app-oferta',
  standalone: true,
  imports: [],
  templateUrl: './oferta.component.html',
  styleUrl: './oferta.component.css'
})
export class OfertaComponent {

  oferta:Oferta = <Oferta>{}
  constructor(private ruta:ActivatedRoute, private os:OfertasService, private router : Router, private us:UsuariosService){
    const idParam = this.ruta.snapshot.paramMap.get("id")
    let idParamNumber : Number = -1
    if(idParam){
      idParamNumber = parseInt(idParam)
    }
    this.os.getById(idParamNumber).subscribe({
      next: result=>{
        this.oferta = result
      },
      error: error=> router.navigate(["/inicio"])
    })
  }
  detallePerfil(){
    this.router.navigate(["/perfil/"+this.oferta.idOfertante.id+"/OFERTANTE"])
  }
  apuntarse(){
    const usuario = <string> localStorage.getItem("usuario")
    this.us.getUsuarioByUsuario(usuario).subscribe({
      next: result=>{
        let num = result.id ? result.id : -1
        this.os.apuntarse(this.oferta, num).subscribe({
          next: result=>this.router.navigate(["perfil"]),
          error: ()=>this.router.navigate(["/inicio"])
        })
      }
    })
  }
}
