import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ActuationInterface} from '../../common/rectypes';
import {ProptechosService} from '../../services/proptechos.service';
import {BehaviorSubject, Observable} from 'rxjs';
import {scan} from 'rxjs/operators';

@Component({
  selector: 'app-interface-dropdown',
  templateUrl: './interface-dropdown.component.html',
  styleUrls: ['./interface-dropdown.component.scss']
})
export class InterfaceDropdownComponent implements OnInit {

  @Output() interfaceChosen: EventEmitter<string> = new EventEmitter<string>();

  options = new BehaviorSubject<Array<ActuationInterface>>(new Array<ActuationInterface>());
  options$: Observable<Array<ActuationInterface>>;

  private lastPage: boolean;
  private pageNumber = 0;
  private size = 10;

  constructor(private proptechosService: ProptechosService) {
    this.options$ = this.options.asObservable().pipe(
      scan((acc: Array<ActuationInterface>, curr: Array<ActuationInterface>) => {
        return [...acc, ...curr];
      }, [])
    );
  }

  ngOnInit(): void {
    this.getNextBatch();
  }

  chooseInterface(event: Event): void {
    const selectedInterface = event as unknown as ActuationInterface;
    this.interfaceChosen.emit(selectedInterface.id);
  }

  getNextBatch() {
    if (!this.lastPage) {
      this.proptechosService.getActuationInterfaces(this.pageNumber, this.size).subscribe((res) => {
        this.options.next(res.content);
        this.pageNumber++;
        this.lastPage = res.last;
      }, (error) => {
        console.log(error);
      });
    }
  }

}
