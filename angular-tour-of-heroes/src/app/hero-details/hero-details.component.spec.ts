import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroDetailsComponent } from './hero-details.component';
import { RouterTestingModule } from '@angular/router/testing';
import { CommonModule } from '@angular/common';

import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { HeroService } from '../hero.service';
import { MessageService } from '../message.service';
import { FormsModule } from '@angular/forms';
import {Store, StoreModule} from "@ngrx/store";
import {heroReducer, HeroStore} from "../reducers/heroes";

describe('HeroDetailsComponent', () => {
  let component: HeroDetailsComponent;
  let fixture: ComponentFixture<HeroDetailsComponent>;
  let store: Store<HeroStore>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        CommonModule,
        HttpClientModule,
        HttpClientTestingModule,
        FormsModule,
        StoreModule.forRoot(
          {
            "heroes": heroReducer
          }
        )
      ],
      providers: [HeroService, MessageService],
      declarations: [ HeroDetailsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    store = TestBed.get(Store);

    spyOn(store, 'dispatch').and.callThrough();

    fixture = TestBed.createComponent(HeroDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
