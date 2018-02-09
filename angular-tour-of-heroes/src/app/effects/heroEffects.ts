import {Injectable} from "@angular/core";
import 'rxjs/add/operator/switchMap';
import 'rxjs/add/operator/map';
import {Actions, Effect} from "@ngrx/effects";
import {HeroService} from "../hero.service";
import {Action} from "@ngrx/store";
import { of } from "rxjs/observable/of";
import {ADD_HERO, DELETE_HERO, HeroActions, LOAD_HERO_BY_ID, LOAD_HEROES, UPDATE_HERO} from "../actions/actions";
import {Hero} from "../hero";
import {Observable} from "rxjs/Observable";

@Injectable()
export class HeroEffects {

  constructor(
    private actions$: Actions<HeroActions>,
    private heroService: HeroService
  ) {}

  @Effect() loadHeroes$: Observable<Action> = this.actions$
    .ofType(LOAD_HEROES)
    .switchMap(() => this.heroService.getHeroes())
    .map(heroes => HeroActions.loadHeroesSuccess(heroes));

  @Effect() addHero$ = this.actions$
    .ofType(ADD_HERO)
    .map(action => action.payload as Hero)
    .switchMap(hero => this.heroService.addHero(hero))
    .map(hero => HeroActions.addHeroSuccess(hero));

  @Effect() updateHero$ = this.actions$
    .ofType(UPDATE_HERO)
    .map(action => action.payload as Hero)
    .switchMap(hero => this.heroService.updateHero(hero))
    .map(hero => HeroActions.updateHeroSuccess(hero));

  @Effect() deleteHero$ = this.actions$
    .ofType(DELETE_HERO)
    .map(action => action.payload as number)
    .switchMap(id => {
      this.heroService.deleteHero(id);
      return of({id} as Hero)
    })
    .map(hero => HeroActions.deleteHeroSuccess(hero.id));

  @Effect() loadHeroById$ = this.actions$
    .ofType(LOAD_HERO_BY_ID)
    .map(action => action.payload as number)
    .switchMap(id => this.heroService.getHero(id))
    .map(hero => HeroActions.loadHeroByIdSuccess(hero));

}
