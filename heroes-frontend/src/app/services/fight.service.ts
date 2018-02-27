import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {of} from "rxjs/observable/of";
import { environment } from '../../environments/environment';
import {Observable} from "rxjs/Observable";
import {MessageService} from "./message.service";
import {Service} from "./service";
import {OpponentHeroes} from "../model/opponent-heroes";
import {FightResult} from "../model/fight-result";

@Injectable()
export class FightService extends Service {

  private fightUrl = `${environment.appUrl}/api/heroes/fight`;

  constructor(private messageService: MessageService, private http: HttpClient) {
    super()
  }

  fightHeroes(opponents: OpponentHeroes): Observable<FightResult> {

    // return this.http.post<FightResult>(this.fightUrl, opponents, httpOptions).pipe(
    //   tap(() => this.log(`fought hero w/ hero=${opponents.hero.name}
    //   opponent=${opponents.opponent.name}`)),
    //   catchError(this.handleError<FightResult>(`fightHeroes`))
    // );
    return of(new FightResult(opponents.opponent, opponents.hero, 98))
  }

  /** Log a HeroService message with the MessageService */
  protected log(message: string) {
    this.messageService.add('HeroService: ' + message);
  }

}
