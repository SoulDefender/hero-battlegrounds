import {Component, OnInit} from '@angular/core';
import {HeroService} from '../hero.service';
import {Hero} from '../hero';
import {HeroStore} from "../reducers/heroes";
import {Store} from "@ngrx/store";
import {Observable} from "rxjs/Observable";
import {HeroActions} from "../actions/actions";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  heroes$: Observable<Hero[]>;

  constructor(private heroService: HeroService, private store: Store<HeroStore>) { }

  ngOnInit() {
    this.getHeroes();
    this.heroes$ = this.store.select('heroes').map(heroes => heroes.slice(0, 4))
  }

  getHeroes(): void {
    this.store.dispatch(HeroActions.loadHeroes());
  }
}
