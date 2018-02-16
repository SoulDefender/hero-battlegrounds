import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroesComponent } from './heroes.component';
import { RouterTestingModule } from '@angular/router/testing';
import { CommonModule } from '@angular/common';

import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { HeroService } from '../hero.service';
import { MessageService } from '../message.service';
import {Store, StoreModule} from "@ngrx/store";
import {heroReducer, HeroStore} from "../reducers/heroes";
import {SuiModule} from "ng2-semantic-ui";

describe('HeroesComponent', () => {
  let component: HeroesComponent;
  let fixture: ComponentFixture<HeroesComponent>;
  let store: Store<HeroStore>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        CommonModule,
        HttpClientModule,
        HttpClientTestingModule,
        StoreModule.forRoot(
          {
            "heroes": heroReducer
          }
        ),
        SuiModule
      ],
      providers: [HeroService, MessageService],
      declarations: [ HeroesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    store = TestBed.get(Store);

    spyOn(store, 'dispatch').and.callThrough();

    fixture = TestBed.createComponent(HeroesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
