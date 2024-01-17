export class Negociacao {
  
  //Definimos as propriedades de classe como "public" e "readyonly", assim depois de atribuitos seus valores pelo construtor
  //não será mais possível altera-los.
  constructor(
    private _data: Date, 
    public readonly quantidade: number, 
    public readonly valor: number){}

  //Como um objeto "Date" tem o metodo "setDate" que permite alterar uma data mesmo sendo "readonly", programamos defensivamente
  //criando uma nova data com base da data original para evitar alteraçoes no objeto/data original.
  get data(): Date {
    const data = new Date(this._data.getTime());
    return data;
  }

  get volume(): number{
    return this.quantidade * this.valor;
  }

  public static criaNegociacao(dataString: string, quantidadeString: string, valorString: string): Negociacao{
    const data = new Date(dataString + 'T00:00:00');    
    const quantidade = parseInt(quantidadeString);
    const valor = parseFloat(valorString);

    return new Negociacao(data, quantidade, valor);
  }
}