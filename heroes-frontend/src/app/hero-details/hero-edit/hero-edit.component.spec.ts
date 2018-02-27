import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroEditComponent } from './hero-edit.component';
import {HeroService} from "../../services/hero.service";
import {Store, StoreModule} from "@ngrx/store";
import {RouterTestingModule} from "@angular/router/testing";
import {heroReducer, HeroStore} from "../../reducers/heroes";
import {SuiModule} from "ng2-semantic-ui";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {HttpClientModule} from "@angular/common/http";
import {MessageService} from "../../services/message.service";
import {HeroDetailsComponent} from "../hero-details.component";
import {HeroCharacteristicsComponent} from "../hero-characteristics/hero-characteristics.component";
import {heroes} from "../../in-memory-data.service";
import {HeroActions} from "../../actions/actions";

describe('HeroEditComponent', () => {
  let component: HeroEditComponent;
  let fixture: ComponentFixture<HeroEditComponent>;
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
      declarations: [ HeroEditComponent, HeroDetailsComponent, HeroCharacteristicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    store = TestBed.get(Store);
    store.dispatch(HeroActions.loadHeroesSuccess(heroes));
    spyOn(store, 'dispatch').and.callThrough();

    fixture = TestBed.createComponent(HeroEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
