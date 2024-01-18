import { MensagemView } from "../views/mensagem-view";

//Função padrão de decorator que aceita parametros. Usa parenteses. Ex. "@mostrarTempoExecucao()"
export function mostrarTempoExecucao(emSegundos: boolean = false) {
  return function (
    target: any,
    propertyKey: string,
    descriptor: PropertyDescriptor
  ) {

    //Guardamos o método original
    const metodoOriginal = descriptor.value;

    //sobrescrevemos o método original por este, e como o método orinal pode ter N parametros, usamos um array para recebe-los
    descriptor.value = function (...args: any[]) {

      let unidade = 'milisegundos';
      let divisor = 1;

      if (emSegundos) {
        unidade = 'segundos';
        divisor = 1000;
      }

      const t1 = performance.now();

      //Executamos o método original passando a lista de argumentos
      //"this" é o contexto, por exemplo, no método "update" quando ele é chamado por "NegociacoesView" este será o contexto,
      //Quando ele for chamado por "MensagemView" este será seu contexto, e caso seja chamado no controller, este será o contexto.
      const retorno = metodoOriginal.apply(this, args);

      const t2 = performance.now();
      console.log(`Metodo ${propertyKey}(), tempo de execução: ${(t2 - t1) / divisor} ${unidade}.`);

      //Precisamos do "return" para retornar a execução do método original. Em métodos "void a falta de retorno não causa problemas
      //Mas em métodos com retorno, causaria problemas na aplicação.
      return retorno;
    };

    return descriptor;
  }
}

//Função simplificada. Ao decorar um método não usa parenteses. Ex. "@inspecionarMetodo"
export function inspecionarMetodo(
  target: any,
  propertyKey: string,
  descriptor: PropertyDescriptor
) {

  const metodoOriginal = descriptor.value;

  descriptor.value = function (...args: any[]) {

    const retorno = metodoOriginal.apply(this, args);

    console.log(`
        --- Método: ${propertyKey}
        --- Parâmetros: ${JSON.stringify(args)}
        --- Retorno: ${JSON.stringify(retorno)}
      `);

    return retorno;
  }

  return descriptor;
}

export function escapar(target: any, propertyKey: string, descriptor: PropertyDescriptor) {
  const metodoOriginal = descriptor.value;
 
  descriptor.value = function (...args: any[]) {
    let retorno = metodoOriginal.apply(this, args);
    
    //Verifica se o retorno é uma String
    if(typeof retorno === 'string'){
      retorno =  retorno.replace(/<script>[\s\S]*?<\/script>/,'');      
    }
    
    return retorno;
  }

  return descriptor;
}