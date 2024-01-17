//"T" é o tipo que quem herdar deve definir. Poderiamos ter mais de um tipo, por exemplo: View<T1,T2>... caso os metodos 
//recebessem parametros de tipos diferentes, então template receberia T1 e update T2 por exemplo.
//Classe Abstrata não pode ser instanciada através do operador "new", somente pode ser herdada. Ela pode nenhum, um ou vários
//métodos "abstratos". Quando um método é abstrato, ele passa a responsabilidade de implementação para quem herdar a classe.
export abstract class View<T> {

  //"Protected" para quem herdar poder acessar esta propriedade
  protected elemento: HTMLElement;

  constructor(seletor: string) {
    this.elemento = document.querySelector(seletor) as HTMLElement;
  }

  //Protected pois somente os filhos que são obrigados a implementa-lo devem ter acesso a ele.
  //Colocar "protected" nos filhos quando implementarem tb para restringir o acesso.
  protected abstract template(model: T): string;

  public update(model: T): void {
    this.elemento.innerHTML = this.template(model);
  }
}