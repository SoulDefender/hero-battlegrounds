import {Component, OnInit} from '@angular/core';
import {Store} from "@ngrx/store";
import {debounceTime, distinctUntilChanged, switchMap} from "rxjs/operators";
import {Environment, Hero} from "../hero";
import {Observable} from "rxjs/Observable";
import {HeroStore} from "../reducers/heroes";
import {FightResult} from "../fight-result";
import {FightAction} from "../actions/actions";
import {Subject} from "rxjs/Subject";
import {LookupFn} from "ng2-semantic-ui";
import "rxjs/add/operator/toPromise";

@Component({
  selector: 'app-fight-cap',
  templateUrl: './fight-cap.component.html',
  styleUrls: ['./fight-cap.component.css']
})
export class FightCapComponent implements OnInit {
  heroes$: Observable<Hero[]>;
  opponentHeroes$: Observable<Hero[]>;
  heroSelect = new Subject<string>();
  opponentHeroSelect = new Subject<string>();
  selectedHero: Hero;
  selectedOpponent: Hero;
  fightResult$: Observable<FightResult>;

  heroLookup: LookupFn<Hero, string> = (query, initial?) => {
    if (initial !== undefined) {
      return this.heroes$.toPromise();
    }
    this.heroSelect.next(query);
    return new Promise<Hero[]>((resolve) => {
      this.heroes$.subscribe(heroes => resolve(heroes));
    });
  };

  opponentHeroLookup: LookupFn<Hero, string> = (query, initial?) => {
    if (initial !== undefined) {
      return this.opponentHeroes$.toPromise();
    }
    this.opponentHeroSelect.next(query);return new Promise<Hero[]>((resolve) => {
      this.opponentHeroes$.subscribe(heroes => resolve(heroes));
    });
  };

  constructor(private store: Store<HeroStore>) {
  }

  ngOnInit(): void {
    this.heroes$ = this.createHeroObs(this.heroSelect);
    this.opponentHeroes$ = this.createHeroObs(this.opponentHeroSelect);
    this.fightResult$ = this.store.select('fightResult');
  }

  private createHeroObs(subject: Subject<string>): Observable<Hero[]> {
    return subject.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),
      // ignore new term if same as previous term
      distinctUntilChanged(),
      // switch to new search observable each time the term changes
      switchMap((term: string) => this.store.select('heroes')
        .map(heroes => heroes.filter(h => h.name.startsWith(term))).take(10))
    );
  }

  fight() {
    this.store.dispatch(FightAction.fightHeroes(this.selectedHero,
      this.selectedOpponent, Environment.MOUNTAINS))
  }

}
