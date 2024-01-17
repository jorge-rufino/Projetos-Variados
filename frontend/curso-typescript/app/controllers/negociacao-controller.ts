import { Negociacao } from "../models/negociacao.js";
import { Negociacoes } from "../models/negociacoes.js";
import { MensagemView } from "../views/mensagem-view.js";
import { NegociacoesView } from "../views/negociacoes-view.js";

export class NegociacaoController {
  private inputData: HTMLDataElement;
  private inputQuantidade: HTMLInputElement;
  private inputValor: HTMLInputElement;
  private formulario: HTMLFormElement;
  private negociacoes = new Negociacoes();
  private negociacoesView = new NegociacoesView('#negociacoesView');
  private mensagemView = new MensagemView('#mensagemView');
  private DOMINGO = 0;
  private SABADO = 6;

  constructor() {
    this.inputData = document.querySelector('#data') as HTMLDataElement;
    this.inputQuantidade = document.querySelector('#quantidade') as HTMLInputElement;
    this.inputValor = document.querySelector('#valor') as HTMLInputElement;
    this.formulario = document.querySelector('.form') as HTMLFormElement;
    this.negociacoesView.update(this.negociacoes);
  }

  public adiciona(): void {        
    const negociacao = this.criaNegociacao();
    
    if (!this.diaUtil(negociacao.data)){
      this.mensagemView.update('Apenas negociações em dias úteis serão aceitas!');
      return;
    } 

    this.negociacoes.adicionar(negociacao);
    this.limparFormulario();
    this.atualizaView();    
  }
  
  private diaUtil(data: Date){
    return data.getDay() > this.DOMINGO && data.getDay() < this.SABADO;
  }

  private criaNegociacao(): Negociacao {
    const data = new Date(this.inputData.value + 'T00:00:00');    
    const quantidade = this.inputQuantidade.valueAsNumber;
    const valor = this.inputValor.valueAsNumber;
    return new Negociacao(data, quantidade, valor);    
  }

  private limparFormulario(): void {
    this.formulario.reset();
    this.inputData.focus();
  }

  private atualizaView(): void{
    this.negociacoesView.update(this.negociacoes);
    this.mensagemView.update('Negociação adicionada com sucesso!');
  }
}
