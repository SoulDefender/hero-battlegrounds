import { Component, OnInit } from '@angular/core';

import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { of } from "rxjs/observable/of";
import "rxjs/add/operator/take";

import { debounceTime, distinctUntilChanged, switchMap } from 'rxjs/operators';
import { Hero } from '../hero';
import {HeroStore} from "../reducers/heroes";
import {Store} from "@ngrx/store";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-hero-search',
  templateUrl: './hero-search.component.html',
  styleUrls: ['./hero-search.component.css']
})
export class HeroSearchComponent implements OnInit {
  heroes$: Observable<Hero[]>;
  searchTerms = new FormControl('');

  constructor(private store: Store<HeroStore>) {}

  ngOnInit(): void {
    this.heroes$ = this.searchTerms.valueChanges.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(300),

      // ignore new term if same as previous term
      distinctUntilChanged(),

      // switch to new search observable each time the term changes
      switchMap((term: string) => this.store.select('heroes')
        .map(heroes => heroes.filter(h => h.name.startsWith(term) && term !== '')).take(10))
    );
  }
}
