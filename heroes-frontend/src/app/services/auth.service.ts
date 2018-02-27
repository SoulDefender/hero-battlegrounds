import { Injectable } from '@angular/core';
import {of} from "rxjs/observable/of";
import {Observable} from "rxjs/Observable";
import {HttpClient} from "@angular/common/http";
import {MessageService} from "./message.service";
import {Service} from "./service";
import { environment } from '../../environments/environment';
import {AuthRequest} from "../model/auth-request";
import {AuthResponse} from "../model/auth";

@Injectable()
export class AuthService extends Service{

  private authUrl = `${environment.appUrl}/api/login`;

  constructor(private messageService: MessageService, private http: HttpClient) {
    super()
  }

  auth(request: AuthRequest): Observable<AuthResponse> {

    // return this.http.post<AuthResponse>(this.authUrl, request, httpOptions).pipe(
    //   catchError(this.handleError<AuthResponse>(`auth`))
    // );
    return of(new AuthResponse('asdasfqwoojdoj19j1ijdksajldj',
      new Date().getMilliseconds() + 3600000, 'akjajskadsjk'))
  }

  /** Log a HeroService message with the MessageService */
  protected log(message: string) {
    this.messageService.add('HeroService: ' + message);
  }

}
