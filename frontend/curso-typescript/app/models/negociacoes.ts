import { Negociacao } from "./negociacao.js";

export class Negociacoes {
  private listaNegociacoes: Array<Negociacao> = [];

  adicionar(negociacao: Negociacao): void{
    this.listaNegociacoes.push(negociacao);
  }

  listar(): ReadonlyArray<Negociacao> {
    //Definindo o retorno como "ReadonlyArray", não será permitido o uso de metódos que modifiquem o array como o "pop" e "push"
    return this.listaNegociacoes;
  }
}