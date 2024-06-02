import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DemandaService } from '../../services/demanda.service';
import { Demanda } from '../../models/demanda';

@Component({
  selector: 'app-demanda',
  standalone: true,
  imports: [],
  templateUrl: './demanda.component.html',
  styleUrl: './demanda.component.css'
})
export class DemandaComponent {

  demanda: Demanda = <Demanda>{}
  constructor(private ruta:ActivatedRoute, private ds: DemandaService, private router: Router){
    const idParam = ruta.snapshot.paramMap.get("id")
    let idParamNumber: Number = -1
    if(idParam){
       idParamNumber= parseInt(idParam)
    }
    this.ds.getById(idParamNumber).subscribe({
      next: result=>{
        this.demanda = result;
        console.log(this.demanda)
      },
      error: error=> this.router.navigate(["/inicio"])
    })
  }
  detallePerfil(){
    this.router.navigate(["/perfil/"+this.demanda.idDemandante.id+"/DEMANDANTE"])
  }
}
