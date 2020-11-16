package com.proptechos.clients;

import com.proptechos.model.Building;
import com.proptechos.model.Point;
import com.proptechos.model.common.Paged;
import com.proptechos.service.RealEstateComponentService;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.filters.AliasFilter;
import com.proptechos.service.filters.LitteraFilter;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class BuildingServiceClient {

  private final RealEstateComponentService realEstateComponentService;

  public BuildingServiceClient(ServiceFactory serviceFactory) {
    this.realEstateComponentService = serviceFactory.realEstateComponentService();
  }

  public Paged<Building> listFirstTenAxioms() {
    return realEstateComponentService.getPage(0, 10);
  }

  public Paged<Building> listAxiomsByLitteraPart(String littera) {
    return realEstateComponentService
        .getPageFiltered(0, 10, new LitteraFilter(littera));
  }

  public Paged<Building> listAxiomsByAliasId(String aliasId) {
    return realEstateComponentService
        .getPageFiltered(0, 10, new AliasFilter(aliasId));
  }

  public Building getAxiomById() {
    Building building = listFirstTenAxioms().getContent().iterator().next();
    return realEstateComponentService.getById(building.getId());
  }

  public List<Building> getInRange(Point point) {
    return realEstateComponentService.getBuildingsInRange(point);
  }

}
