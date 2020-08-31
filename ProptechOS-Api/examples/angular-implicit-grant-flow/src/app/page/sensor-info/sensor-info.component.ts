import {Component, Input, OnInit} from '@angular/core';
import {Device} from '../../common/rectypes';

@Component({
  selector: 'app-sensor-info',
  templateUrl: './sensor-info.component.html',
  styleUrls: ['./sensor-info.component.scss']
})
export class SensorInfoComponent implements OnInit {

  @Input() sensor: Device;

  constructor() { }

  ngOnInit(): void {
  }

}
