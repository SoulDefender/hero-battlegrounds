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
  preferredEnvironments: Environment[];
}

export enum Environment {
  SNOW,
  SAND,
  HILLS,
  MOUNTAINS,
  CITY,
  FIELD,
  RAINLANDS,
  MISTLANDS
}
