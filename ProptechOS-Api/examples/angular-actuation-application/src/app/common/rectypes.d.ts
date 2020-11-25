export interface BaseResponse {
  content: Array<ActuationInterface>;
  last: boolean;
}

export interface Device {
  id: string;
  class: string;
  littera: string;
  popularName: string;
}

export interface Actuator extends Device {
  deviceMeasurementUnit: string;
  deviceQuantityKind: string;
  devicePlacementContext: string;
  hasDefaultActuationInterface: string;
  hasActuationInterface?: Array<string>;
  observedBy: string;
}

export interface Sensor extends Device {
  deviceMeasurementUnit: string;
  deviceQuantityKind: string;
  devicePlacementContext: string;
  observesActuator: string;
}

export interface ActuationInterface {
  id: string;
  littera: string;
  popularName: string;
}

export interface Observation {
  observationTime: string;
  value: any;
}

export interface ActuationRequest {
  targetInterface: string;
  payload: string;
}

export interface ActuationRequestResponse {
  id: string;
  actuationObservedBy: string;
  generatedCommandResponse: string;
  requestAccepted: boolean;
}
