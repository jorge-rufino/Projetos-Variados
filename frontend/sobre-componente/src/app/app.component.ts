import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'sobre-componente';

  //Abaixo são os valores que iremos passar por Property Binding
  primary = {
    titulo: 'Editar',
    styles: { backgroundColor: '#007bff' }
  };

  secondary = {
    titulo: 'Desabilitado',
    styles: { backgroundColor: '#6c757d' }
  };

  success = {
    titulo: 'Salvar',
    styles: { backgroundColor: '#28a745' },
  };

  danger = {
    titulo: 'Deletar',
    styles: { backgroundColor: '#dc3545' }
  };
}
