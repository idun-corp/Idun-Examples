import { Component, OnInit } from '@angular/core';
import {MsalService} from '@azure/msal-angular';
import {environment as env} from '../../../environments/environment';
import { InDataService } from '../../services/in-data.service';
import { ProptechosService } from '../../services/proptechos.service';
import {Device, Observation} from '../../common/rectypes';

@Component({
  selector: 'app-root',
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss']
})
export class RootComponent implements OnInit {

  accessToken: string;

  actuator: Device;
  sensor: Device;
  observation: Observation;

  constructor(
    private msalService: MsalService,
    private inDataService: InDataService,
    private apiService: ProptechosService
  ) { }


  ngOnInit(): void {
    // this.msalService.acquireTokenSilent(env.auth.consentScopes).then(token => {
    //   this.accessToken = token;
    // });
    console.log(this.inDataService.retrieveInData());
    this.apiService.getActuator(this.inDataService.retrieveInData().actuatorId).subscribe((data) => {
      this.actuator = data;
    });
    this.apiService.getSensor(this.inDataService.retrieveInData().sensorId).subscribe((data) => {
      this.sensor = data;
    });
    this.apiService.getLatestObservation(this.inDataService.retrieveInData().sensorId).subscribe((data) => {
      this.observation = data;
    });
  }

}
