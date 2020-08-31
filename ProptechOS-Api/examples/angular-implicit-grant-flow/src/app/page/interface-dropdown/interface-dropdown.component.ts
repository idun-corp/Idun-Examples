import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActuationInterface} from '../../common/rectypes';
import {ProptechosService} from '../../services/proptechos.service';

@Component({
  selector: 'app-interface-dropdown',
  templateUrl: './interface-dropdown.component.html',
  styleUrls: ['./interface-dropdown.component.scss']
})
export class InterfaceDropdownComponent implements OnInit {

  @Input() interfaces: Array<ActuationInterface>;
  @Output() interfaceChosen: EventEmitter<string> = new EventEmitter<string>();

  constructor(private proptechosService: ProptechosService) {
    this.interfaces = new Array<ActuationInterface>();
  }

  ngOnInit(): void {
    this.proptechosService.getActuationInterfaces(0, 15).subscribe((interfaces) => {
      this.interfaces = this.interfaces.concat(interfaces.content);
    });
  }

  chooseInterface(event: Event): void {
    const selectedInterface = event as unknown as ActuationInterface;
    this.interfaceChosen.emit(selectedInterface.id);
  }

}
