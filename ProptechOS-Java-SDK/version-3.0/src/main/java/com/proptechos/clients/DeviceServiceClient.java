package com.proptechos.clients;

import com.proptechos.model.common.IDevice;
import com.proptechos.model.common.Paged;
import com.proptechos.service.DeviceService;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.filters.common.AliasFilter;
import com.proptechos.service.filters.common.LitteraFilter;
import org.springframework.stereotype.Component;

@Component
public class DeviceServiceClient {

  private final DeviceService deviceService;

  public DeviceServiceClient(ServiceFactory serviceFactory) {
    this.deviceService = serviceFactory.deviceService();
  }

  public Paged<IDevice> listFirstTenAxioms() {
    return deviceService.getPage(0, 10);
  }

  public Paged<IDevice> listAxiomsByLitteraPart(String littera) {
    return deviceService
        .getPageFiltered(0, 10, new LitteraFilter(littera));
  }

  public Paged<IDevice> listAxiomsByAliasId(String aliasId) {
    return deviceService
        .getPageFiltered(0, 10, new AliasFilter(aliasId));
  }

  public IDevice getAxiomById() {
    IDevice device = listFirstTenAxioms().getContent().iterator().next();

    return deviceService.getById(device.getId());
  }

}
