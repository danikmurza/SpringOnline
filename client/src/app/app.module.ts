import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';


import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import {RouterModule} from "@angular/router";
import {CookieService} from "./cookie.service";
import { RegistrationComponent } from './registration/registration.component';
import {ConfigService} from "./config.service";
import {HttpClientModule} from "@angular/common/http";
// import { MainComponent } from './main/main.component';
import { FooterComponent } from './footer/footer.component';
import { NavComponent } from './nav/nav.component';
import { AccountComponent } from './account/account.component';
import { MainComponent } from './main/main.component';
import {DownloadfileService} from "./downloadfile.service";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    // MainComponent,
    FooterComponent,
    NavComponent,
    AccountComponent,
    MainComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: '', component: MainComponent},
      {path: 'login', component: LoginComponent},
      // {path: 'get-cookie', component: GetCookieExampleComponent}
      {path: 'registration', component: RegistrationComponent},
    ])
  ],
  providers: [CookieService, ConfigService, DownloadfileService],
  bootstrap: [AppComponent]
})
export class AppModule { }
