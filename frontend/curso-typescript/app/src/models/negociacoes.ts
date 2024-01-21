import { Imprimivel } from "../utils/imprimivel.js";
import { Negociacao } from "./negociacao.js";

//Como aqui não temos construtor, não precisamos chamar o construtor da superclasse
export class Negociacoes extends Imprimivel {
  private listaNegociacoes: Negociacao[] = [];

  adicionar(negociacao: Negociacao): void{
    this.listaNegociacoes.push(negociacao);
  }

  listar(): readonly Negociacao[] {
    //Definindo o retorno como "ReadonlyArray", não será permitido o uso de metódos que modifiquem o array como o "pop" e "push"
    return this.listaNegociacoes;
  }

  public toString(): string {
    //"2" é o espaçamento para formatação
    return JSON.stringify(this.listaNegociacoes, null, 2);
  }
}