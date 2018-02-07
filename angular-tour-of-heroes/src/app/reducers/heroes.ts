import { Hero } from '../hero';
import {
  LOAD_HEROES_SUCCESS, LOAD_HERO_BY_ID_SUCCESS, ADD_HERO_SUCCESS, DELETE_HERO_SUCCESS, UPDATE_HERO_SUCCESS,
  HeroActions
} from "../actions/actions";

export interface HeroStore {
  heroes: Hero[];
}

export function heroReducer(state: Hero[] = [], action: HeroActions) {
  switch (action.type) {
    case LOAD_HEROES_SUCCESS:
      return action.payload as Hero[];
    case DELETE_HERO_SUCCESS:
      return state.filter(h => h.id !== (action.payload as number));
    case UPDATE_HERO_SUCCESS:
      return state.map(
        h => {
          if (h.id === (action.payload as Hero).id) {
            return action.payload as Hero;
          }
          return h;
        });
    case LOAD_HERO_BY_ID_SUCCESS:
        return state.map(
          h => {
            if (h.id === (action.payload as Hero).id) {
              return action.payload as Hero;
            }
            return h;
          });
    case ADD_HERO_SUCCESS:
        return state.concat(action.payload as Hero);
    default:
      return state;
  }
}
