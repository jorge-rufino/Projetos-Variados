import { Imprimivel } from "../utils/imprimivel.js";
import { Comparavel } from "./comparavel.js";

//Uma interface diferentemente de uma classe que só herdar uma vez, pode herdar quantas interfaces quiser.
//Todas as classes que implementarem "Modelo", terão que implementar os métodos e propriedades das "SuperInterfaces"
export interface Modelo<T> extends Imprimivel, Comparavel<T> { }