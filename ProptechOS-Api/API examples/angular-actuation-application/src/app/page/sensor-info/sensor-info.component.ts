import {Component, Input, OnInit} from '@angular/core';
import {Sensor} from '../../common/rectypes';
import {AxiomInfo} from '../../common/basetypes';

@Component({
  selector: 'app-sensor-info',
  templateUrl: './sensor-info.component.html',
  styleUrls: ['./sensor-info.component.scss']
})
export class SensorInfoComponent implements OnInit {

  @Input() sensor: Sensor;

  constructor() { }

  ngOnInit(): void {
  }

  sensorInfo(): Array<AxiomInfo> {
    const result = [
      {property: 'Placement context', value: this.sensor.devicePlacementContext},
      {property: 'Quantity kind', value: this.sensor.deviceQuantityKind},
      {property: 'Measurement unit', value: this.sensor.deviceMeasurementUnit}];
    if (this.sensor.littera) {
      result.push(
        {property: 'Littera', value: this.sensor.littera});
    }
    if (this.sensor.popularName) {
      result.push(
        {property: 'Popular name', value: this.sensor.popularName});
    }
    if (this.sensor.observesActuator) {
      result.push(
        {property: 'Observes actuator', value: this.sensor.observesActuator});
    }
    return result;
  }

}
