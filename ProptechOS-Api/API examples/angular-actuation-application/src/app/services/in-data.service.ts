import { Injectable } from '@angular/core';



@Injectable({
  providedIn: 'root'
})
export class InDataService {

  inData: InData;

  constructor() { }

  retrieveInData(): InData {
    return this.inData;
  }
}

export interface InData {
  sensorId: string;
  actuatorId: string;
  sensorToCheckId?: string;
}
