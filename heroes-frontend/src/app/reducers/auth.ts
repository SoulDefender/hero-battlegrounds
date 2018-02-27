import {AuthAction, AUTHENTICATE_SUCCESS} from "../actions/actions";
import {AuthInfo, AuthResponse} from "../model/auth";

export function authReducer(state: AuthInfo, action: AuthAction) {
  switch (action.type) {
    case AUTHENTICATE_SUCCESS:
      let response = action.payload as AuthResponse;
      return new AuthInfo(true, response.accessToken);
  }
}
