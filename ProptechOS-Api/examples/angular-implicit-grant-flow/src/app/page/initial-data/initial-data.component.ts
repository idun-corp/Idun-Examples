import {Component, EventEmitter, Output} from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import {InDataService} from '../../services/in-data.service';

@Component({
  selector: 'app-initial-data',
  templateUrl: './initial-data.component.html',
  styleUrls: ['./initial-data.component.scss']
})
export class InitialDataComponent {
  initialForm = this.fb.group({
    sensorId: ['fa8fbe5e-19f4-4ef2-b853-d6327f481954', Validators.required],
    actuatorId: ['6f7f74df-18d7-4790-bf71-52be4dbf125e', Validators.required]
  });

  @Output() formSubmitted: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(private fb: FormBuilder,
              private inDataService: InDataService) {}

  onSubmit() {
    this.inDataService.inData = this.initialForm.getRawValue();
    this.formSubmitted.emit(true);
  }
}
