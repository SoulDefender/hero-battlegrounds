export class Hero {
  id: number;
  name: string;
  title: string;
  ages: number;
  weight: number;
  height: number;
  portraitUrl: string;
  characteristics: HeroAbilities;
}

export class HeroAbilities {
  strength: number;
  dexterity: number;
  intelligence: number;
  wisdom: number;
  magicPowerGrantedByUniverse: number;
  preferredEnvironments: string[];
}

export enum Environment {
  SNOW='SNOW',
  SAND='SAND',
  HILLS='HILLS',
  MOUNTAINS='MOUNTAINS',
  CITY='CITY',
  FIELD='FIELD',
  RAINLANDS='RAINLANDS',
  MISTLANDS='MISTLANDS'
}
