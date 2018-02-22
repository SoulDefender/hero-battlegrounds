import {Component, Input, OnInit} from '@angular/core';
import {Environment} from "../../hero";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-hero-abilities',
  templateUrl: './hero-characteristics.component.html',
  styleUrls: ['./hero-characteristics.component.css']
})
export class HeroCharacteristicsComponent implements OnInit {

  @Input() public abilitiesFrom: FormGroup;

  envs: string[] = [Environment.FIELD, Environment.RAINLANDS,
    Environment.MOUNTAINS, Environment.CITY,
    Environment.HILLS, Environment.MISTLANDS, Environment.SAND, Environment.SNOW];

  constructor() { }

  ngOnInit() {
  }
}
