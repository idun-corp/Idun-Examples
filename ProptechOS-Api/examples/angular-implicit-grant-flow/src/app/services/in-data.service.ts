import { Injectable } from '@angular/core';



@Injectable({
  providedIn: 'root'
})
export class InDataService {

  constructor() { }

  retrieveInData(): InData {
    return {
      sensorId : 'bdae086c-1e39-4e6b-8eb0-002b97c28be7',
      actuatorId : '6273c056-947d-4d9b-b5ce-007e4342e1b5',
      actuationInterfaceId : 'b18e585c-5a02-4077-949a-b435735c348f',
      sensorToCheckId : '1fbbdade-b546-407e-98a8-83abc37eb71b'
    };
  }
}

export interface InData {
  sensorId: string;
  actuatorId: string;
  actuationInterfaceId: string;
  sensorToCheckId: string;
}
