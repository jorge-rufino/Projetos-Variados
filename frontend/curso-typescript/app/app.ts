import { NegociacaoController } from "./controllers/negogicao-controller.js";
import { NegociacoesView } from "./views/negociacoes-view.js";

const controller =  new NegociacaoController();

const form = document.querySelector('.form');
form!.addEventListener('submit', (evento) => {
  evento.preventDefault();
  controller.adiciona();  
})