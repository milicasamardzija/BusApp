import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PassengerHomePageComponent } from './passenger-home-page/passenger-home-page.component';

import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'passenger', component: PassengerHomePageComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
