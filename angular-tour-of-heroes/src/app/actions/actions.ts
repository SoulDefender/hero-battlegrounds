import {Action} from "@ngrx/store";
import {Environment, Hero} from "../hero";
import {OpponentHeroes} from "../opponent-heroes";
import {FightResult} from "../fight-result";

export const LOAD_HEROES: string = '[GET] LOAD_HEROES';
export const LOAD_HEROES_SUCCESS: string = '[CHANGE] LOAD_HEROES_SUCCESS';
export const LOAD_HERO_BY_ID: string = '[GET] LOAD_HERO_BY_ID';
export const LOAD_HERO_BY_ID_SUCCESS: string = '[CHANGE] LOAD_HERO_BY_ID_SUCCESS';
export const SEARCH_HEROES_BY_NAME: string = '[GET] SEARCH_HERO_BY_NAME';
export const SEARCH_HEROES_BY_NAME_SUCCESS: string = '[CHANGE] SEARCH_HERO_BY_NAME_SUCCESS';
export const ADD_HERO: string = '[POST] ADD_HERO';
export const ADD_HERO_SUCCESS: string = '[CHANGE] ADD_HERO_SUCCESS';
export const UPDATE_HERO: string = '[PUT] UPDATE_HERO';
export const UPDATE_HERO_SUCCESS: string = '[CHANGE] UPDATE_HERO_SUCCESS';
export const DELETE_HERO: string = '[DELETE] DELETE_HERO';
export const DELETE_HERO_SUCCESS: string = '[CHANGE] DELETE_HERO_SUCCESS';
export const HERO_FIGHT: string = '[POST] HERO_FIGHT';
export const HERO_FIGHT_RESULT: string = '[CHANGE] HERO_FIGHT_RESULT';

export class HeroActions implements Action{

  static loadHeroes(): HeroActions {
    return new HeroActions(LOAD_HEROES);
  }

  static loadHeroesSuccess(heroes: Hero[]): HeroActions {
    return new HeroActions(LOAD_HEROES_SUCCESS, heroes);
  }

  static loadHeroById(id: number): HeroActions {
    return new HeroActions(LOAD_HERO_BY_ID, id);
  }

  static loadHeroByIdSuccess(hero: Hero): HeroActions {
    return new HeroActions(LOAD_HERO_BY_ID_SUCCESS, hero);
  }

  static searchHeroesByName(name: string) {
    return new HeroActions(SEARCH_HEROES_BY_NAME, name);
  }

  static searchHeroesByNameSuccess(heroes: Hero[]): HeroActions {
    return new HeroActions(SEARCH_HEROES_BY_NAME_SUCCESS, heroes);
  }

  static addHero(hero: Hero): HeroActions {
    return new HeroActions(ADD_HERO, hero);
  }

  static addHeroSuccess(hero: Hero): HeroActions {
    return new HeroActions(ADD_HERO_SUCCESS, hero);
  }

  static updateHero(hero: Hero): HeroActions {
    return new HeroActions(UPDATE_HERO, hero);
  }

  static updateHeroSuccess(hero: Hero): HeroActions {
    return new HeroActions(UPDATE_HERO_SUCCESS, hero);
  }

  static deleteHero(id: number): HeroActions {
    return new HeroActions(DELETE_HERO, id);
  }

  static deleteHeroSuccess(id: number): HeroActions {
    return new HeroActions(DELETE_HERO_SUCCESS, id);
  }

  constructor(public type: string, public payload?: number|string|Hero|Hero[]) {}

}

export class FightAction implements Action{

  constructor(public type: string, public payload?: OpponentHeroes | FightResult) {}


  static fightHeroes(hero: Hero, opponent: Hero, environment: Environment): FightAction {
    return new FightAction(HERO_FIGHT, new OpponentHeroes(hero, opponent, environment));
  }

  static fightHeroesResult(fightResult: FightResult) {
    return new FightAction(HERO_FIGHT_RESULT, fightResult);
  }

}
