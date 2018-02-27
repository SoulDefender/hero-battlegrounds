import {FightResult} from "../model/fight-result";
import {FightAction, HERO_FIGHT_RESULT} from "../actions/actions";

export function fightReducer(state: FightResult = undefined, action: FightAction) {
  switch (action.type) {
    case HERO_FIGHT_RESULT:
      return action.payload as FightResult;
  }
}
