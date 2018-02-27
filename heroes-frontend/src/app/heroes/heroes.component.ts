import { Component, OnInit } from '@angular/core';
import {Hero} from '../model/hero';
import {HeroService} from '../services/hero.service';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs/Observable';
import {HeroStore} from "../reducers/heroes";
import {HeroActions} from "../actions/actions";

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {
  heroes$: Observable<Hero[]>;

  constructor(private heroService: HeroService, private store: Store<HeroStore>) {
    this.heroes$ = store.select('heroes');
  }

  getHeroes(): void {
    this.store.dispatch(HeroActions.loadHeroes());
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    console.log("add hero");
    this.store.dispatch(HeroActions.addHero({ name } as Hero));
  }

  delete(hero: Hero): void {
    this.store.dispatch(HeroActions.deleteHero(hero.id));
  }

  ngOnInit() {
    this.getHeroes();
  }

}
