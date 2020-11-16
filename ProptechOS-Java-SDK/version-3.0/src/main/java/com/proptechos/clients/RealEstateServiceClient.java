package com.proptechos.clients;

import com.proptechos.model.Point;
import com.proptechos.model.RealEstate;
import com.proptechos.model.common.Paged;
import com.proptechos.service.RealEstateService;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.filters.AliasFilter;
import com.proptechos.service.filters.LitteraFilter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RealEstateServiceClient {

  private final RealEstateService realEstateService;

  @Autowired
  public RealEstateServiceClient(ServiceFactory serviceFactory) {
    this.realEstateService = serviceFactory.realEstateService();
  }

  public Paged<RealEstate> listFirstTenAxioms() {
    return realEstateService.getPage(0, 10);
  }

  public Paged<RealEstate> listAxiomsByLitteraPart(String littera) {
    return realEstateService
        .getPageFiltered(0, 10, new LitteraFilter(littera));
  }

  public Paged<RealEstate> listAxiomsByAliasId(String aliasId) {
    return realEstateService
        .getPageFiltered(0, 10, new AliasFilter(aliasId));
  }

  public RealEstate getAxiomById() {
    RealEstate realEstate = listFirstTenAxioms().getContent().iterator().next();

    return realEstateService.getById(realEstate.getId());
  }

  public List<RealEstate> getInRange(Point point) {
    return realEstateService.getRealEstatesInRange(point);
  }

}
