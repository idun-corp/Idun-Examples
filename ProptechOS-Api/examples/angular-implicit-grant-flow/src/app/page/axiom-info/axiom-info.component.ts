import { Component, Input, OnInit } from '@angular/core';
import {Device} from '../../common/rectypes';
import {AxiomInfo} from '../../common/basetypes';

@Component({
  selector: 'app-axiom-info',
  templateUrl: './axiom-info.component.html',
  styleUrls: ['./axiom-info.component.scss']
})
export class AxiomInfoComponent implements OnInit {

  @Input() device: Device;
  @Input() dataSource: Array<AxiomInfo>;
  displayedColumns: Array<string>;

  constructor() {}

  ngOnInit(): void {
    this.displayedColumns = ['property', 'value'];
  }
}
