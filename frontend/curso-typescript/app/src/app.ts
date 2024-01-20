import { NegociacaoController } from "./controllers/negociacao-controller.js";

const controller =  new NegociacaoController();
const btnImportar = document.querySelector('#botao-importar');

const form = document.querySelector('.form');
form!.addEventListener('submit', (evento) => {
  evento.preventDefault();
  controller.adiciona();  
})

if(btnImportar){
  btnImportar.addEventListener('click', () =>{
    controller.importarDadosApi();
  });

}else {
  throw Error('Botão não encontrado.');
}