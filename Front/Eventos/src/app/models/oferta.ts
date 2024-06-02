import { Categoria } from "./categoria";
import { Usuario } from "./usuario";

export interface Ofertante{
    id: Number;
    usuario: Usuario;
    competenciaOfertantes: []
}

export interface Oferta {
    id: Number;
    title: string;
    descripcion: string;
    date: string;
    localizacion: string;
    price: Number;
    idOfertante: Ofertante;
    categorias: Categoria[]
    idAdminCheck: {id: number}
}
