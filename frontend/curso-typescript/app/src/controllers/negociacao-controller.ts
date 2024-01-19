import { domInjector, mostrarTempoExecucao } from "../decorators/decorators.js";
import { DiaDaSemana } from "../enums/dias-da-semana.js";
import { Negociacao } from "../models/negociacao.js";
import { Negociacoes } from "../models/negociacoes.js";
import { MensagemView } from "../views/mensagem-view.js";
import { NegociacoesView } from "../views/negociacoes-view.js";

export class NegociacaoController {

  @domInjector('#data')
  private inputData: HTMLDataElement;
  @domInjector('#quantidade')
  private inputQuantidade: HTMLInputElement;
  @domInjector('#valor')
  private inputValor: HTMLInputElement;
  @domInjector('.form')
  private formulario: HTMLFormElement;
  
  private negociacoes = new Negociacoes();
  private negociacoesView = new NegociacoesView('#negociacoesView');
  private mensagemView = new MensagemView('#mensagemView');  

  constructor() {    
    this.negociacoesView.update(this.negociacoes);
  }

  @mostrarTempoExecucao()
  public adiciona(): void {       
    const negociacao = Negociacao.criaNegociacao(
      this.inputData.value,
      this.inputQuantidade.value,
      this.inputValor.value
    );
    
    if (!this.diaUtil(negociacao.data)){
      this.mensagemView.update('Apenas negociações em dias úteis serão aceitas!');
      return;
    } 

    this.negociacoes.adicionar(negociacao);
    this.limparFormulario();
    this.atualizaView();    
  }
  
  private diaUtil(data: Date){
    return data.getDay() > DiaDaSemana.DOMINGO 
        && data.getDay() < DiaDaSemana.SABADO;
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
