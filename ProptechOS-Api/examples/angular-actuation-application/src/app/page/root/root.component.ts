import { Component, OnInit } from '@angular/core';
import {MsalService} from '@azure/msal-angular';
import { InDataService } from '../../services/in-data.service';
import { ProptechosService } from '../../services/proptechos.service';
import {Device, Observation} from '../../common/rectypes';

@Component({
  selector: 'app-root',
  templateUrl: './root.component.html',
  styleUrls: ['./root.component.scss']
})
export class RootComponent implements OnInit {

  isFormSubmitted = false;

  actuator: Device;
  sensor: Device;

  constructor(
    private msalService: MsalService,
    private inDataService: InDataService,
    private apiService: ProptechosService) { }


  ngOnInit(): void {
    // console.log(this.inDataService.retrieveInData());
    // this.apiService.getActuator(this.inDataService.retrieveInData().actuatorId).subscribe((data) => {
    //   this.actuator = data;
    // });
    // this.apiService.getSensor(this.inDataService.retrieveInData().sensorId).subscribe((data) => {
    //   this.sensor = data;
    // });
  }

  onFormSubmit(formSubmitted: boolean): void {
    this.isFormSubmitted = formSubmitted;
  }

}
