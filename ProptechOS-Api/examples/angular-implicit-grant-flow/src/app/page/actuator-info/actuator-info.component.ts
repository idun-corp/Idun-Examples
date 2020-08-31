import {Component, Input, OnInit} from '@angular/core';
import {Device} from '../../common/rectypes';

@Component({
  selector: 'app-actuator-info',
  templateUrl: './actuator-info.component.html',
  styleUrls: ['./actuator-info.component.scss']
})
export class ActuatorInfoComponent implements OnInit {

  @Input() actuator: Device;

  constructor() { }

  ngOnInit(): void {
  }

}
