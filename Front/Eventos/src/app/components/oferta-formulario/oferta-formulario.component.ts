import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { OfertasService } from '../../services/ofertas.service';
import { ActivatedRoute, Route, Router, RouterLink } from '@angular/router';
import { Oferta } from '../../models/oferta';
import { Categoria } from '../../models/categoria';
import { CategoriaService } from '../../services/categoria.service';

@Component({
  selector: 'app-oferta-formulario',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink, FormsModule],
  templateUrl: './oferta-formulario.component.html',
  styleUrl: './oferta-formulario.component.css'
})
export class OfertaFormularioComponent {

  formGroup: FormGroup
  added:boolean=false;
  update:boolean = false;
  oferta: Oferta = <Oferta> {}
  categorias: Categoria[] = []
  totalCategorias: Categoria[]= []
  categoriaSelected: Categoria = <Categoria>{}
  categoriaChange: any
  idOfertante: number = -1;
  constructor(private os:OfertasService, private ruta: ActivatedRoute, private router:Router, private fb:FormBuilder, private cs:CategoriaService){
    this.formGroup = this.fb.group({
      title: ['', Validators.required],
      descripcion: ['', Validators.required],
      date: ['', Validators.required],
      localizacion: ['', Validators.required],
      price: ['', [Validators.required, Validators.min(0)]],

    })
    this.cs.getAll().subscribe({
      next: result=> this.totalCategorias = result
    })
    const idOfertaParam = ruta.snapshot.paramMap.get("idOferta")
    if(idOfertaParam){
      console.log("entra")
      this.update=true;
      let idParamNumber = parseInt(idOfertaParam)
      this.os.getById(idParamNumber).subscribe({
        next: result=>{
          this.oferta=result;
          this.patchToForm()
          this.cs.getByOferta(this.oferta.id).subscribe({
            next: result=>{
              this.categorias=result
              this.patchToForm()
            } 
          })
        }
      })
    }
    const idOfertanteParam = ruta.snapshot.paramMap.get("idOfertante")
    if(idOfertanteParam){
      this.idOfertante = parseInt(idOfertanteParam)

    }
  }
  onSubmit(): void {
    if (this.formGroup.valid) {
      this.patchOferta();
      console.log(this.oferta)
      console.log(this.idOfertante)
      this.os.addOrUpdateOferta(this.oferta, this.idOfertante).subscribe({
        next: result=>{
          this.oferta=result;
          this.added =true;
        }
      })
    }
    
  }
  sendCategorias(){
    this.os.addUpdateCategoriasOferta(this.oferta, this.categorias).subscribe({
      next: result=> this.router.navigate(["/perfil"])
    })
  }
  patchOferta(){
    this.oferta.title = this.formGroup.value.title
    this.oferta.descripcion = this.formGroup.value.descripcion
    this.oferta.date = this.formGroup.value.date
    this.oferta.localizacion = this.formGroup.value.localizacion
    this.oferta.price = parseFloat(this.formGroup.value.price)
  }
  patchToForm(){
    this.formGroup.patchValue(this.oferta)
  }
  clearFilter(){
    this.categorias = []
  }
  changeSelect(){
    this.categoriaSelected = this.totalCategorias.filter(item =>
      item.id == this.categoriaChange
    )[0]
  }
  addCategoria(){
    if(!this.categorias.find(item=>item.id==this.categoriaSelected.id))
    this.categorias.push(this.categoriaSelected)
  }
}
