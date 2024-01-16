export class Negociacao {
  
  //Definimos as propriedades de classe como "public" e "readyonly", assim depois de atribuitos seus valores pelo construtor
  //não será mais possível altera-los.
  constructor(
    public readonly data: Date, 
    public readonly quantidade: number, 
    public readonly valor: number){}

  get volume(): number{
    return this.quantidade * this.valor;
  }
}