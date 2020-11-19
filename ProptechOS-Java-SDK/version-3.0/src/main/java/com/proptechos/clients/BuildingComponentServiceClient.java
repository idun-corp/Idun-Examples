package com.proptechos.clients;

import com.proptechos.model.common.IBuildingComponent;
import com.proptechos.model.common.Paged;
import com.proptechos.service.BuildingComponentService;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.filters.common.AliasFilter;
import com.proptechos.service.filters.common.LitteraFilter;
import org.springframework.stereotype.Component;

@Component
public class BuildingComponentServiceClient {

  private final BuildingComponentService buildingComponentService;

  public BuildingComponentServiceClient(ServiceFactory serviceFactory) {
    this.buildingComponentService = serviceFactory.buildingComponentService();
  }

  public Paged<IBuildingComponent> listFirstTenAxioms() {
    return buildingComponentService.getPage(0, 10);
  }

  public Paged<IBuildingComponent> listAxiomsByLitteraPart(String littera) {
    return buildingComponentService
        .getPageFiltered(0, 10, new LitteraFilter(littera));
  }

  public Paged<IBuildingComponent> listAxiomsByAliasId(String aliasId) {
    return buildingComponentService
        .getPageFiltered(0, 10, new AliasFilter(aliasId));
  }

  public IBuildingComponent getAxiomById() {
    IBuildingComponent buildingComponent =
        listFirstTenAxioms().getContent().iterator().next();

    return buildingComponentService.getById(buildingComponent.getId());
  }
}
