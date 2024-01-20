import { NegociacaoApi } from "../interfaces/negociacao-api.js";
import { Negociacao } from "../models/negociacao.js";

export class NegociacaoService {

    public obterNegociacoesApi(): Promise<Negociacao[]> {
        return fetch('http://localhost:8080/dados')
            .then(response => {
                return response.json();
            })
            .then((dados: NegociacaoApi[]) => {
                return dados.map(elemento => {
                    return new Negociacao(new Date(), elemento.vezes, elemento.montante);
                });
            })
            .catch(error => {
                throw Error('Erro ao importar dados da API:', error.message);
            });
    }
}