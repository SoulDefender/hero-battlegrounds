import {Component, Input, OnInit} from '@angular/core';
import {Hero} from "../model/hero";

@Component({
  selector: 'app-hero-view',
  templateUrl: './hero-view.component.html',
  styleUrls: ['./hero-view.component.css']
})
export class HeroViewComponent implements OnInit {

  @Input() hero: Hero;

  constructor() { }

  ngOnInit() {
  }

}
