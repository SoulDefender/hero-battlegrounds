import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {debounceTime, distinctUntilChanged, switchMap} from "rxjs/operators";
import {LookupFn} from "ng2-semantic-ui";
import {Store} from "@ngrx/store";
import {Subject} from "rxjs/Subject";
import {HeroStore} from "../../reducers/heroes";
import {Observable} from "rxjs/Observable";
import {Hero} from "../../hero";

@Component({
  selector: 'app-hero-dropdown',
  templateUrl: './hero-dropdown.component.html',
  styleUrls: ['./hero-dropdown.component.css']
})
export class HeroDropdownComponent implements OnInit {

  heroes$: Observable<Hero[]>;
  heroSelect = new Subject<string>();
  hero: Hero;
  @Output() selectedHero = new EventEmitter<Hero>();

  constructor(private store: Store<HeroStore>) {
  }

  ngOnInit(): void {
    this.heroes$ = this.createHeroObs(this.heroSelect);
  }

  heroChanged(hero: Hero) {
    this.selectedHero.emit(hero);
  }

  heroLookup: LookupFn<Hero, string> = (query, initial?) => {
    if (initial !== undefined) {
      return this.heroes$.toPromise();
    }
    this.heroSelect.next(query);
    return new Promise<Hero[]>((resolve) => {
      this.heroes$.subscribe(heroes => resolve(heroes));
    });
  };

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

}
