import { Negociacao } from "./negociacao.js";

export class Negociacoes {
  private listaNegociacoes: Array<Negociacao> = [];

  adicionar(negociacao: Negociacao): void{
    this.listaNegociacoes.push(negociacao);
  }

  listar(): Array<Negociacao> {
    //Com o spread operator, em vez de devolver a lista, devolvemos uma nova lista criada a partir da lista original
    return [...this.listaNegociacoes];
  }
}