import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api';
import {InMemoryDataService} from './in-memory-data.service';


import {AppComponent} from './app.component';
import {HeroesComponent} from './heroes/heroes.component';
import {HeroDetailsComponent} from './hero-details/hero-details.component';

import {HeroService} from './hero.service';
import {MessagesComponent} from './messages/messages.component';
import {MessageService} from './message.service';
import {AppRoutingModule} from './app-routing.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import {HeroSearchComponent} from './hero-search/hero-search.component';
import {StoreModule} from '@ngrx/store';
import {fightReducer, heroReducer, HeroStore} from './reducers/heroes';
import {EffectsModule} from "@ngrx/effects";
import {HeroEffects} from "./effects/hero-effects";
import {FightCapComponent} from './fight-cap/fight-cap.component';
import {SuiModule} from "ng2-semantic-ui";

@NgModule({
  declarations: [
    AppComponent,
    HeroesComponent,
    HeroDetailsComponent,
    MessagesComponent,
    DashboardComponent,
    HeroSearchComponent,
    FightCapComponent
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
    StoreModule.forRoot<HeroStore>({
      heroes: heroReducer,
      fightResult: fightReducer
    }),
    EffectsModule.forRoot([HeroEffects]),
    SuiModule
  ],
  providers: [HeroService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
