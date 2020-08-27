import { Injectable } from '@angular/core';
import { MsalService } from '@azure/msal-angular';
import { environment as env } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';
import {Device, Observation} from '../common/rectypes';

@Injectable({
  providedIn: 'root'
})
export class ProptechosService {

  constructor(
    private msalService: MsalService,
    private http: HttpClient
  ) { }

  getActuator(id: string): Observable<Device> {
    return this.http.get<Device>(`${env.baseUrl}/json/actuator/${id}`);
  }

  getSensor(id: string): Observable<Device> {
    return this.http.get<Device>(`${env.baseUrl}/json/sensor/${id}`);
  }

  getLatestObservation(sensorId: string): Observable<Observation> {
    return this.http.get<Observation>(`${env.baseUrl}/sensor/${sensorId}/observation/latest`);
  }
}

