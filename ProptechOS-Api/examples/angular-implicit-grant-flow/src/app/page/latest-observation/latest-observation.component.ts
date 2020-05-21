import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-latest-observation',
  templateUrl: './latest-observation.component.html',
  styleUrls: ['./latest-observation.component.scss']
})
export class LatestObservationComponent implements OnInit {

  @Input() observation: Object;

  constructor() { }

  ngOnInit(): void {
  }

}
