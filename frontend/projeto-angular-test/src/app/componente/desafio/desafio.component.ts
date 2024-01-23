import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-desafio',
  template: `
     <h2>Componente do desafio</h2>
    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Commodi recusandae, est ipsam odit porro tenetur doloribus corrupti quia ipsum, 
      nulla enim amet. Impedit ratione eos error inventore expedita blanditiis cumque.</p>
  `,
  styles: ['h2, p{color: blue}']
})
export class DesafioComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

}
