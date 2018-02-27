import { Injectable } from '@angular/core';
import {Store} from "@ngrx/store";
import {AppStore} from "../reducers/app-store";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {Observable} from "rxjs/Observable";
import {AuthInfo} from "../model/auth";

@Injectable()
export class AuthInterceptor implements HttpInterceptor{

  auth$: Observable<AuthInfo>;

  constructor(private store: Store<AppStore>) {
    this.auth$ = this.store.select('auth');
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authInfo: AuthInfo = new AuthInfo(false, '');
    this.auth$.subscribe(
      ai => {
        if (ai) {
          authInfo = ai
        }
      }
    );

    if (authInfo.authenticated) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${authInfo.token}`
        }
      })
    }
    return next.handle(req);
  }



}
