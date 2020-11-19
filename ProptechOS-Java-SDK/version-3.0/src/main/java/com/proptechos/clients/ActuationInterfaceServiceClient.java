package com.proptechos.clients;

import com.proptechos.model.actuation.ActuationInterface;
import com.proptechos.model.common.Paged;
import com.proptechos.service.ActuationInterfaceService;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.filters.actuationiterface.KeyValueDataTypeFilter;
import com.proptechos.service.filters.actuationiterface.RestrictionTypeFilter;
import com.proptechos.service.filters.common.LitteraFilter;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ActuationInterfaceServiceClient {

  private final ActuationInterfaceService actuationInterfaceService;

  public ActuationInterfaceServiceClient(ServiceFactory serviceFactory) {
    this.actuationInterfaceService = serviceFactory.actuationInterfaceService();
  }

  public Paged<ActuationInterface> listFirstTenAxioms() {
    return actuationInterfaceService.getPage(0, 10);
  }

  public Paged<ActuationInterface> listAxiomsByLitteraPart(String littera) {
    return actuationInterfaceService
        .getPageFiltered(0, 10, new LitteraFilter(littera));
  }

  public Paged<ActuationInterface> listAxiomsByDataTypes(List<String> dataTypes) {
    return actuationInterfaceService
        .getPageFiltered(0, 10, new KeyValueDataTypeFilter(dataTypes));
  }

  public Paged<ActuationInterface> listAxiomsByRestrictionTypes(List<String> restrictionTypes) {
    return actuationInterfaceService
        .getPageFiltered(0, 10, new RestrictionTypeFilter(restrictionTypes));
  }

  public ActuationInterface getAxiomById() {
    ActuationInterface actuationInterface =
        listFirstTenAxioms().getContent().iterator().next();

    return actuationInterfaceService.getById(actuationInterface.getId());
  }

}
