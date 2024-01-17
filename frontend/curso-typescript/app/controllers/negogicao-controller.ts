import { Negociacao } from "../models/negociacao.js";
import { Negociacoes } from "../models/negociacoes.js";
import { NegociacoesView } from "../views/negociacoes-view.js";

export class NegociacaoController {
  private inputData: HTMLDataElement;
  private inputQuantidade: HTMLInputElement;
  private inputValor: HTMLInputElement;
  private formulario: HTMLFormElement;
  private negociacoes = new Negociacoes();
  private negociacoesView = new NegociacoesView('#negociacoesView');

  constructor() {
    this.inputData = document.querySelector('#data') as HTMLDataElement;
    this.inputQuantidade = document.querySelector('#quantidade') as HTMLInputElement;
    this.inputValor = document.querySelector('#valor') as HTMLInputElement;
    this.formulario = document.querySelector('.form') as HTMLFormElement;
    this.negociacoesView.update(this.negociacoes);
  }

  adiciona(): void {        
    const negociacao = this.criaNegociacao();
    this.negociacoes.adicionar(negociacao);        
    this.negociacoesView.update(this.negociacoes);
    this.limparFormulario();
  }
  
  criaNegociacao(): Negociacao {
    const data = new Date(this.inputData.value + 'T00:00:00');    
    const quantidade = this.inputQuantidade.valueAsNumber;
    const valor = this.inputValor.valueAsNumber;
    return new Negociacao(data, quantidade, valor);    
  }

  limparFormulario(): void {
    this.formulario.reset();
    this.inputData.focus();
  }
}
