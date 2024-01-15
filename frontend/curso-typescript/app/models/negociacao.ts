export class Negociacao {
  //O "_" é uma convenção javascript para indicar que os campos são privados. Não é obrigatório.
  private _data;
  private _quantidade;
  private _valor;

  constructor(data: any, quantidade: any, valor: any){
    this._data = data;
    this._quantidade = quantidade;
    this._valor = valor;  
  }

  //Temos 2 modos de fazer MÉTODOS "get"
  //Neste primeiro modo, quando formos chamar os métodos, iremos usar a sintaxe padrão de acessu a um atributo, sem parenteses.
  //Por exemplo: "negociacao.volume"
  get data(){
    return this._data;
  }

  get quantidade(){
    return this._quantidade;
  }

  get valor(){
    return this._valor;
  }

  //Perceba que volume é um método, não existe um atributo de classe "volume".
  get volume(){
    return this._quantidade * this._valor;
  }

  //Aqui temos os métodos "get" de maneira convencional
  getData() {
    return this._data;
  }

  getQuantidade(){
    return this._quantidade;
  }

  getValor(){
    return this._valor;
  }
}