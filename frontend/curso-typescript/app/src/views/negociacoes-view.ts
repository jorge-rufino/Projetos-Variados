import { escapar } from "../decorators/decorators.js";
import { Negociacoes } from "../models/negociacoes.js";
import { View } from "./view.js";

export class NegociacoesView extends View<Negociacoes>{

  //O map retorna um array, então usamos o "join" para ele juntar tudo. O parametro serve para separar os arrays e como neste caso
  //queremos uni-los, passamos o separador '' vazio.
  //"DateTimeFormat" poderia passar "pt-br" como parametro, porém deixamos vazio para ele utilizar a localizacao de acordo com navegador
  //A saída será no modelo DD/MM/AAA

  @escapar
  protected template(model: Negociacoes): string {
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
                <td>${this.formatarData(negociacao.data)}</td>
                <td>${negociacao.quantidade}</td>
                <td>${negociacao.valor}</td>
              </tr>
            `;
          }).join('')}          
      </tbody>
    </table>    
    `;
  }

  private formatarData(data: Date): string{
    return new Intl.DateTimeFormat().format(data);
  }
}