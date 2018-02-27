import {Hero} from "../model/hero";
import {FightResult} from "../model/fight-result";
import {AuthInfo} from "../model/auth";

export interface AppStore {
  heroes: Hero[];
  fightResult: FightResult;
  auth: AuthInfo;
}
