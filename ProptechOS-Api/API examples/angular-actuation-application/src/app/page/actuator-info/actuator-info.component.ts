import {Component, Input, OnInit} from '@angular/core';
import {Actuator} from '../../common/rectypes';
import {AxiomInfo} from '../../common/basetypes';

@Component({
  selector: 'app-actuator-info',
  templateUrl: './actuator-info.component.html',
  styleUrls: ['./actuator-info.component.scss']
})
export class ActuatorInfoComponent implements OnInit {

  @Input() actuator: Actuator;

  constructor() { }

  ngOnInit(): void {
  }

  actuatorInfo(): Array<AxiomInfo> {
    const result = [
      {property: 'Placement context', value: this.actuator.devicePlacementContext},
      {property: 'Quantity kind', value: this.actuator.deviceQuantityKind},
      {property: 'Measurement unit', value: this.actuator.deviceMeasurementUnit},
      {property: 'Default actuation interfaces', value: this.actuator.hasDefaultActuationInterface}];
    if (this.actuator.hasActuationInterface) {
      result.push(
        {property: 'Actuation interfaces', value: this.actuator.hasActuationInterface.join(',')});
    }
    if (this.actuator.littera) {
      result.push(
        {property: 'Littera', value: this.actuator.littera});
    }
    if (this.actuator.popularName) {
      result.push(
        {property: 'Popular name', value: this.actuator.popularName});
    }
    if (this.actuator.observedBy) {
      result.push(
        {property: 'Observed by sensor', value: this.actuator.observedBy});
    }
    return result;
  }

}
