import { InMemoryDbService } from 'angular-in-memory-web-api';
import {Hero} from "./hero";
import {Environment} from "./hero";

export class InMemoryDataService implements InMemoryDbService {
  createDb() {
    const heroes: Hero[] = [
      {
        id: 11,
        name: "Saitama",
        title: "Bald Cape",
        ages: 27,
        weight: 71,
        height: 177,
        portraitUrl: "https://myanimelist.cdn-dena.com/images/characters/11/294388.jpg",
        characteristics: {
          strength: 100,
          dexterity: 100,
          intelligence: 100,
          wisdom: 100,
          magicPowerGrantedByUniverse: 100,
          preferredEnvironments: [Environment.CITY, Environment.HILLS, Environment.MOUNTAINS,
            Environment.RAINLANDS, Environment.FIELD]
        }
      },
      {
        id: 12,
        name: "Garou",
        title: "Human Monster",
        ages: 25,
        weight: 77,
        height: 184,
        portraitUrl: "https://vignette.wikia.nocookie.net/onepunchman/images/3/30/Garou_profile2.png/revision/latest?cb=20161116180516",
        characteristics: {
          strength: 75,
          dexterity: 89,
          intelligence: 60,
          wisdom: 50,
          magicPowerGrantedByUniverse: 20,
          preferredEnvironments: [Environment.CITY, Environment.FIELD]
        }
      }
    ];
    return { heroes };
  }
}
