import {Component, Input} from '@angular/core';
import {ActuationRequest, ActuationRequestResponse} from '../../common/rectypes';
import {MsalService} from '@azure/msal-angular';
import {ProptechosService} from '../../services/proptechos.service';
import {FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-actuation',
  templateUrl: './actuation.component.html',
  styleUrls: ['./actuation.component.scss']
})
export class ActuationComponent {

  @Input() value: string;
  @Input() actuatorId: string;

  actuationRequestForm = this.fb.group({
    payload: [null, Validators.required]
  });

  actuationRequestResponse: ActuationRequestResponse;

  private actuationInterfaceId = '';

  constructor(private fb: FormBuilder,
              private msalService: MsalService,
              private proptechosService: ProptechosService) { }

  addActuationInterface(aiId: string) {
    this.actuationInterfaceId = aiId;
  }

  sendActuation(): void {
    const request = this.buildRequest();
    this.proptechosService.sendActuation(this.actuatorId, request).subscribe((response) => {
      this.actuationRequestResponse = response;
    });
  }

  newActuation(): void {
    this.actuationRequestResponse = undefined;
  }

  private buildRequest(): ActuationRequest {
    const payloadData =
      this.actuationRequestForm.getRawValue().payload;
    const agent = this.msalService.getAccount().userName;
    return {
      payload: payloadData,
      requestingAgent: agent,
      targetInterface: this.actuationInterfaceId
    };
  }

}
