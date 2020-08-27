import { Component, Input, OnInit } from '@angular/core';
import {Device} from '../../common/rectypes';

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
    if (this.device !== null && this.device !== undefined) {
      const name = this.device.littera ? ': ' + this.device.littera : '';
      return this.device.class + name;
    }
    return 'Device';
  }

}
