import {of} from "rxjs/observable/of";
import {Observable} from "rxjs/Observable";

export abstract class Service {

  protected handleError<T> (operation = 'operation', result?: T): (error: any) => Observable<T> {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error);

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of (result as T);
    };
  }

  protected abstract log(message: string);

}
