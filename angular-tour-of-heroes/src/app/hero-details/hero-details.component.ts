import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Hero, HeroAbilities} from '../hero';
import {FormBuilder, FormGroup, ValidatorFn, Validators} from "@angular/forms";
import {abilitiesValidators} from "../hero-validators";

@Component({
  selector: 'app-hero-details',
  templateUrl: './hero-details.component.html',
  styleUrls: ['./hero-details.component.css']
})
export class HeroDetailsComponent implements OnInit {

  @Input() title: string;
  @Input() hero: Hero;
  @Output() saveHero: EventEmitter<Hero> = new EventEmitter<Hero>();
  @Output() goBackObservable: EventEmitter<any> = new EventEmitter<any>();
  heroForm: FormGroup;

  constructor(private fb: FormBuilder) {
  }

  ngOnInit() {
    this.heroForm = this.createHeroForm(this.hero, this.hero.characteristics, abilitiesValidators);
  }

  private createHeroForm(hero: Hero, abilities: HeroAbilities, abilitiesValidator: ValidatorFn[]) {
    return this.fb.group({
      name: [hero.name, [Validators.required, Validators.minLength(2)]],
      title: [hero.title, [Validators.required, Validators.minLength(2)]],
      portraitUrl: [hero.portraitUrl, [Validators.required]],
      ages: [hero.ages],
      weight: [hero.weight],
      height: [hero.height],
      characteristics: this.fb.group({
        strength: [abilities.strength, abilitiesValidator],
        dexterity: [abilities.dexterity, abilitiesValidator],
        wisdom: [abilities.wisdom, abilitiesValidator],
        intelligence: [abilities.intelligence, abilitiesValidator],
        magicPowerGrantedByUniverse: [abilities.magicPowerGrantedByUniverse, abilitiesValidator],
        preferredEnvironments: [abilities.preferredEnvironments,
          Validators.required]
      })
    });
  }

  save(model: FormGroup): void {
    this.saveHero.emit(model.value);
  }

  goBack() {
    this.goBackObservable.emit();
  }
}
