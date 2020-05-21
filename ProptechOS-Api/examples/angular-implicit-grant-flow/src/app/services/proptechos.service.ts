import { Injectable } from '@angular/core';
import { MsalService } from '@azure/msal-angular';
import { environment as env } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProptechosService {

 private accessToken: string;

  constructor(
    private msalService: MsalService,
    private http: HttpClient
  ) {
    msalService.acquireTokenSilent(env.auth.consentScopes).then(token => {
      this.accessToken = token;
    });
  }

  getActuator(id: string) {
    const headersMap = new HttpHeaders({'Authorization': 'Bearer ' + this.accessToken});
    return this.http.get(`${env.baseUrl}/json/actuator/${id}`, {
      headers: headersMap
    });
  }

  getSensor(id: string) {
    const headersMap = new HttpHeaders({'Authorization': 'Bearer ' + this.accessToken});
    return this.http.get(`${env.baseUrl}/json/sensor/${id}`, {
      headers: headersMap
    });
  }

  getLatestObservation(sensorId: string) {
    const headersMap = new HttpHeaders({'Authorization': 'Bearer ' + this.accessToken});
    return this.http.get(`${env.baseUrl}/sensor/${sensorId}/observation/latest`, {
      headers: headersMap
    });
  }
}

