export class Negociacao {
  
  //Definindo os parametros do construtor como "private", o TS automaticamente irá criar propriedades de classe com os mesmos nomes
  //Deste modo, nos economizar ter que digitar as propriedades de classe e fazer a atribuiçao com os parametros do construtor.
  constructor(
    private _data: Date, 
    private _quantidade: number, 
    private _valor: number){}

  //Temos 2 modos de fazer MÉTODOS "get"
  //Neste primeiro modo, quando formos chamar os métodos, iremos usar a sintaxe padrão de acessu a um atributo, sem parenteses.
  //Por exemplo: "negociacao.volume"
  get data(): Date{
    return this._data;
  }

  get quantidade(): number{
    return this._quantidade;
  }

  get valor(): number{
    return this._valor;
  }

  //Perceba que volume é um método, não existe um atributo de classe "volume".
  get volume(): number{
    return this._quantidade * this._valor;
  }

  //Aqui temos os métodos "get" de maneira convencional
  getData(): Date {
    return this._data;
  }

  getQuantidade(): number{
    return this._quantidade;
  }

  getValor(): number{
    return this._valor;
  }
}