import { Injectable } from '@angular/core';
import { Store } from '@ngrx/store';
import { ADD_HERO, HeroActionWithPayload, UPDATE_HERO, DELETE_HERO, UPDATE_HEROES } from './reducers/heroes';
import { Hero } from './hero';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import { catchError, map, tap, flatMap } from 'rxjs/operators';
import { MessageService } from './message.service';

import { environment } from '../environments/environment';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class HeroService {

  private heroesUrl = `${environment.appUrl}/api/heroes`;

  constructor(private messageService: MessageService, private http: HttpClient,
    private store: Store<{heroes: Hero[]}>) { }

  getHeroes(): Observable<void> {
    return this.http.get<Hero[]>(this.heroesUrl)
    .pipe(
      tap(heroes => this.log(`fetched heroes`)),
      catchError(this.handleError('getHeroes', [])),
      flatMap(hero => {
        this.store.dispatch({type: UPDATE_HEROES, payload: hero});
        return of();
      })
    );
  }

  getHero(id: number): Observable<void> {
    const url = `${this.heroesUrl}/${id}`;
    return this.http.get<Hero>(url).pipe(
      tap(_ => this.log(`fetched hero with id=${id}`)),
      catchError(this.handleError<Hero>(`getHero id=${id}`)),
      flatMap(hero => {
        this.store.dispatch({type: UPDATE_HERO, payload: hero});
        return of();
      })
    );
  }

  /** GET hero by id. Return `undefined` when id not found */
  getHeroNo404<Data>(id: number): Observable<Hero> {
    const url = `${this.heroesUrl}/?id=${id}`;
    return this.http.get<Hero[]>(url)
      .pipe(
        map(heroes => heroes[0]), // returns a {0|1} element array
        tap(h => {
          const outcome = h ? `fetched` : `did not find`;
          this.log(`${outcome} hero id=${id}`);
        }),
        catchError(this.handleError<Hero>(`getHero id=${id}`))
      );
  }

  updateHero(hero: Hero): Observable<any> {
    return this.http.put(this.heroesUrl, hero, httpOptions).pipe(
      tap(_ => this.log(`updated hero with id=${hero.id}`)),
      catchError(this.handleError<any>(`updateHero`)),
      flatMap(_ => {
        this.store.dispatch({type: UPDATE_HERO, payload: hero});
        return of();
      })
    );
  }

  addHero(hero: Hero): Observable<Hero> {
    return this.http.post<Hero>(this.heroesUrl, hero, httpOptions).pipe(
      tap(_ => this.log(`added hero w/ id=${hero.id}`)),
      catchError(this.handleError<Hero>(`addHero`)),
      flatMap(_ => {
        this.store.dispatch({type: ADD_HERO, payload: hero});
        return of();
      })
    );
  }

  deleteHero(hero: Hero | number): Observable<Hero> {
    const id = typeof hero === 'number' ? hero : hero.id;
    const url = `${this.heroesUrl}/${id}`;

    return this.http.delete<Hero>(url, httpOptions).pipe(
      tap(_ => this.log(`deleted hero id=${id}`)),
      catchError(this.handleError<Hero>(`deleteHero`)),
      flatMap(_ => {
        this.store.dispatch({type: DELETE_HERO, payload: hero});
        return of();
      })
    );
  }

  searchHeroes(term: string): Observable<Hero[]> {
    if (!term.trim()) {
      return of([]);
    }
    return this.http.get<Hero[]>(`api/heroes/?name=${term}`).pipe(
      tap(_ => this.log(`found heroes matching ${term}`)),
      catchError(this.handleError<Hero[]>(`searchHeroes`, []))
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
