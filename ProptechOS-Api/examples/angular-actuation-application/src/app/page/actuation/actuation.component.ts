import {Component, Input} from '@angular/core';
import {ActuationRequest, ActuationRequestResponse} from '../../common/rectypes';
import {MsalService} from '@azure/msal-angular';
import {ProptechosService} from '../../services/proptechos.service';
import {FormBuilder, Validators} from '@angular/forms';
import {AxiomInfo} from '../../common/basetypes';

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
  actuationRequest: ActuationRequest;

  private actuationInterfaceId = '';

  constructor(private fb: FormBuilder,
              private msalService: MsalService,
              private proptechosService: ProptechosService) { }

  addActuationInterface(aiId: string) {
    this.actuationInterfaceId = aiId;
  }

  sendActuation(actuationReq): void {
    const self = this;
    const request = this.buildRequest();
    this.proptechosService.sendActuation(this.actuatorId, request).subscribe((response) => {
      self.actuationRequest = request;
      this.actuationRequestResponse = response;
    });
    actuationReq.resetForm();
  }

  newActuation(): void {
    this.actuationRequestResponse = undefined;
    this.actuationRequest = undefined;
  }

  actuationRequestInfo(req: ActuationRequest): Array<AxiomInfo> {
    return [
      { property: 'Payload: ', value: req.payload },
      { property: 'Target interface: ', value: req.targetInterface }
    ];
  }

  actuationRequestResponseInfo(res: ActuationRequestResponse): Array<AxiomInfo> {
    return [
      { property: 'Request accepted: ', value: String(res.requestAccepted) },
      { property: 'Id: ', value: res.id},
      { property: 'Generated command response: ', value: res.generatedCommandResponse },
      { property: 'Observed by: ', value: res.actuationObservedBy }
    ];
  }

  private buildRequest(): ActuationRequest {
    const payloadData =
      this.actuationRequestForm.getRawValue().payload;
    return {
      payload: payloadData,
      targetInterface: this.actuationInterfaceId
    };
  }

}
