import { Negociacao } from "../models/negociacao.js";

export class NegociacaoController {
  private inputData: HTMLInputElement;
  private inputQuantidade: HTMLInputElement;
  private inputValor: HTMLInputElement;

  constructor() {
    this.inputData = document.querySelector('#data') as HTMLInputElement;
    this.inputQuantidade = document.querySelector('#quantidade') as HTMLInputElement;
    this.inputValor = document.querySelector('#valor') as HTMLInputElement;
  }

  adiciona(): void {        
    const negociacao = this.criaNegociacao();
    console.log(negociacao);
  }
  
  criaNegociacao(): Negociacao {
    const data = new Date(this.inputData.value + 'T00:00:00');    
    const quantidade = this.inputQuantidade.valueAsNumber;
    const valor = this.inputValor.valueAsNumber;
    return new Negociacao(data, quantidade, valor);    
  }
}
