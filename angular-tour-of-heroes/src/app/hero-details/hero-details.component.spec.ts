import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HeroDetailsComponent} from './hero-details.component';
import {RouterTestingModule} from '@angular/router/testing';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {SuiModule} from "ng2-semantic-ui";
import {HeroCharacteristicsComponent} from "./hero-characteristics/hero-characteristics.component";
import {Hero} from "../hero";

describe('HeroDetailsComponent', () => {
  let component: HeroDetailsComponent;
  let fixture: ComponentFixture<HeroDetailsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        ReactiveFormsModule,
        CommonModule,
        FormsModule,
        SuiModule
      ],
      declarations: [ HeroDetailsComponent, HeroCharacteristicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeroDetailsComponent);
    component = fixture.componentInstance;
    component.hero = Hero.defaultHero();
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
