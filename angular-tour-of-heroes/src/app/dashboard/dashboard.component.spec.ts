import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {DashboardComponent} from './dashboard.component';
import {RouterTestingModule} from '@angular/router/testing';
import {CommonModule} from '@angular/common';
import {HeroSearchComponent} from '../hero-search/hero-search.component';
import {HttpClientModule} from '@angular/common/http';
import {HttpClientTestingModule} from '@angular/common/http/testing';
import {HeroService} from '../hero.service';
import {MessageService} from '../message.service';
import {Store, StoreModule} from "@ngrx/store";
import {heroReducer, HeroStore} from "../reducers/heroes";
import {SuiModule} from "ng2-semantic-ui";
import {ReactiveFormsModule} from "@angular/forms";

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let store: Store<HeroStore>

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        ReactiveFormsModule,
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
      declarations: [ DashboardComponent, HeroSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    store = TestBed.get(Store);

    spyOn(store, 'dispatch').and.callThrough();

    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
