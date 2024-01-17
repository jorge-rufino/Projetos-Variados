//"T" é o tipo que quem herdar deve definir. Poderiamos ter mais de um tipo, por exemplo: View<T1,T2>... caso os metodos 
//recebessem parametros de tipos diferentes, então template receberia T1 e update T2 por exemplo.
export class View<T> {

  //"Protected" para quem herdar poder acessar esta propriedade
  protected elemento: HTMLElement;

  constructor(seletor: string) {
    this.elemento = document.querySelector(seletor) as HTMLElement;
  }

  template(model: T): string {
    throw Error('Classe filha precisa implementar template');
  }

  update(model: T): void {
    this.elemento.innerHTML = this.template(model);
  }
}