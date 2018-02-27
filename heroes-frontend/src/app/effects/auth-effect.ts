import {Injectable} from "@angular/core";
import {AuthAction, AUTHENTICATE} from "../actions/actions";
import {Actions, Effect} from "@ngrx/effects";
import {Action} from "@ngrx/store";
import {Observable} from "rxjs/Observable";
import {AuthRequest} from "../model/auth-request";
import {AuthService} from "../services/auth.service";
import 'rxjs/add/operator/do'

@Injectable()
export class AuthEffects {

  constructor(private actions$: Actions<AuthAction>,
              private authService: AuthService) {
  }

  @Effect() fightHeroes: Observable<Action> = this.actions$
    .ofType(AUTHENTICATE)
    .map(action => action.payload as AuthRequest)
    .switchMap(opponents => this.authService.auth(opponents))
    .do(response => {
      localStorage.setItem("accessToken", response.accessToken);
      localStorage.setItem("expireAt", String(response.expireTime));
      localStorage.setItem("refreshToken", response.refreshToken);
    })
    .map(authResponse => AuthAction.authSuccess(authResponse));

}
