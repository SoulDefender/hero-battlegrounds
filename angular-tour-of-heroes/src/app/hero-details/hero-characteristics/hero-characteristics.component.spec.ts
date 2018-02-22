import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroCharacteristicsComponent } from './hero-characteristics.component';
import {CommonModule} from "@angular/common";
import {FormControl, FormGroup, ReactiveFormsModule} from "@angular/forms";
import {SuiModule} from "ng2-semantic-ui";

describe('HeroCharacteristicsComponent', () => {
  let component: HeroCharacteristicsComponent;
  let fixture: ComponentFixture<HeroCharacteristicsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        CommonModule,
        ReactiveFormsModule,
        SuiModule
      ],
      declarations: [ HeroCharacteristicsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HeroCharacteristicsComponent);
    component = fixture.componentInstance;
    component.abilitiesFrom = new FormGroup({
      strength: new FormControl(),
      dexterity: new FormControl(),
      wisdom: new FormControl(),
      intelligence: new FormControl(),
      magicPowerGrantedByUniverse: new FormControl(),
      preferredEnvironments: new FormControl(),
    });
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
