import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HeroDetailsComponent} from './hero-details.component';
import {RouterTestingModule} from '@angular/router/testing';
import {CommonModule} from '@angular/common';

import {HttpClientModule} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {HeroService} from '../hero.service';
import {MessageService} from '../message.service';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {Store, StoreModule} from "@ngrx/store";
import {heroReducer, HeroStore} from "../reducers/heroes";
import {SuiModule} from "ng2-semantic-ui";
import {HeroCharacteristicsComponent} from "./hero-characteristics/hero-characteristics.component";
import {HeroActions} from "../actions/actions";
import {heroes} from "../in-memory-data.service";
import {ActivatedRoute} from "@angular/router";

describe('HeroDetailsComponent', () => {
  let component: HeroDetailsComponent;
  let fixture: ComponentFixture<HeroDetailsComponent>;
  let store: Store<HeroStore>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        ReactiveFormsModule,
        CommonModule,
        HttpClientModule,
        HttpClientTestingModule,
        FormsModule,
        StoreModule.forRoot(
          {
            "heroes": heroReducer
          }
        ),
        SuiModule
      ],
      providers: [HeroService, MessageService,
        {
          provide: ActivatedRoute,
          useValue: {
            data: {},
            params: {},
            snapshot: {
              paramMap: {
                get: () => 11
              },
            },
          },
        }],
      declarations: [ HeroDetailsComponent, HeroCharacteristicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    store = TestBed.get(Store);
    store.dispatch(HeroActions.loadHeroesSuccess(heroes));
    spyOn(store, 'dispatch').and.callThrough();

    fixture = TestBed.createComponent(HeroDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
