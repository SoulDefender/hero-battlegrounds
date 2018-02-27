import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HeroViewComponent} from './hero-view.component';
import {SuiModule} from "ng2-semantic-ui";
import {CommonModule} from "@angular/common";
import {Hero} from "../model/hero";

describe('HeroViewComponent', () => {
  let component: HeroViewComponent;
  let fixture: ComponentFixture<HeroViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        CommonModule,
        SuiModule
      ],
      declarations: [ HeroViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeroViewComponent);
    component = fixture.componentInstance;
    component.hero = new Hero();
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
