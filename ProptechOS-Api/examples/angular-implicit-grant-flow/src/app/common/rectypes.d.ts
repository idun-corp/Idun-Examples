export interface BaseResponse {
  content: Array<ActuationInterface>;
}

export interface Device {
  id: string;
  class: string;
  littera: string;
  popularName: string;
  deviceMeasurementUnit: string;
  deviceQuantityKind: string;
  devicePlacementContext: string;
  hasActuationInterface?: string;
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
  requestingAgent: string;
}

export interface ActuationRequestResponse {
  id: string;
  actuationObservedBy: string;
  generatedCommandResponse: string;
  requestAccepted: boolean;
}
