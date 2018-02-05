import { Component, OnInit } from '@angular/core';
import {Hero} from '../hero';
import {HeroService} from '../hero.service';
import { Store, select } from '@ngrx/store';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-heroes',
  templateUrl: './heroes.component.html',
  styleUrls: ['./heroes.component.css']
})
export class HeroesComponent implements OnInit {
  heroes$: Observable<Hero[]>;

  constructor(private heroService: HeroService, private store: Store<{heroes: Hero[]}>) {
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
