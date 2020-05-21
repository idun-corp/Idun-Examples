import { Component, Input, OnInit } from '@angular/core';

export interface Device {

  id: string;
  class: string;
  littera: string;
  popularName: string;
  deviceMeasurementUnit: string;
  deviceQuantityKind: string;
  devicePlacementContext: string;
  hasActuationInterface?: string;

}

@Component({
  selector: 'app-axiom-info',
  templateUrl: './axiom-info.component.html',
  styleUrls: ['./axiom-info.component.scss']
})
export class AxiomInfoComponent implements OnInit {

  @Input() device: Device;

  constructor() { }

  ngOnInit(): void {
  }

  getTitle(): string {
    return this.device ? this.device.class + ': ' + this.device.popularName : '';
  }

}
