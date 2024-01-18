import { inspecionarMetodo, mostrarTempoExecucao } from "../decorators/decorators.js";

//métodos "abstratos". Quando um método é abstrato, ele passa a responsabilidade de implementação para quem herdar a classe.
export abstract class View<T> {

  //"Protected" para quem herdar poder acessar esta propriedade
  protected elemento: HTMLElement;  

  constructor(seletor: string) {
    this.elemento = document.querySelector(seletor) as HTMLElement;

    if(!this.elemento){
      throw Error(`Seletor ${seletor} não existe no DOM. Verifique o código.`);
    }

  }

  //Protected pois somente os filhos que são obrigados a implementa-lo devem ter acesso a ele.
  //Colocar "protected" nos filhos quando implementarem tb para restringir o acesso.
  protected abstract template(model: T): string;
  
  //Perceba que a ordem que os 'decorators' são executados parece está invertida porém eles são executados de cima pra baixo.
  //Porém ao executar "inspecionarMetodo", dentro dele é chamado o método "update" antes de fazer o retorno, e o "update" tem o 
  //"mostrarTempoExecucao" como decorator, então ele vai executar este decorator primeiro para poder finalizar o "inspecionarMetodo"
  @inspecionarMetodo 
  @mostrarTempoExecucao(true)   
  public update(model: T): void {
    let template = this.template(model);
    this.elemento.innerHTML = template;
  }   
}

