package com.proptechos.clients;

import com.proptechos.model.Collection;
import com.proptechos.model.common.IBaseClass;
import com.proptechos.model.common.Paged;
import com.proptechos.service.CollectionService;
import com.proptechos.service.ServiceFactory;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class CollectionServiceClient {

  private final CollectionService collectionService;

  public CollectionServiceClient(ServiceFactory serviceFactory) {
    this.collectionService = serviceFactory.collectionService();
  }

  public Paged<Collection> listFirstTenAxioms() {
    return collectionService.getPage(0, 10);
  }

  public Collection getAxiomById() {
    Collection collection = listFirstTenAxioms().getContent().iterator().next();

    return collectionService.getById(collection.getId());
  }

  public Paged<IBaseClass> listFirstTenIncludedAxioms() {
    Collection collection = listFirstTenAxioms().getContent().iterator().next();

    return collectionService.getIncludedAxioms(collection.getId(), 0, 10);
  }

  public List<UUID> includeAxioms(UUID...axiomIds) {
    Collection collection = listFirstTenAxioms().getContent().iterator().next();

    return collectionService.includeAxioms(collection.getId(), axiomIds);
  }

  public List<UUID> excludeAxioms(UUID...axiomIds) {
    Collection collection = listFirstTenAxioms().getContent().iterator().next();

    return collectionService.excludeAxioms(collection.getId(), axiomIds);
  }

}
