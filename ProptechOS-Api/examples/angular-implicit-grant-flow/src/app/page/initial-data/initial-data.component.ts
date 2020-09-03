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
    sensorId: [null, Validators.required],
    actuatorId: [null, Validators.required]
  });

  @Output() formSubmitted: EventEmitter<boolean> = new EventEmitter<boolean>();

  constructor(private fb: FormBuilder,
              private inDataService: InDataService) {}

  onSubmit() {
    this.inDataService.inData = this.initialForm.getRawValue();
    this.formSubmitted.emit(true);
  }
}
