import {Component, Input, OnInit} from '@angular/core';
import {environments} from "../../model/hero";
import {FormGroup} from "@angular/forms";

@Component({
  selector: 'app-hero-abilities',
  templateUrl: './hero-characteristics.component.html',
  styleUrls: ['./hero-characteristics.component.css']
})
export class HeroCharacteristicsComponent implements OnInit {

  @Input() public abilitiesFrom: FormGroup;

  envs: string[] = environments;

  constructor() { }

  ngOnInit() {
  }
}
