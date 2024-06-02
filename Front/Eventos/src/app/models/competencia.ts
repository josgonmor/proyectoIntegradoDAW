import { Categoria } from "./categoria";

export interface Competencia {
    id: Number,
    name: String,
    categorias: Categoria[]
}
