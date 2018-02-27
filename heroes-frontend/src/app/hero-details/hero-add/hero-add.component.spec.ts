import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HeroAddComponent} from './hero-add.component';
import {Store, StoreModule} from "@ngrx/store";
import {SuiModule} from "ng2-semantic-ui";
import {HeroDetailsComponent} from "../hero-details.component";
import {CommonModule} from "@angular/common";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";
import {heroReducer, HeroStore} from "../../reducers/heroes";
import {HeroCharacteristicsComponent} from "../hero-characteristics/hero-characteristics.component";

describe('HeroAddComponent', () => {
  let component: HeroAddComponent;
  let fixture: ComponentFixture<HeroAddComponent>;
  let store: Store<HeroStore>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        ReactiveFormsModule,
        CommonModule,
        FormsModule,
        StoreModule.forRoot(
          {
            "heroes": heroReducer
          }
        ),
        SuiModule
      ],
      declarations: [ HeroAddComponent, HeroDetailsComponent, HeroCharacteristicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    store = TestBed.get(Store);
    spyOn(store, 'dispatch').and.callThrough();

    fixture = TestBed.createComponent(HeroAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
