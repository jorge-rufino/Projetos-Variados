import { Negociacao } from "./negociacao.js";

export class Negociacoes {
  private listaNegociacoes: Array<Negociacao> = [];

  adicionar(negociacao: Negociacao): void{
    this.listaNegociacoes.push(negociacao);
  }

  listar(): Array<Negociacao> {
    return this.listaNegociacoes;
  }
}