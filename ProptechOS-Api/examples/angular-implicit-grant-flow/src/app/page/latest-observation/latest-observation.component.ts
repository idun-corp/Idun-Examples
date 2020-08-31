import { Component, Input, OnInit } from '@angular/core';
import {Observation} from '../../common/rectypes';
import {ProptechosService} from '../../services/proptechos.service';
import {UtilsService} from '../../services/utils.service';

@Component({
  selector: 'app-latest-observation',
  templateUrl: './latest-observation.component.html',
  styleUrls: ['./latest-observation.component.scss']
})
export class LatestObservationComponent implements OnInit {

  @Input() sensorId: string;
  observation: Observation;

  constructor(private apiService: ProptechosService) { }

  ngOnInit(): void {
  }

  getObservation(): void {
    this.apiService.getLatestObservation(this.sensorId).subscribe((data) => {
      this.observation = data;
    });
  }

  showObservation(): boolean {
    return UtilsService.nonNull(this.observation);
  }

}
