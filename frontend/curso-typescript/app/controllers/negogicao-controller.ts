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

  constructor() {
    this.inputData = document.querySelector('#data') as HTMLDataElement;
    this.inputQuantidade = document.querySelector('#quantidade') as HTMLInputElement;
    this.inputValor = document.querySelector('#valor') as HTMLInputElement;
    this.formulario = document.querySelector('.form') as HTMLFormElement;
    this.negociacoesView.update(this.negociacoes);
  }

  public adiciona(): void {        
    const negociacao = this.criaNegociacao();

    //"getDay()" retorna qual o dia da semana. 0 é igual a domingo e 6 é igual sábado.
    if (negociacao.data.getDay() > 0 && negociacao.data.getDay() < 6){
        this.negociacoes.adicionar(negociacao);
        this.limparFormulario();
        this.atualizaView();
    } else {
      this.mensagemView.update('Apenas negociações em dias úteis serão aceitas!');
    }

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
