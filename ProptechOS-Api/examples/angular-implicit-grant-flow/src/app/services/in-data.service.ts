import { Injectable } from '@angular/core';



@Injectable({
  providedIn: 'root'
})
export class InDataService {

  constructor() { }

  retrieveInData(): InData {
    return {
      sensorId : 'bdae086c-1e39-4e6b-8eb0-002b97c28be7',
      actuatorId : '32043d03-9b6b-4a5c-9088-6e3d87c84efd',
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
