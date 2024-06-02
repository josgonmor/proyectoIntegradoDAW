import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoggerService } from '../../services/logger.service';
import { Usuario } from '../../models/usuario';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { UsuariosService } from '../../services/usuarios.service';

@Component({
  selector: 'app-registro',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule, RouterLink],
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.css'
})
export class RegistroComponent {

  public formGroup : FormGroup
  usuarioDto: Usuario = <Usuario>{}
  resError: boolean = false;
  update: boolean = false;
  constructor(private lg: LoggerService, private fb: FormBuilder, private route: Router, private ruta:ActivatedRoute, private us:UsuariosService){
    this.formGroup = fb.group({
      usuario: ['', Validators.required],
      password: ['', [Validators.required, Validators.minLength(6)]],
      email: ['', [Validators.required, Validators.email]],
      nombre: ['', Validators.required],
      apellidos: ['', Validators.required],
      tipo: ['OFERTANTE', Validators.required] 
    })
    const idParam = this.ruta.snapshot.paramMap.get("id")
    if(idParam!="-1"){
      this.update=true
      let usuario = localStorage.getItem('usuario')
      if(usuario){
        this.us.getUsuarioByUsuario(usuario).subscribe({
          next: result=>{
            this.usuarioDto = result
            this.formGroup.patchValue(this.usuarioDto)
            this.formGroup.get('usuario')?.disabled
            this.formGroup.get('tipo')?.disabled
          }
        })
      }
    }
  }

  onSubmit(): void {
    if (this.formGroup.valid) {
      this.pathValuesToUsuario()
      console.log(this.usuarioDto);
      if(this.update){
          this.us.update(this.usuarioDto).subscribe({
            next: result=>{
              console.log(result);
              this.route.navigate(["/inicio"])
            },error: error=>{
              this.resError = true
            }
        })
      }else{
        if(confirm("Está creando un usuario de tipo "+this.formGroup.value.tipo+". ¿Está seguro de que desea continuar?")){
          this.lg.registro(this.usuarioDto, this.formGroup.value.tipo).subscribe({
            next: result=>{
              console.log(result);
              this.route.navigate(["/login"])
            },error: error=>{
              this.resError = true
            }
          })
        }
      }
    } else {
      console.log('Form is invalid');
    }
  }
  pathValuesToUsuario(){
    if(!this.update){
      this.usuarioDto.usuario = this.formGroup.value.usuario 
    }
    this.usuarioDto.password = this.formGroup.value.password
    this.usuarioDto.nombre = this.formGroup.value.nombre
    this.usuarioDto.apellidos = this.formGroup.value.apellidos
    this.usuarioDto.email = this.formGroup.value.email
  }

  get usuario() { return this.formGroup.get('usuario'); }
  get password() { return this.formGroup.get('password'); }
  get email() { return this.formGroup.get('email'); }
  get nombre() { return this.formGroup.get('nombre'); }
  get apellidos() { return this.formGroup.get('apellidos'); }
}
