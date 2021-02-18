package com.proptechos.clients;

import com.proptechos.model.definitions.BuildingComponentClass;
import com.proptechos.model.definitions.DeviceFunctionType;
import com.proptechos.model.definitions.MeasurementUnit;
import com.proptechos.model.definitions.PlacementContext;
import com.proptechos.model.definitions.QuantityKind;
import com.proptechos.model.definitions.RoomType;
import com.proptechos.service.RecIndividualService;
import com.proptechos.service.ServiceFactory;
import org.springframework.stereotype.Component;

@Component
public class RecIndividualsServiceClient {

  private final RecIndividualService recIndividualService;

  public RecIndividualsServiceClient(ServiceFactory serviceFactory) {
    this.recIndividualService = serviceFactory.recIndividualService();
  }

  public BuildingComponentClass firstBuildingComponentClass() {
    return recIndividualService.getBuildingComponentClasses().get(0);
  }

  public RoomType firstRoomType() {
    return recIndividualService.getRoomTypes().get(0);
  }

  public DeviceFunctionType firstDeviceFunctionType() {
    return recIndividualService.getDeviceFunctionTypes().get(0);
  }

  public MeasurementUnit firstMeasurementUnit() {
    return recIndividualService.getMeasurementUnits().get(0);
  }

  public PlacementContext firstPlacementContext() {
    return recIndividualService.getPlacementContexts().get(0);
  }

  public QuantityKind firstQuantityKind() {
    return recIndividualService.getQuantityKinds().get(0);
  }

}
