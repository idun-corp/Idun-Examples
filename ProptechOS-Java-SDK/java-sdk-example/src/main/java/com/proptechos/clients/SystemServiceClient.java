package com.proptechos.clients;

import com.proptechos.model.System;
import com.proptechos.model.common.IBaseClass;
import com.proptechos.model.common.Paged;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.SystemService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class SystemServiceClient {

    private final SystemService systemService;

    public SystemServiceClient(ServiceFactory serviceFactory) {
        this.systemService = serviceFactory.systemService();
    }

    public Paged<System> listFirstTenAxioms() {
        return systemService.getPage(0, 10);
    }

    public System getAxiomById() {
        System system = listFirstTenAxioms().getContent().iterator().next();

        return systemService.getById(system.getId());
    }

    public Paged<IBaseClass> listFirstTenIncludedAxioms() {
        System system = listFirstTenAxioms().getContent().iterator().next();

        return systemService.getIncludedAxioms(system.getId(), 0, 10);
    }

    public List<UUID> includeAxioms(UUID...axiomIds) {
        System system = listFirstTenAxioms().getContent().iterator().next();

        return systemService.includeAxioms(system.getId(), axiomIds);
    }

    public List<UUID> excludeAxioms(UUID...axiomIds) {
        System system = listFirstTenAxioms().getContent().iterator().next();

        return systemService.excludeAxioms(system.getId(), axiomIds);
    }
}
