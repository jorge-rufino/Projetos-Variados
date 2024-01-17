//"T" é o tipo que quem herdar deve definir. Poderiamos ter mais de um tipo, por exemplo: View<T1,T2>... caso os metodos 
//recebessem parametros de tipos diferentes, então template receberia T1 e update T2 por exemplo.
//Classe Abstrata não pode ser instanciada através do operador "new", somente pode ser herdada. Ela pode nenhum, um ou vários
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

    if(escapar) {
      this.escapar = escapar;
    }
  }

  //Protected pois somente os filhos que são obrigados a implementa-lo devem ter acesso a ele.
  //Colocar "protected" nos filhos quando implementarem tb para restringir o acesso.
  protected abstract template(model: T): string;

  //Agora nós estamos protegendo o template para caso alguem mal intencionado tente passar um script nele
  //Ele seja removido
  public update(model: T): void {
    let template = this.template(model);
    if(this.escapar){
      template = template.replace(/<script>[\s\S]*?<\/script>/,'');
    }

    this.elemento.innerHTML = template;
  }
}