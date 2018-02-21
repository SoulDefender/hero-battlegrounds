import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {HeroDropdownComponent} from './hero-dropdown.component';
import {StoreModule} from "@ngrx/store";
import {heroReducer} from "../../reducers/heroes";
import {SuiModule} from "ng2-semantic-ui";
import {CommonModule} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {HeroViewComponent} from "../../hero-view/hero-view.component";

describe('HeroDropdownComponent', () => {
  let component: HeroDropdownComponent;
  let fixture: ComponentFixture<HeroDropdownComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        FormsModule,
        CommonModule,
        StoreModule.forRoot(
          {
            "heroes": heroReducer
          }
        ),
        SuiModule
      ],
      declarations: [ HeroDropdownComponent, HeroViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeroDropdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
