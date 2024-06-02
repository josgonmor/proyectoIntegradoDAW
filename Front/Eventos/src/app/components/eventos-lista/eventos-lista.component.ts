import { Component, NgModule } from '@angular/core';
import { OfertasService } from '../../services/ofertas.service';
import { Oferta } from '../../models/oferta';
import { Categoria } from '../../models/categoria';
import { CategoriaService } from '../../services/categoria.service';
import { FormsModule, NgModel } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-eventos-lista',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './eventos-lista.component.html',
  styleUrl: './eventos-lista.component.css'
})
export class EventosListaComponent {

  items: Oferta[] = [];
  displayedItems: Oferta[] = [];
  currentPage: number = 1;
  itemsPerPage: number = 10;
  totalPages: number = 0;
  pages: number[] = [];

  totalCategorias: Categoria[] = []
  categoriasFilters: Categoria[] = []
  categoriasSelected: Categoria[] = []
  categoriaSelected: Categoria = <Categoria>{}
  categoriaText: String = ""
  categoriaChange: any
  constructor(private os: OfertasService, private cs: CategoriaService, private route: Router){}
 
  ngOnInit(): void {
    this.os.getAll().subscribe({
      next: result=>{
      this.items = result;
      this.totalPages = Math.ceil(this.items.length / this.itemsPerPage);
      this.pages = Array.from({ length: this.totalPages }, (_, i) => i + 1);
      this.updateDisplayedItems();
      }
    })
    this.cs.getAll().subscribe({
      next: result=>{
        console.log(result)
        this.totalCategorias = <Categoria[]>result;
        this.categoriasFilters = this.totalCategorias
      }
    })
  }

  updateDisplayedItems(): void {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.displayedItems = this.items.slice(startIndex, endIndex);
  }

  goToPage(page: number): void {
    if (page < 1 || page > this.totalPages) {
      return;
    }
    this.currentPage = page;
    this.updateDisplayedItems();
  }
  filterOptions() {
    console.log()
    this.categoriasFilters = this.totalCategorias.filter(option =>
      option.name.toLowerCase().includes(this.categoriaText.toLowerCase())
    );
  }
  changeSelect(){
    this.categoriaSelected = this.totalCategorias.filter(item =>
      item.id == this.categoriaChange
    )[0]
  }
  addCategoria(){
    if(!this.categoriasSelected.find(item=>item.id==this.categoriaSelected.id))
    this.categoriasSelected.push(this.categoriaSelected)
  }
  addFilter(){
    this.os.getByCategorias(this.categoriasSelected).subscribe({
      next: result=>{
        this.items = result;
        this.totalPages = Math.ceil(this.items.length / this.itemsPerPage);
        this.pages = Array.from({ length: this.totalPages }, (_, i) => i + 1);
        this.updateDisplayedItems();
      }
    })
  }
  deleteFilter() {
    this.categoriasSelected = []
    this.os.getAll().subscribe({
      next: result => {
        this.items = result;
        this.totalPages = Math.ceil(this.items.length / this.itemsPerPage);
        this.pages = Array.from({ length: this.totalPages }, (_, i) => i + 1);
        this.updateDisplayedItems();
      }
    })
  }
  detalleOferta(oferta:Oferta){
    if(!localStorage.getItem('jwt')){
      this.route.navigate(["/registro/-1"])
    }else{
      this.route.navigate(["/oferta/"+oferta.id])
    }
  }
}

