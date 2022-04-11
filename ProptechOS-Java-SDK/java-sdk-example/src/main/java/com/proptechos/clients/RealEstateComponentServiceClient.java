package com.proptechos.clients;

import com.proptechos.model.Point;
import com.proptechos.model.common.IRealEstateComponent;
import com.proptechos.model.common.Paged;
import com.proptechos.service.RealEstateComponentService;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.filters.common.AliasFilter;
import com.proptechos.service.filters.common.LitteraFilter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RealEstateComponentServiceClient {

  private final RealEstateComponentService realEstateComponentService;

  public RealEstateComponentServiceClient(ServiceFactory serviceFactory) {
    this.realEstateComponentService = serviceFactory.realEstateComponentService();
  }

  public Paged<IRealEstateComponent> listFirstTenAxioms() {
    return realEstateComponentService.getPage(0, 10);
  }

  public Paged<IRealEstateComponent> listAxiomsByLitteraPart(String littera) {
    return realEstateComponentService
        .getPageFiltered(0, 10, new LitteraFilter(littera));
  }

  public Paged<IRealEstateComponent> listAxiomsByAliasId(String aliasId) {
    return realEstateComponentService
        .getPageFiltered(0, 10, new AliasFilter(aliasId));
  }

  public IRealEstateComponent getAxiomById() {
    IRealEstateComponent realEstateComponent = listFirstTenAxioms().getContent().iterator().next();
    return realEstateComponentService.getById(realEstateComponent.getId());
  }

  public List<IRealEstateComponent> getInRange(Point point) {
    return realEstateComponentService.getRealEstateComponentsInRange(point);
  }

}
