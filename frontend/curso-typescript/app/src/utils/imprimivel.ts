//Mudei o nome do método pois usando "toString" o método não estava sendo obrigado a ser implementado. Creio que já exista
//um método ou outra componente com este no no TS.
//Alterando a classe para "Interface", teremos a obrigacao de implementar os métodos e atributos, teremos o efeito do Polimorfismo
//e podemos ter váris Interfaces numa mesma classe, enquanto Herança só é permitido uma no TS. Então só devemos usar herança
//quando tivermos uma classe com métodos que tenham alguma função como no cado da "View.ts".
export interface Imprimivel {        
    paraTexto(): string;
}