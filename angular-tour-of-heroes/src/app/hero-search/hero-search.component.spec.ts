import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RouterTestingModule } from '@angular/router/testing';
import { CommonModule } from '@angular/common';
import { HeroSearchComponent } from './hero-search.component';

import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { HeroService } from '../hero.service';
import { MessageService } from '../message.service';
import {Store, StoreModule} from "@ngrx/store";
import {heroReducer, HeroStore} from "../reducers/heroes";

describe('HeroSearchComponent', () => {
  let component: HeroSearchComponent;
  let fixture: ComponentFixture<HeroSearchComponent>;
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
        )
      ],
      providers: [HeroService, MessageService],
      declarations: [ HeroSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    store = TestBed.get(Store);

    spyOn(store, 'dispatch').and.callThrough();

    fixture = TestBed.createComponent(HeroSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
