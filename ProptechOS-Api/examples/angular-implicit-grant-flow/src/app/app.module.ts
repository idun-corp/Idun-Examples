import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { HomeComponent } from './page/home/home.component';
import {MsalInterceptor, MsalModule} from '@azure/msal-angular';
import { environment } from '../environments/environment';
import { RootComponent } from './page/root/root.component';
import { ProptechosService } from './services/proptechos.service';
import { InDataService } from './services/in-data.service';
import { AxiomInfoComponent } from './page/axiom-info/axiom-info.component';
import { LatestObservationComponent } from './page/latest-observation/latest-observation.component';
import { ActuationComponent } from './page/actuation/actuation.component';
import {CommonModule} from '@angular/common';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RootComponent,
    AxiomInfoComponent,
    LatestObservationComponent,
    ActuationComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatButtonModule,
    MsalModule.forRoot(environment.msalConfig)
  ],
  providers: [
    ProptechosService,
    InDataService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: MsalInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
