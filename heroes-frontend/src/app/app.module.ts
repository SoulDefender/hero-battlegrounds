import {BrowserModule} from '@angular/platform-browser';
import {APP_INITIALIZER, NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api';
import {InMemoryDataService} from './in-memory-data.service';


import {AppComponent} from './app.component';
import {HeroesComponent} from './heroes/heroes.component';
import {HeroDetailsComponent} from './hero-details/hero-details.component';

import {HeroService} from './services/hero.service';
import {MessagesComponent} from './messages/messages.component';
import {MessageService} from './services/message.service';
import {AppRoutingModule} from './app-routing.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {HeroSearchComponent} from './hero-search/hero-search.component';
import {Store, StoreModule} from '@ngrx/store';
import {EffectsModule} from "@ngrx/effects";
import {HeroEffects} from "./effects/hero-effects";
import {FightCapComponent} from './fight-cap/fight-cap.component';
import {SuiModule} from "ng2-semantic-ui";
import {HeroDropdownComponent} from './fight-cap/hero-dropdown/hero-dropdown.component';
import {FightEffects} from "./effects/fight-effects";
import {FightService} from "./services/fight.service";
import {HeroViewComponent} from './hero-view/hero-view.component';
import {HeroCharacteristicsComponent} from './hero-details/hero-characteristics/hero-characteristics.component';
import {HeroEditComponent} from './hero-details/hero-edit/hero-edit.component';
import {HeroAddComponent} from './hero-details/hero-add/hero-add.component';
import {AuthEffects} from "./effects/auth-effect";
import {AppStore} from "./reducers/app-store";
import {heroReducer} from "./reducers/heroes";
import {fightReducer} from "./reducers/fight";
import {authReducer} from "./reducers/auth";
import {AuthAction, HeroActions} from "./actions/actions";
import {AuthInfo, AuthResponse} from "./model/auth";
import {AuthInterceptor} from "./services/auth-interceptor";
import {AuthService} from "./services/auth.service";

@NgModule({
  declarations: [
    AppComponent,
    HeroesComponent,
    HeroDetailsComponent,
    MessagesComponent,
    DashboardComponent,
    HeroSearchComponent,
    HeroDropdownComponent,
    FightCapComponent,
    HeroViewComponent,
    HeroCharacteristicsComponent,
    HeroEditComponent,
    HeroAddComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    // The HttpClientInMemoryWebApiModule module intercepts HTTP requests
    // and returns simulated server responses.
    // Remove it when a real server is ready to receive requests.
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, {dataEncapsulation: false}
    ),
    StoreModule.forRoot<AppStore>({
      heroes: heroReducer,
      fightResult: fightReducer,
      auth: authReducer
    }, {
      initialState: {
        auth: new AuthInfo(false, '')
      }
    }),
    EffectsModule.forRoot([HeroEffects, FightEffects, AuthEffects]),
    SuiModule
  ],
  providers: [HeroService, MessageService, FightService, AuthService,
    {
      provide: APP_INITIALIZER,
      useFactory: (store: Store<AppStore>) => function () {
        if (localStorage.getItem("accessToken") &&
          +localStorage.getItem("expireAt") > new Date().getMilliseconds())
          store.dispatch(AuthAction.authSuccess(
            new AuthResponse(
              localStorage.getItem("accessToken"),
              +localStorage.getItem("expireAt"),
              localStorage.getItem("refreshToken")
            )
          ));
        store.dispatch(HeroActions.loadHeroes());
      },
      deps: [Store],
      multi: true
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
