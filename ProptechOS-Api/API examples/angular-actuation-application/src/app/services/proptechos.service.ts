import {Injectable} from '@angular/core';
import {MsalService} from '@azure/msal-angular';
import {environment as env} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {
  ActuationRequest,
  ActuationRequestResponse,
  Actuator,
  BaseResponse,
  Observation, Sensor
} from '../common/rectypes';

@Injectable({
  providedIn: 'root'
})
export class ProptechosService {

  constructor(
    private msalService: MsalService,
    private http: HttpClient
  ) {}

  getActuator(id: string): Observable<Actuator> {
    return this.http.get<Actuator>(`${env.baseUrl}/json/actuator/${id}`);
  }

  getSensor(id: string): Observable<Sensor> {
    return this.http.get<Sensor>(`${env.baseUrl}/json/sensor/${id}`);
  }

  getActuationInterfaces(page: number, size: number): Observable<BaseResponse> {
    return this.http.get<BaseResponse>(
      `${env.baseUrl}/json/actuationinterface`,
      {
        params: {
          page: page.toString(),
          size: size.toString()
        }
      });
  }

  getLatestObservation(sensorId: string): Observable<Observation> {
    return this.http.get<Observation>(`${env.baseUrl}/json/sensor/${sensorId}/observation/latest`);
  }

  sendActuation(actuatorId: string, request: ActuationRequest): Observable<ActuationRequestResponse> {
    return this.http.put<ActuationRequestResponse>(`${env.baseUrl}/json/actuator/${actuatorId}/actuation`, request);
  }
}

