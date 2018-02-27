import {Component, OnInit} from '@angular/core';
import {Hero} from "../../model/hero";
import {HeroService} from "../../services/hero.service";
import {Store} from "@ngrx/store";
import {HeroStore} from "../../reducers/heroes";
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {HeroActions} from "../../actions/actions";
import {MessageService} from "../../services/message.service";
import {Subject} from "rxjs/Subject";
import {debounceTime, distinctUntilChanged} from "rxjs/operators";

@Component({
  selector: 'app-hero-edit',
  templateUrl: './hero-edit.component.html',
  styleUrls: ['./hero-edit.component.css']
})
export class HeroEditComponent implements OnInit {

  hero: Hero;
  backSb: Subject<any> = new Subject<any>();

  constructor(private route: ActivatedRoute,
              private heroService: HeroService,
              private messageService: MessageService,
              private location: Location,
              private store: Store<HeroStore>) {
  }

  ngOnInit() {
    this.getHero();
    this.backSb.pipe(
      // wait 300ms after each keystroke before considering the term
      debounceTime(200),
      // ignore new term if same as previous term
      distinctUntilChanged()).subscribe(
      () => {
        this.messageService.add("back");
        this.location.back()
      });
  }

  getHero(): void {
    if (this.route.snapshot.paramMap.get('id')) {
      const id = +this.route.snapshot.paramMap.get('id');
      this.store.select('heroes').map(heroes => heroes.find(hero => hero.id === id))
        .subscribe(hero => {
          this.hero = hero;
        });
    }
  }

  saveHeroEdit(hero: Hero) {
    this.store.dispatch(HeroActions.updateHero(hero));
    this.goBack();
  }

  goBack(): void {
    this.backSb.next();
  }
}
