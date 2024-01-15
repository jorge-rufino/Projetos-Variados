import { Negociacao } from "../models/negociacao.js";

export class NegociacaoController {
  private inputData: HTMLDataElement;
  private inputQuantidade: HTMLInputElement;
  private inputValor: HTMLInputElement;

  constructor() {
    this.inputData = document.querySelector('#data') as HTMLDataElement;
    this.inputQuantidade = document.querySelector('#quantidade') as HTMLInputElement;
    this.inputValor = document.querySelector('#valor') as HTMLInputElement;
  }

  adiciona() {    
    const exp = /-/g;
    const data = new Date(this.inputData.value.replace(exp,','));    
    const quantidade = parseInt(this.inputQuantidade.value);
    const valor = parseFloat(this.inputValor.value);

    const negociacao = new Negociacao(data, quantidade, valor);

    console.log(negociacao);
  }
}
