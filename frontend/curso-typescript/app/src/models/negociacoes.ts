import { Comparavel } from "../interfaces/comparavel.js";
import { Imprimivel } from "../utils/imprimivel.js";
import { Negociacao } from "./negociacao.js";

export class Negociacoes implements Imprimivel, Comparavel<Negociacoes> {
  private listaNegociacoes: Negociacao[] = [];
  
  adicionar(negociacao: Negociacao): void{
    this.listaNegociacoes.push(negociacao);
  }
  
  listar(): readonly Negociacao[] {
    //Definindo o retorno como "ReadonlyArray", não será permitido o uso de metódos que modifiquem o array como o "pop" e "push"
    return this.listaNegociacoes;
  }
  
  public paraTexto(): string {
    //"2" é o espaçamento para formatação
    return JSON.stringify(this.listaNegociacoes, null, 2);
  }

  ehIgual(objeto: Negociacoes): boolean {
    return JSON.stringify(this.listaNegociacoes) === JSON.stringify(objeto);
  }
}