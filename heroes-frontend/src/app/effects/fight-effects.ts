import {Injectable} from "@angular/core";
import {FightAction, HERO_FIGHT} from "../actions/actions";
import {Actions, Effect} from "@ngrx/effects";
import {Action} from "@ngrx/store";
import {Observable} from "rxjs/Observable";
import {FightService} from "../services/fight.service";
import {OpponentHeroes} from "../model/opponent-heroes";

@Injectable()
export class FightEffects {

  constructor(private actions$: Actions<FightAction>,
              private fightService: FightService) {
  }

  @Effect() fightHeroes: Observable<Action> = this.actions$
    .ofType(HERO_FIGHT)
    .map(action => action.payload as OpponentHeroes)
    .switchMap(opponents => this.fightService.fightHeroes(opponents))
    .map(fightResult => FightAction.fightHeroesResult(fightResult));

}
