package com.proptechos.clients;

import com.proptechos.model.PropertyOwner;
import com.proptechos.model.common.Paged;
import com.proptechos.service.PropertyOwnerService;
import com.proptechos.service.ServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PropertyOwnerServiceClient {

  private final PropertyOwnerService propertyOwnerService;

  @Autowired
  public PropertyOwnerServiceClient(ServiceFactory serviceFactory) {
    this.propertyOwnerService = serviceFactory.propertyOwnerService();
  }

  public Paged<PropertyOwner> listFirstTenAxioms() {
    return propertyOwnerService.getPage(0, 10);
  }

  public PropertyOwner getAxiomById() {
    PropertyOwner propertyOwner = listFirstTenAxioms().getContent().iterator().next();

    return propertyOwnerService.getById(propertyOwner.getId());
  }

}
