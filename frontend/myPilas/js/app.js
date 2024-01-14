var alternador = document.querySelector('.js-botao-chaveador');

//Quando clicar no botão, vai adicionar/remover a classe "menu--exibindo", que faz com que o menu apareça
alternador.onclick = function(){
  var menu = document.querySelector('.js-menu');
  menu.classList.toggle('menu--exibindo');  //Se já existir a classe ele remove, se não existir ele adiciona
};