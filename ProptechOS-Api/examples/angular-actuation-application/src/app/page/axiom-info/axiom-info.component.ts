import { Component, Input, OnInit } from '@angular/core';
import {Device} from '../../common/rectypes';

@Component({
  selector: 'app-axiom-info',
  templateUrl: './axiom-info.component.html',
  styleUrls: ['./axiom-info.component.scss']
})
export class AxiomInfoComponent implements OnInit {

  @Input() device: Device;
  dataSource: Array<any>;
  displayedColumns: string[];

  constructor() {}

  ngOnInit(): void {
    this.displayedColumns = ['property', 'value'];
    this.dataSource = [
      {property: 'Placement context', value: this.device.devicePlacementContext},
      {property: 'Quantity kind', value: this.device.deviceQuantityKind},
      {property: 'Measurement unit', value: this.device.deviceMeasurementUnit}];
    if (this.device.littera) {
      this.dataSource.push(
        {property: 'Littera', value: this.device.littera});
    }
    if (this.device.popularName) {
      this.dataSource.push(
        {property: 'Popular name', value: this.device.popularName});
    }
    if (this.device.hasActuationInterface) {
      this.dataSource.push(
        {property: 'Actuation interface', value: this.device.hasActuationInterface});
    }
  }

}
