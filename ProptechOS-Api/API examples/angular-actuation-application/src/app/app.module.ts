import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {HomeComponent} from './page/home/home.component';
import {MsalInterceptor, MsalModule} from '@azure/msal-angular';
import {environment} from '../environments/environment';
import {RootComponent} from './page/root/root.component';
import {ProptechosService} from './services/proptechos.service';
import {InDataService} from './services/in-data.service';
import {AxiomInfoComponent} from './page/axiom-info/axiom-info.component';
import {LatestObservationComponent} from './page/latest-observation/latest-observation.component';
import {ActuationComponent} from './page/actuation/actuation.component';
import {CommonModule} from '@angular/common';
import {SensorInfoComponent} from './page/sensor-info/sensor-info.component';
import {ActuatorInfoComponent} from './page/actuator-info/actuator-info.component';
import {InterfaceDropdownComponent} from './page/interface-dropdown/interface-dropdown.component';
import {InitialDataComponent} from './page/initial-data/initial-data.component';
import {ActionComponent} from './page/action/action.component';
import {MaterialModule} from './material/material.module';
import {HighchartsChartModule} from 'highcharts-angular';
import {HeaderComponent} from './page/header/header.component';
import { GeneralInfoComponent } from './page/general-info/general-info.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    RootComponent,
    AxiomInfoComponent,
    LatestObservationComponent,
    ActuationComponent,
    SensorInfoComponent,
    ActuatorInfoComponent,
    InterfaceDropdownComponent,
    InitialDataComponent,
    ActionComponent,
    HeaderComponent,
    GeneralInfoComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    MsalModule.forRoot(
      environment.msalConfig, environment.msalAngularConfig),
    MaterialModule,
    HighchartsChartModule
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
