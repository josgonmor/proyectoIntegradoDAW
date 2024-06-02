import { Routes } from '@angular/router';
import { LoggerService } from './services/logger.service';
import { LoginComponent } from './components/login/login.component';
import { MainComponent } from './components/main/main.component';
import { MainLoggedComponent } from './components/main-logged/main-logged.component';
import { EventosListaComponent } from './components/eventos-lista/eventos-lista.component';
import { RegistroComponent } from './components/registro/registro.component';
import { DemandasListaComponent } from './components/demandas-lista/demandas-lista.component';
import { PerfilComponent } from './components/perfil/perfil.component';
import { DemandaComponent } from './components/demanda/demanda.component';
import { OfertaComponent } from './components/oferta/oferta.component';
import { OfertaFormularioComponent } from './components/oferta-formulario/oferta-formulario.component';
import { DemandaFormularioComponent } from './components/demanda-formulario/demanda-formulario.component';

export const routes: Routes = [
   {
    component: LoginComponent, path: "login"
   },
   {
    component: MainComponent, path: ""
   },
   {
      component: MainLoggedComponent, path: "inicio"
   },
   {
      component:  EventosListaComponent, path: "eventos"
   },
   {
      component: RegistroComponent, path: 'registro/:id'
   },
   {
      component: DemandasListaComponent, path: 'demandas'
   },
   {
      component: PerfilComponent, path: 'perfil'
   },
   {
      component: PerfilComponent, path: 'perfil/:id/:rol'
   },
   {
      component: DemandaComponent, path: 'demanda/:id'
   },
   {
      component: OfertaComponent, path: 'oferta/:id'
   },
   {
      component: OfertaFormularioComponent, path: 'ofertaForm/:idOfertante'
   },
   {
      component: OfertaFormularioComponent, path: 'oferta-form/:idOfertante/:idOferta'
   },
   {
      component: DemandaFormularioComponent, path: 'demanda-form/:idDemandante'
   },
   {
      component: DemandaFormularioComponent, path: 'demanda-form/:idDemandante/:idDemanda'
   },
];
