import {Hero} from "./hero";

export class FightResult {
  public constructor(public winner: Hero, public loser: Hero, public probability: number) {}
}
