import { Negociacoes } from "../models/negociacoes.js";

export class NegociacoesView {

  private elemento: HTMLElement;

  constructor(seletor: string){
    this.elemento = document.querySelector(seletor) as HTMLElement;
  }

  //O map retorna um array, então usamos o "join" para ele juntar tudo. O parametro serve para separar os arrays e como neste caso
  //queremos uni-los, passamos o separador '' vazio.
  //Template da tabela para mostrar as negociacoes
  template(model: Negociacoes): string {
    return ` 
    <table class="table table-hover table-bordered">
      <thead>
          <tr>
              <th>DATA</th>
              <th>QUANTIDADE</th>
              <th>VALOR</th>
          </tr>
      </thead>
      <tbody>
          ${model.listar().map(negociacao => {
            return `
              <tr>
                <td>${negociacao.data}</td>
                <td>${negociacao.quantidade}</td>
                <td>${negociacao.valor}</td>
              </tr>
            `;
          }).join('')}
      </tbody>
    </table>
    `;
  }

  update(model: Negociacoes): void {
    this.elemento.innerHTML = this.template(model);
  }
}