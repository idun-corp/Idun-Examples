package com.proptechos.clients;

import com.proptechos.model.common.IBaseClass;
import com.proptechos.service.ServiceFactory;
import com.proptechos.service.TwinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TwinServiceClient {

    private final TwinService twinService;

    @Autowired
    public TwinServiceClient(ServiceFactory serviceFactory) {
        this.twinService = serviceFactory.twinService();
    }

    public IBaseClass getTwin(UUID twinId) {
        return twinService.getById(twinId);
    }

}
