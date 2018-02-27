import {cold, hot} from 'jasmine-marbles';
import {async, TestBed} from '@angular/core/testing';
import {Actions} from '@ngrx/effects';
import {Store, StoreModule} from '@ngrx/store';
import {getTestActions, TestActions} from '../helpers/helpers';

import {Observable} from 'rxjs/Observable';

import 'rxjs/add/observable/of';
import {HeroEffects} from "./hero-effects";
import {HeroService} from "../services/hero.service";
import {HeroActions} from "../actions/actions";
import {heroReducer} from "../reducers/heroes";
import {Environment} from "../model/hero";

describe('Effect Tests', () => {

  let store: Store<any>;
  let storeSpy: jasmine.Spy;

  beforeEach(async(() => {

    TestBed.configureTestingModule({
      imports: [
        StoreModule.forRoot({'heroes': heroReducer})
      ],
      providers: [
        HeroEffects,
        {
          provide: Actions,
          useFactory: getTestActions
        },
        {
          provide: HeroService,
          useValue: jasmine.createSpyObj('HeroService',
            ['getHeroes', 'getHero', 'addHero', 'deleteHero'])
        }
      ]
    });

    store = TestBed.get(Store);
    storeSpy = spyOn(store, 'dispatch').and.callThrough();
    storeSpy = spyOn(store, 'select').and.callThrough();

  }));

  function setup() {
    return {
      effects: TestBed.get(HeroEffects) as HeroEffects,
      heroService: TestBed.get(HeroService) as jasmine.SpyObj<HeroService>,
      actions$: TestBed.get(Actions) as TestActions
    };
  }

  describe('loadHeroes$', () => {
    it('should return LOAD_HEROES_SUCCESS action for LOAD_HEROES_ACTION', async () => {

      const { effects, heroService, actions$ } = setup();
      const heroes = [
        {
          id: 11,
          name: "Eleven",
          title: "Buggy",
          ages: 27,
          weight: 71,
          height: 177,
          portraitUrl: "",
          characteristics: {
            strength: 100,
            dexterity: 100,
            intelligence: 100,
            wisdom: 100,
            magicPowerGrantedByUniverse: 100,
            preferredEnvironments: [Environment.CITY, Environment.HILLS, Environment.MOUNTAINS,
              Environment.RAINLANDS, Environment.FIELD]
          }
        }
      ];
      heroService.getHeroes.and.returnValue(Observable.of(heroes));

      const action = HeroActions.loadHeroes();
      const completion = HeroActions.loadHeroesSuccess(heroes);
      actions$.stream = hot('a', { a: action });
      const expected = cold('b', { b: completion });

      expect(effects.loadHeroes$).toBeObservable(expected);
      expect(heroService.getHeroes).toHaveBeenCalled();

    });
  })

});
