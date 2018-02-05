import { Hero } from '../hero';
import { Action } from '@ngrx/store';

export const ADD_HERO = 'ADD_HERO';
export const DELETE_HERO = 'DELETE_HERO';
export const UPDATE_HERO = 'UPDATE_HERO';
export const UPDATE_HEROES = 'UPDATE_HEROES';

export class HeroActionWithPayload implements Action {
  type: string;
  payload: Hero | Hero[];
}

export function heroReducer(state: Hero[] = [], action: HeroActionWithPayload) {
  switch (action.type) {
    case ADD_HERO:
      return state.concat(action.payload);
    case DELETE_HERO:
      return state.filter(h => h.id === (action.payload as Hero).id);
    case UPDATE_HERO:
      return state.map(
        h => {
          if (h.id === (action.payload as Hero).id) {
            return action.payload as Hero;
          }
          return h;
        });
    case UPDATE_HEROES:
        return action.payload as Hero[];
    default:
      return state;
  }
}
