import { Usuario } from "./usuario";

export interface Demandante {
    id: Number;
    usuario: Usuario
}
export interface Demanda {
    id: Number;
    title: String;
    descripcion: String;
    estado: String;
    idDemandante: Demandante;
}
