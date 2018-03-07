export class Hero {
  id: number;
  name: string;
  title: string;
  ages: number;
  weight: number;
  height: number;
  portraitUrl: string;
  characteristics: HeroAbilities;

  static defaultHero(): Hero {
      let hero = new Hero();
      hero.characteristics = new HeroAbilities();
      hero.characteristics.preferredEnvironments = [];
      return hero;
  }
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


export const environments: string[] = [Environment.FIELD, Environment.RAINLANDS,
  Environment.MOUNTAINS, Environment.CITY,
  Environment.HILLS, Environment.MISTLANDS, Environment.SAND, Environment.SNOW];
