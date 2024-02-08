import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-botao',
  templateUrl: './botao.component.html',
  styleUrls: ['./botao.component.css']
})
export class BotaoComponent implements OnInit {

  //Estava variavel irá receber seus dados pelo componente Pai (AppComponent)
  @Input()
  btnConfigs: any;

  constructor() { }

  ngOnInit(): void {
  }

  alert() {
    alert(this.btnConfigs.titulo);
  }

}
