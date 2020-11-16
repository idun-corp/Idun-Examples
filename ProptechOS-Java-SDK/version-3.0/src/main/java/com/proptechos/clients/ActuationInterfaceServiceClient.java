package com.proptechos.clients;

import com.proptechos.model.actuation.ActuationInterface;
import com.proptechos.model.common.Paged;
import com.proptechos.service.ActuationInterfaceService;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.filters.LitteraFilter;
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

  public ActuationInterface getAxiomById() {
    ActuationInterface actuationInterface =
        listFirstTenAxioms().getContent().iterator().next();

    return actuationInterfaceService.getById(actuationInterface.getId());
  }

}
