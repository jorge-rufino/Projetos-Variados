import { inspecionarMetodo, mostrarTempoExecucao } from "../decorators/decorators.js";

//métodos "abstratos". Quando um método é abstrato, ele passa a responsabilidade de implementação para quem herdar a classe.
export abstract class View<T> {

  //"Protected" para quem herdar poder acessar esta propriedade
  protected elemento: HTMLElement;
  private escapar = false;

  //Adicionamos o "?" no parametro "escapar" tornando ele OPCIONAL, assim não quebra o código obrigando
  //que ele seja passado nas classes filhas.
  //Poderiamos também passar um valor padrão para "escapar"(escapar: boolean=false), assim evitariamos de fazer o "if" e 
  //ele continuaria sendo opcional.
  constructor(seletor: string, escapar?: boolean) {
    this.elemento = document.querySelector(seletor) as HTMLElement;

    if(!this.elemento){
      throw Error(`Seletor ${seletor} não existe no DOM. Verifique o código.`);
    }

    if(escapar) {
      this.escapar = escapar;
    }
  }

  //Protected pois somente os filhos que são obrigados a implementa-lo devem ter acesso a ele.
  //Colocar "protected" nos filhos quando implementarem tb para restringir o acesso.
  protected abstract template(model: T): string;
  
  //Perceba que a ordem que os 'decorators' são executados parece está invertida porém eles são executados de cima pra baixo.
  //Porém ao executar "inspecionarMetodo", dentro dele é chamado o método "update" antes de fazer o retorno, e o "update" tem o 
  //"mostrarTempoExecucao" como decorator, então ele vai executar este decorator primeiro para poder finalizar o "inspecionarMetodo"
  @inspecionarMetodo() 
  @mostrarTempoExecucao(true) 
  public update(model: T): void {
    let template = this.template(model);
    if(this.escapar){
      template = template.replace(/<script>[\s\S]*?<\/script>/,'');
    }

    this.elemento.innerHTML = template;
  }   
}

