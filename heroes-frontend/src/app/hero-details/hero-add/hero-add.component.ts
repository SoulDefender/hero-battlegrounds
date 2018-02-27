import { Component, OnInit } from '@angular/core';
import {Hero} from "../../model/hero";
import {HeroStore} from "../../reducers/heroes";
import {Store} from "@ngrx/store";
import {HeroActions} from "../../actions/actions";
import {Location} from "@angular/common";

@Component({
  selector: 'app-hero-add',
  templateUrl: './hero-add.component.html',
  styleUrls: ['./hero-add.component.css']
})
export class HeroAddComponent implements OnInit {

  hero: Hero;

  constructor(private store: Store<HeroStore>,
              private location: Location) { }

  ngOnInit() {
    this.hero = Hero.defaultHero();
    this.hero.id = undefined;
  }

  saveHero(hero: Hero) {
    this.store.dispatch(HeroActions.addHero(hero));
    this.goBack();
  }

  goBack() {
    this.location.back()
  }

}
