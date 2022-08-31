import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { WelcomeComponent } from './welcome/welcome.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule} from '@angular/common/http';
import { FormsModule } from '@angular/forms';
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
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
