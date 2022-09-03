import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule} from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatToolbarModule } from '@angular/material/toolbar';
import { SearchLinesComponent } from './search-lines/search-lines.component';
import { AllBusLinesComponent } from './all-bus-lines/all-bus-lines.component';
import { CommentsComponent } from './comments/comments.component';
import { MatSidenavModule } from '@angular/material/sidenav'
import { MatIconModule }  from '@angular/material/icon'
import { MatDividerModule }  from '@angular/material/divider';
import { PassengerStatisticsComponent } from './passenger-statistics/passenger-statistics.component';
import { PassengerMonthlyTicketComponent } from './passenger-monthly-ticket/passenger-monthly-ticket.component';
import { PassengerPreviousTicketsComponent } from './passenger-previous-tickets/passenger-previous-tickets.component';
import { PassengerHomePageComponent } from './passenger-home-page/passenger-home-page.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserNavBarComponent } from './user-nav-bar/user-nav-bar.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserProfileChangeComponent } from './user-profile-change/user-profile-change.component';
import { StaffHomePageComponent } from './staff-home-page/staff-home-page.component';
import { AdminHomePageComponent } from './admin-home-page/admin-home-page.component';
import { StaffStatisticsComponent } from './staff-statistics/staff-statistics.component';
import { StaffPassengersComponent } from './staff-passengers/staff-passengers.component';
import { StaffMonthlyTicketsComponent } from './staff-monthly-tickets/staff-monthly-tickets.component';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import { AdminRegistrationRequestsComponent } from './admin-registration-requests/admin-registration-requests.component';
import { MatSelectModule } from '@angular/material/select';
import { StaffAddDrivingLineComponent } from './staff-add-driving-line/staff-add-driving-line.component';
import { StaffUpdateDrivingLineComponent } from './staff-update-driving-line/staff-update-driving-line.component';
import { StaffDeleteDrivingLineComponent } from './staff-delete-driving-line/staff-delete-driving-line.component';
import { StaffPriceDrivingLineComponent } from './staff-price-driving-line/staff-price-driving-line.component';
import { MatDatepickerModule } from '@angular/material/datepicker';
import {MatNativeDateModule} from '@angular/material/core';
import {NgxMaterialTimepickerModule} from 'ngx-material-timepicker';
import { StaffBusesComponent } from './staff-buses/staff-buses.component';


@NgModule({
  declarations: [
    AppComponent,
    WelcomeComponent,
    SearchLinesComponent,
    AllBusLinesComponent,
    PassengerHomePageComponent,
    PassengerPreviousTicketsComponent,
    CommentsComponent,
    PassengerMonthlyTicketComponent,
    PassengerStatisticsComponent,
    LoginComponent,
    RegisterComponent,
    UserNavBarComponent,
    UserProfileComponent,
    UserProfileChangeComponent,
    StaffHomePageComponent,
    AdminHomePageComponent,
    StaffStatisticsComponent,
    StaffPassengersComponent,
    StaffMonthlyTicketsComponent,
    AdminUsersComponent,
    AdminRegistrationRequestsComponent,
    StaffAddDrivingLineComponent,
    StaffUpdateDrivingLineComponent,
    StaffDeleteDrivingLineComponent,
    StaffPriceDrivingLineComponent,
    StaffBusesComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    FormsModule,
    HttpClientModule,
    MatInputModule,
    MatCheckboxModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatSidenavModule,
    MatIconModule,
    MatDividerModule,
    MatSelectModule,
    ReactiveFormsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    NgxMaterialTimepickerModule
  ],
  providers: [ ] ,
  bootstrap: [AppComponent]
})
export class AppModule { }
