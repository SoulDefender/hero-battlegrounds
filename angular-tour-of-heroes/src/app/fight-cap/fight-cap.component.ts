import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {Environment, Hero} from "../hero";
import {Observable} from "rxjs/Observable";
import {HeroStore} from "../reducers/heroes";
import {FightResult} from "../fight-result";
import {FightAction} from "../actions/actions";

@Component({
  selector: 'app-fight-cap',
  templateUrl: './fight-cap.component.html',
  styleUrls: ['./fight-cap.component.css']
})
export class FightCapComponent implements OnInit {
  hero: Hero;
  opponent: Hero;
  fightResult$: Observable<FightResult>;

  constructor(public store: Store<HeroStore>) {
  }

  ngOnInit(): void {
    this.fightResult$ = this.store.select('fightResult');
  }

  heroSelected(hero: Hero) {
    this.hero = hero;
  }

  opponentSelected(hero: Hero) {
    this.opponent = hero;
  }

  fight() {
    this.store.dispatch(FightAction.fightHeroes(this.hero,
      this.opponent, Environment.MOUNTAINS))
  }

}
