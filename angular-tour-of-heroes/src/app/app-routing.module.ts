import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { HeroesComponent } from './heroes/heroes.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeroDetailsComponent } from './hero-details/hero-details.component';
import {FightCapComponent} from "./fight-cap/fight-cap.component";
import {HeroEditComponent} from "./hero-details/hero-edit/hero-edit.component";
import {HeroAddComponent} from "./hero-details/hero-add/hero-add.component";


const routes: Routes = [
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'heroes/detail/:id', component: HeroEditComponent},
  {path: 'heroes/fight', component: FightCapComponent},
  {path: 'heroes/add', component: HeroAddComponent},
  {path: 'heroes', component: HeroesComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule {
}
