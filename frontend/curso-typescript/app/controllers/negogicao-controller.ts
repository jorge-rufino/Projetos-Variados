import { Negociacao } from "../models/negociacao.js";
import { Negociacoes } from "../models/negociacoes.js";

export class NegociacaoController {
  private inputData: HTMLDataElement;
  private inputQuantidade: HTMLInputElement;
  private inputValor: HTMLInputElement;
  private formulario: HTMLFormElement;
  private negociacoes = new Negociacoes();

  constructor() {
    this.inputData = document.querySelector('#data') as HTMLDataElement;
    this.inputQuantidade = document.querySelector('#quantidade') as HTMLInputElement;
    this.inputValor = document.querySelector('#valor') as HTMLInputElement;
    this.formulario = document.querySelector('.form') as HTMLFormElement;
  }

  adiciona(): void {        
    const negociacao = this.criaNegociacao();
    this.negociacoes.adicionar(negociacao);        
    
    //Como alteramos o retorno do metodo "listar", se tentarmos usar metodos que modificam o array, dará erro.
    //this.negociacoes.listar().pop()
    console.log(this.negociacoes.listar());
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
