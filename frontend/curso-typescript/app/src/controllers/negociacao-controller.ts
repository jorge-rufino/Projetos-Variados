import { domInjector, mostrarTempoExecucao } from "../decorators/decorators.js";
import { DiaDaSemana } from "../enums/dias-da-semana.js";
import { NegociacaoApi } from "../interfaces/negociacao-api.js";
import { Negociacao } from "../models/negociacao.js";
import { Negociacoes } from "../models/negociacoes.js";
import { NegociacaoService } from "../services/negociacao-service.js";
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
  private negociacaoService = new NegociacaoService();

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

  //Importa dados da API
  importarDadosApi(): void {
    this.negociacaoService
      .obterNegociacoesApi()
      .then(negociacoesApi => {
        for(let negociacao of negociacoesApi){
          this.negociacoes.adicionar(negociacao);
        }

        //Atualiza a view com os dados da API
        this.negociacoesView.update(this.negociacoes);
      })
      .catch(error => {
         throw Error ('Erro ao importar dados da API:', error.message);        
      });
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
