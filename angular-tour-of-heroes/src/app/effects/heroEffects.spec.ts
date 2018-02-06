import { cold, hot} from 'jasmine-marbles';
import { async, TestBed } from '@angular/core/testing';
import { Actions } from '@ngrx/effects';
import { Store, StoreModule } from '@ngrx/store';
import { getTestActions, TestActions } from '../helpers/helpers';

import { Observable } from 'rxjs/Observable';

import 'rxjs/add/observable/of';
import {HeroEffects} from "./heroEffects";
import {HeroService} from "../hero.service";
import {HeroActions} from "../actions/actions";
import {heroReducer} from "../reducers/heroes";

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
      const heroes = [{id: '1', name: 'Eleven'}];
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
