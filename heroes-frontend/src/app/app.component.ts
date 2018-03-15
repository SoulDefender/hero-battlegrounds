import {Component, OnInit} from '@angular/core';
import {environment} from '../environments/environment';
import {Observable} from "rxjs/Observable";
import {AppStore} from "./reducers/app-store";
import {Store} from "@ngrx/store";
import {HeroActions} from "./actions/actions";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Heroes Battleground';
  production = environment.production;
  authenticated$: Observable<boolean>;

  constructor(private store: Store<AppStore>) {
  }

  ngOnInit() {
    this.authenticated$ =
      this.store.select('auth').map(authInfo => authInfo.authenticated);
    this.store.dispatch(HeroActions.loadHeroes());
  }

}
