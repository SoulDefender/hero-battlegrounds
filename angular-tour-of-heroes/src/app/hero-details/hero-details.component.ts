import {Component, Input, OnInit} from '@angular/core';
import {Environment, Hero, HeroAbilities} from '../hero';
import {ActivatedRoute} from '@angular/router';
import {HeroService} from '../hero.service';
import {Location} from '@angular/common';
import {Subject} from 'rxjs/Subject';
import {Store} from "@ngrx/store";
import {HeroStore} from "../reducers/heroes";
import {HeroActions} from "../actions/actions";
import {FormBuilder, FormGroup, ValidatorFn, Validators} from "@angular/forms";

@Component({
  selector: 'app-hero-details',
  templateUrl: './hero-details.component.html',
  styleUrls: ['./hero-details.component.css']
})
export class HeroDetailsComponent implements OnInit {

  public heroForm: FormGroup;
  @Input() hero: Hero;
  id: Subject<number> = new Subject<number>();


  constructor(private route: ActivatedRoute,
              private heroService: HeroService,
              private location: Location,
              private store: Store<HeroStore>,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.getHero();
  }

  getHero(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.store.select('heroes').map(heroes => heroes.find(hero => hero.id === id))
      .subscribe(hero => {
        this.hero = hero;
        let abilities = hero.characteristics;
        let abilitiesValidators = [Validators.max(100), Validators.min(1), Validators.required];

        this.heroForm = this.createHeroForm(hero, abilities, abilitiesValidators)
      });
  }

  private createHeroForm(hero: Hero, abilities: HeroAbilities, abilitiesValidator: ValidatorFn[]) {
    return this.formBuilder.group({
      name: [hero.name, [Validators.required, Validators.minLength(2)]],
      title: [hero.title, [Validators.required, Validators.minLength(2)]],
      ages: [hero.ages],
      weight: [hero.weight],
      height: [hero.height],
      characteristics: this.formBuilder.group({
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

  goBack(): void {
    this.location.back();
  }

  save(model: FormGroup): void {
    console.log(model.value);
    this.store.dispatch(HeroActions.updateHero(model.value));
    this.goBack()
  }
}
