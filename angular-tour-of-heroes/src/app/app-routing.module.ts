import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { HeroesComponent } from './heroes/heroes.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HeroDetailsComponent } from './hero-details/hero-details.component';
import {FightCapComponent} from "./fight-cap/fight-cap.component";


const routes: Routes = [
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'detail/:id', component: HeroDetailsComponent},
  {path: 'heroes/fight', component: FightCapComponent},
  {path: 'heroes', component: HeroesComponent}
];

@NgModule({
  exports: [RouterModule],
  imports: [RouterModule.forRoot(routes)]
})
export class AppRoutingModule {
}
