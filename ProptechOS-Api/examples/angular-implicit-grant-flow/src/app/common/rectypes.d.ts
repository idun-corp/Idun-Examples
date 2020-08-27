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

export interface Observation {
  observationTime: Date;
  value: string;
}
