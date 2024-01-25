import { Component } from "@angular/core";

@Component({
  selector: "app-root",
  template: ` 
    <div>
      <h1 [ngClass]="{'color-blue': active}">
        Home!
      </h1>
      <button (click)="colorBlue()">
          Mudar cor
      </button>
    </div>
    `,
  styles: [],
})
export class AppComponent {
  active: boolean = true;
 
  constructor() { }
 
  colorBlue(){
    this.active = !this.active
  }
}
