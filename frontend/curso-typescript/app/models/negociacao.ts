export class Negociacao {
  //O "#" indica que os campos são privados. É o mesmo que usar o "private"
  #data;
  #quantidade;
  #valor;

  constructor(data: any, quantidade: any, valor: any){
    this.#data = data;
    this.#quantidade = quantidade;
    this.#valor = valor;  
  }

  //Temos 2 modos de fazer MÉTODOS "get"
  //Neste primeiro modo, quando formos chamar os métodos, iremos usar a sintaxe padrão de acessu a um atributo, sem parenteses.
  //Por exemplo: "negociacao.volume"
  get data(){
    return this.#data;
  }

  get quantidade(){
    return this.#quantidade;
  }

  get valor(){
    return this.#valor;
  }

  //Perceba que volume é um método, não existe um atributo de classe "volume".
  get volume(){
    return this.#quantidade * this.#valor;
  }

  //Aqui temos os métodos "get" de maneira convencional
  getData() {
    return this.#data;
  }

  getQuantidade(){
    return this.#quantidade;
  }

  getValor(){
    return this.#valor;
  }
}