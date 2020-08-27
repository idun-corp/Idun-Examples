import { Component, Input, OnInit } from '@angular/core';
import {Observation} from '../../common/rectypes';

@Component({
  selector: 'app-latest-observation',
  templateUrl: './latest-observation.component.html',
  styleUrls: ['./latest-observation.component.scss']
})
export class LatestObservationComponent implements OnInit {

  @Input() observation: Observation;

  constructor() { }

  ngOnInit(): void {
  }

}
