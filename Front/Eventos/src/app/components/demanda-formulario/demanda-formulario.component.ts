import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { DemandaService } from '../../services/demanda.service';
import { Demanda } from '../../models/demanda';

@Component({
  selector: 'app-demanda-formulario',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './demanda-formulario.component.html',
  styleUrl: './demanda-formulario.component.css'
})
export class DemandaFormularioComponent {

  formGroup: FormGroup
  demanda: Demanda = <Demanda>{}
  idDemandante: number =-1
  constructor(private fb: FormBuilder, private ruta: ActivatedRoute, private ds:DemandaService, private router:Router) {
    this.formGroup = this.fb.group({
      title: ['', Validators.required],
      descripcion: ['', Validators.required],
    });

    const idDemandaParam = ruta.snapshot.paramMap.get("idDemanda")
    if(idDemandaParam){
      let nu = parseInt(idDemandaParam)
      this.ds.getById(nu).subscribe({
        next: result=>{this.demanda=result
          this.formGroup.patchValue(this.demanda)
        }
      })
    }
    const idDemandante = ruta.snapshot.paramMap.get("idDemandante")
    if(idDemandante){
      this.idDemandante = parseInt(idDemandante)
    }
  }


  onSubmit(): void {
    if (this.formGroup.valid) {
      this.patchDemanda()
      this.ds.addOrUpdateDemanda(this.demanda, this.idDemandante).subscribe({
        next: result=>this.router.navigate(["/perfil"])
      })
    }
  }

  patchDemanda(){
    this.demanda.title= this.formGroup.value.title
    this.demanda.descripcion = this.formGroup.value.descripcion
  }

}
