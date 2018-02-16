import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FightCapComponent} from './fight-cap.component';
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";
import {StoreModule} from "@ngrx/store";
import {heroReducer} from "../reducers/heroes";
import {MessageService} from "../message.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {RouterTestingModule} from "@angular/router/testing";
import {HeroService} from "../hero.service";
import {SuiModule} from "ng2-semantic-ui";
import {FormsModule} from "@angular/forms";


describe('FightCapComponent', () => {
  let component: FightCapComponent;
  let fixture: ComponentFixture<FightCapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        FormsModule,
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
      declarations: [ FightCapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FightCapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
