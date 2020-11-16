package com.proptechos.clients;

import com.proptechos.model.AliasNamespace;
import com.proptechos.model.common.Paged;
import com.proptechos.service.AliasNamespaceService;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.filters.LitteraFilter;
import org.springframework.stereotype.Component;

@Component
public class AliasNamespaceServiceClient {

  private final AliasNamespaceService namespaceService;

  public AliasNamespaceServiceClient(ServiceFactory serviceFactory) {
    this.namespaceService = serviceFactory.aliasNamespaceService();
  }

  public Paged<AliasNamespace> listFirstTenAxioms() {
    return namespaceService.getPage(0, 10);
  }

  public Paged<AliasNamespace> listAxiomsByLitteraPart(String littera) {
    return namespaceService
        .getPageFiltered(0, 10, new LitteraFilter(littera));
  }

  public AliasNamespace getAxiomById() {
    AliasNamespace namespace = listFirstTenAxioms().getContent().iterator().next();

    return namespaceService.getById(namespace.getId());
  }

}
