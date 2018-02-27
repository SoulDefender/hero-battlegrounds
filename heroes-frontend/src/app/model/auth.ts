export class AuthInfo {
  constructor(public authenticated: boolean, public token: string) {}
}

export class AuthResponse {
  constructor(public accessToken: string, public expireTime: number, public refreshToken) {}
}


