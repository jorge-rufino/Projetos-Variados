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
export function inspecionarMetodo(target: any, propertyKey: string, descriptor: PropertyDescriptor
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
    if (typeof retorno === 'string') {
      retorno = retorno.replace(/<script>[\s\S]*?<\/script>/, '');
    }

    return retorno;
  }

  return descriptor;
}

//Pega o DOM do document e adiciona na variavel decorada.
export function domInjector(seletor: string) {
  return function (target: any, propertyKey: string) {
    //console.log(`Modificando prototype ${target.constructor.name} e adicionando getter para a propriedade ${propertyKey}`);

    let elemento: HTMLElement | null = null;

    //Chegando o elemento, evita de todas vez que for adicionada um negociação, a função seja executada.
    const getter = function() {
      if(!elemento){
        elemento = document.querySelector(seletor);
        console.log(`Buscando elemento DOM com o seletor ${seletor} para injetar em ${propertyKey}`);
      }      

      return elemento;      
    }

    Object.defineProperty(
      target, 
      propertyKey,
      { get: getter }
      );
  }
}

//Exemplo de decorator para modificar propriedade String
function MinLength(minLength: number) {
  return function (target: any, propertyKey: string) {
    let value = target[propertyKey];
    
    //Será chamado quando quisermos obter o valor da propriedade decorada
    const getter = () => {
     return value.toUpperCase();
    };

    //Será chamado quando quisermos atribuir um valor a propriedade decorada
    const setter = (newValue: string) => {
      if (newValue.length < minLength) {
        throw new Error(`O valor deve ter no mínimo ${minLength} caracteres.`);
      }
      value = newValue;
    };

    //Quando quisermos saber o valor da propriedade, o "get" será usado
    //Quando quisermos atribuir um valor para a propriedade, o "set" será usado
    Object.defineProperty(target, propertyKey, {
      get: getter,
      set: setter,
    });
  };
}

class Person {
  @MinLength(6)
  name: string;

  constructor(name: string) {
    this.name = name;
  }
}

//Perceba que quando estamos atribuindo um valor para a propriedade "name", o a função "setter" do decorator entrará em ação
//validando o tamanho minimo da string.
//Quando queremos saber o valor da propriedade "name", a função "getter" do decorator entrará em ação, fazendo com que a string
//fique toda em letra maiuscula.
const person = new Person("JoRGe"); // Erro: O valor deve ter no mínimo 6 caracteres.
console.log(person.name);
