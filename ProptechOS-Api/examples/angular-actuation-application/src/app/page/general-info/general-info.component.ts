import {Component, Input, OnInit} from '@angular/core';
import {AxiomInfo} from '../../common/basetypes';

@Component({
  selector: 'app-general-info',
  templateUrl: './general-info.component.html',
  styleUrls: ['./general-info.component.scss']
})
export class GeneralInfoComponent implements OnInit {

  @Input() title: string;
  @Input() dataSource: Array<AxiomInfo>;

  constructor() { }

  ngOnInit(): void {
  }

}
