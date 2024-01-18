import { MensagemView } from "../views/mensagem-view";

export function mostrarTempoExecucao() {
  return function (
    target: any,
    propertyKey: string,
    descriptor: PropertyDescriptor
  ) {

    //Guardamos o método original
    const metodoOriginal = descriptor.value;

    //sobrescrevemos o método original por este, e como o método orinal pode ter N parametros, usamos um array para recebe-los
    descriptor.value = function(...args: any[]) {
      
      const t1 = performance.now();

      //Executamos o método original passando a lista de argumentos
      //"this" é o contexto, por exemplo, no método "update" quando ele é chamado por "NegociacoesView" este será o contexto,
      //Quando ele for chamado por "MensagemView" este será seu contexto, e caso seja chamado no controller, este será o contexto.
      const retorno = metodoOriginal.apply(this, args);

      const t2 = performance.now();
      console.log(`Metodo ${propertyKey}(), tempo de execução: ${(t2-t1) / 1000} segundos.`);

      retorno;
    };

    return descriptor;
  }
}