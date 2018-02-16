import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {of} from "rxjs/observable/of";
import {environment} from "../environments/environment";
import {catchError, tap} from "rxjs/operators";
import {Observable} from "rxjs/Observable";
import {MessageService} from "./message.service";
import {httpOptions} from "./hero.service";
import {OpponentHeroes} from "./opponent-heroes";
import {FightResult} from "./fight-result";

@Injectable()
export class FightService {

  private fightUrl = `${environment.appUrl}/api/heroes`;

  constructor(private messageService: MessageService, private http: HttpClient) { }

  fightHeroes(opponents: OpponentHeroes): Observable<FightResult> {
    return this.http.post<FightResult>(this.fightUrl, opponents, httpOptions).pipe(
      tap(() => this.log(`fought hero w/ hero=${opponents.hero.name} 
      opponent=${opponents.opponent.name}`)),
      catchError(this.handleError<FightResult>(`fightHeroes`))
    );
  }

  /**
   * Handle Http operation that failed.
   * Let the app continue.
   * @param operation - name of the operation that failed
   * @param result - optional value to return as the observable result
   */
  private handleError<T> (operation = 'operation', result?: T): (error: any) => Observable<T> {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error);

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of (result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add('HeroService: ' + message);
  }

}
