import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminHomePageComponent } from './admin-home-page/admin-home-page.component';
import { AdminRegistrationRequestsComponent } from './admin-registration-requests/admin-registration-requests.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import { AllBusLinesComponent } from './all-bus-lines/all-bus-lines.component';
import { PassengerHomePageComponent } from './passenger-home-page/passenger-home-page.component';
import { PassengerMonthlyTicketComponent } from './passenger-monthly-ticket/passenger-monthly-ticket.component';
import { PassengerPreviousTicketsComponent } from './passenger-previous-tickets/passenger-previous-tickets.component';
import { PassengerStatisticsComponent } from './passenger-statistics/passenger-statistics.component';
import { PassengerTicketCheckComponent } from './passenger-ticket-check/passenger-ticket-check.component';
import { StaffAddDrivingLineComponent } from './staff-add-driving-line/staff-add-driving-line.component';
import { StaffBusesComponent } from './staff-buses/staff-buses.component';
import { StaffEmployeesComponent } from './staff-employees/staff-employees.component';
import { StaffHomePageComponent } from './staff-home-page/staff-home-page.component';
import { StaffMonthlyTicketsComponent } from './staff-monthly-tickets/staff-monthly-tickets.component';
import { StaffPassengersComponent } from './staff-passengers/staff-passengers.component';
import { StaffStatisticsComponent } from './staff-statistics/staff-statistics.component';
import { StaffUpdateDrivingLineComponent } from './staff-update-driving-line/staff-update-driving-line.component';
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
      },
      {
        path:'statistics', component: StaffStatisticsComponent
      },
      {
        path:'passengers', component: StaffPassengersComponent
      },  
      {
        path:'buses', component: StaffBusesComponent
      },
      {
        path:'employees', component: StaffEmployeesComponent
      },
      {
        path:'monthlyTickets', component: StaffMonthlyTicketsComponent
      },
      {
        path:'add', component: StaffAddDrivingLineComponent
      },
      {
        path:'lines/:id', component: StaffUpdateDrivingLineComponent
      }
    ]  
  },
  { path: 'admin', component: AdminHomePageComponent,
    children: [
      {
        path:'lines', component: AllBusLinesComponent
      },
      {
        path:'users', component: AdminUsersComponent
      },
      {
        path:'requests', component: AdminRegistrationRequestsComponent
      }
    ]  
  },
  {
    path:'standardTicket/checkTicket/:id', component: PassengerTicketCheckComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
