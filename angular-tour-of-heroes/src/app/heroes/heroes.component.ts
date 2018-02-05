import { Component, OnInit } from '@angular/core';
import {Hero} from '../hero';
import {HeroService} from '../hero.service';
import { Store } from '@ngrx/store';
import { Observable } from 'rxjs/Observable';
import {HeroStore} from "../reducers/heroes";

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
    this.heroService.getHeroes().subscribe();
  }

  add(name: string): void {
    name = name.trim();
    if (!name) { return; }
    this.heroService.addHero({ name } as Hero).subscribe();
  }

  delete(hero: Hero): void {
    this.heroService.deleteHero(hero).subscribe();
  }
  ngOnInit() {
    this.getHeroes();
  }

}
