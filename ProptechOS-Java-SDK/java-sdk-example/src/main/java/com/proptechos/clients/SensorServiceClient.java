package com.proptechos.clients;

import com.proptechos.model.Observation;
import com.proptechos.model.Sensor;
import com.proptechos.model.common.IDevice;
import com.proptechos.model.common.Paged;
import com.proptechos.service.SensorService;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.filters.common.AliasFilter;
import com.proptechos.service.filters.common.LitteraFilter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SensorServiceClient {

  private final SensorService sensorService;

  public SensorServiceClient(ServiceFactory serviceFactory) {
    this.sensorService = serviceFactory.sensorService();
  }

  public Paged<Sensor> listFirstTenAxioms() {
    return sensorService.getPage(0, 10);
  }

  public Paged<Sensor> listAxiomsByLitteraPart(String littera) {
    return sensorService
        .getPageFiltered(0, 10, new LitteraFilter(littera));
  }

  public Paged<Sensor> listAxiomsByAliasId(String aliasId) {
    return sensorService
        .getPageFiltered(0, 10, new AliasFilter(aliasId));
  }

  public Sensor getAxiomById() {
    Sensor device = listFirstTenAxioms().getContent().iterator().next();

    return sensorService.getById(device.getId());
  }

  public List<Observation> getObservations(UUID sensorId) {
    return sensorService.getObservationsBySensorIdForPeriod(sensorId, null, null);
  }

}
