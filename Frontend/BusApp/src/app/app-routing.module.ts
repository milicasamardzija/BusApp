import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomePageComponent } from './admin-home-page/admin-home-page.component';
import { AllBusLinesComponent } from './all-bus-lines/all-bus-lines.component';
import { PassengerHomePageComponent } from './passenger-home-page/passenger-home-page.component';
import { PassengerMonthlyTicketComponent } from './passenger-monthly-ticket/passenger-monthly-ticket.component';
import { PassengerPreviousTicketsComponent } from './passenger-previous-tickets/passenger-previous-tickets.component';
import { PassengerStatisticsComponent } from './passenger-statistics/passenger-statistics.component';
import { StaffHomePageComponent } from './staff-home-page/staff-home-page.component';
import { UserProfileComponent } from './user-profile/user-profile.component';

import { WelcomeComponent } from './welcome/welcome.component';

const routes: Routes = [
  { path: '', component: WelcomeComponent },
  { path: 'passenger', component: PassengerHomePageComponent,
    children: [
      {
        path:'lines', component: AllBusLinesComponent
      },
      {
        path:'statistics', component: PassengerStatisticsComponent
      },
      {
        path:'monthlyTickets', component: PassengerMonthlyTicketComponent
      },
      {
        path:'tickets', component: PassengerPreviousTicketsComponent
      },
      {
        path:'profile', component: UserProfileComponent
      }
    ]
  },
  { path: 'staff', component: StaffHomePageComponent,
    children: [
      {
        path:'lines', component: AllBusLinesComponent
      },
      {
        path:'profile', component: UserProfileComponent
      }
    ]  
  },
  { path: 'admin', component: AdminHomePageComponent,
    children: [
      {
        path:'lines', component: AllBusLinesComponent
      }
    ]  
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
