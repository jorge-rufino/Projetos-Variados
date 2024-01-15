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
    const data = new Date(this.inputData.value + 'T00:00:00');    
    const quantidade = this.inputQuantidade.valueAsNumber;
    const valor = this.inputValor.valueAsNumber;

    const negociacao = new Negociacao(data, quantidade, valor);

    console.log(negociacao);
  }
}
