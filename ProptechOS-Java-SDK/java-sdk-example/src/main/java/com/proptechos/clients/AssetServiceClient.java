package com.proptechos.clients;

import com.proptechos.model.Asset;
import com.proptechos.model.common.Paged;
import com.proptechos.service.AssetService;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.filters.common.AliasFilter;
import com.proptechos.service.filters.common.LitteraFilter;
import org.springframework.stereotype.Component;

@Component
public class AssetServiceClient {

  private final AssetService assetService;

  public AssetServiceClient(ServiceFactory serviceFactory) {
    this.assetService = serviceFactory.assetService();
  }

  public Paged<Asset> listFirstTenAxioms() {
    return assetService.getPage(0, 10);
  }

  public Paged<Asset> listAxiomsByLitteraPart(String littera) {
    return assetService
        .getPageFiltered(0, 10, new LitteraFilter(littera));
  }

  public Paged<Asset> listAxiomsByAliasId(String aliasId) {
    return assetService
        .getPageFiltered(0, 10, new AliasFilter(aliasId));
  }

  public Asset getAxiomById() {
    Asset asset =
        listFirstTenAxioms().getContent().iterator().next();

    return assetService.getById(asset.getId());
  }
}
