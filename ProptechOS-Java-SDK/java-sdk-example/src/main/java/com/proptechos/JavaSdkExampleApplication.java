package com.proptechos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proptechos.clients.*;
import com.proptechos.model.AliasNamespace;
import com.proptechos.model.Asset;
import com.proptechos.model.PropertyOwner;
import com.proptechos.model.RealEstate;
import com.proptechos.model.actuation.ActuationInterface;
import com.proptechos.model.common.IBuildingComponent;
import com.proptechos.model.common.IDevice;
import com.proptechos.model.common.IRealEstateComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@Slf4j
@SpringBootApplication
public class JavaSdkExampleApplication {

	private final ObjectMapper mapper = new ObjectMapper();

	private final TwinServiceClient twinServiceClient;
	private final PropertyOwnerServiceClient propertyOwnerServiceClient;
	private final RealEstateServiceClient realEstateServiceClient;
	private final RealEstateComponentServiceClient realEstateComponentServiceClient;
	private final BuildingComponentServiceClient buildingComponentServiceClient;
	private final AssetServiceClient assetServiceClient;
	private final DeviceServiceClient deviceServiceClient;
	private final ActuationInterfaceServiceClient actuationInterfaceServiceClient;
	private final AliasNamespaceServiceClient namespaceServiceClient;
	private final CollectionServiceClient collectionServiceClient;
	private final SystemServiceClient systemServiceClient;
	private final RecIndividualsServiceClient recIndividualsServiceClient;

	@Autowired
	public JavaSdkExampleApplication(
			TwinServiceClient twinServiceClient,
			PropertyOwnerServiceClient propertyOwnerServiceClient,
			RealEstateServiceClient realEstateServiceClient,
			RealEstateComponentServiceClient realEstateComponentServiceClient,
			BuildingComponentServiceClient buildingComponentServiceClient,
			AssetServiceClient assetServiceClient,
			DeviceServiceClient deviceServiceClient,
			ActuationInterfaceServiceClient actuationInterfaceServiceClient,
			AliasNamespaceServiceClient namespaceServiceClient,
			CollectionServiceClient collectionServiceClient,
			SystemServiceClient systemServiceClient,
			RecIndividualsServiceClient recIndividualsServiceClient) {
		this.twinServiceClient = twinServiceClient;
		this.propertyOwnerServiceClient = propertyOwnerServiceClient;
		this.realEstateServiceClient = realEstateServiceClient;
		this.realEstateComponentServiceClient = realEstateComponentServiceClient;
		this.buildingComponentServiceClient = buildingComponentServiceClient;
		this.assetServiceClient = assetServiceClient;
		this.deviceServiceClient = deviceServiceClient;
		this.actuationInterfaceServiceClient = actuationInterfaceServiceClient;
		this.namespaceServiceClient = namespaceServiceClient;
		this.collectionServiceClient = collectionServiceClient;
		this.systemServiceClient = systemServiceClient;
		this.recIndividualsServiceClient = recIndividualsServiceClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaSdkExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			log.info("Return PropertyOwner: ");
			PropertyOwner propertyOwner = propertyOwnerServiceClient.getAxiomById();
			printJson(propertyOwner);

			log.info("Return RealEstate: ");
			RealEstate realEstate = realEstateServiceClient.getAxiomById();
			printJson(realEstate);

			log.info("Return Building: ");
			IRealEstateComponent realEstateComponent = realEstateComponentServiceClient.getAxiomById();
			printJson(realEstateComponent);

			log.info("Return BuildingComponent: ");
			IBuildingComponent buildingComponent = buildingComponentServiceClient.getAxiomById();
			printJson(buildingComponent);

			log.info("Return Asset: ");
			Asset asset = assetServiceClient.getAxiomById();
			printJson(asset);

			log.info("Return Device: ");
			IDevice device = deviceServiceClient.getAxiomById();
			printJson(device);

			log.info("Return ActuationInterface: ");
			ActuationInterface actuationInterface = actuationInterfaceServiceClient.getAxiomById();
			printJson(actuationInterface);

			log.info("Return AliasNamespace: ");
			AliasNamespace aliasNamespace = namespaceServiceClient.getAxiomById();
			printJson(aliasNamespace);

			log.info("Return Collection: ");
			printJson(collectionServiceClient.getAxiomById());

			log.info("Return axioms included in Collection: ");
			printJson(collectionServiceClient.listFirstTenIncludedAxioms());

			log.info("Return axioms included in System: ");
			printJson(systemServiceClient.listFirstTenIncludedAxioms());

			log.info("Return RecIndividuals: ");
			printJson(recIndividualsServiceClient.firstBuildingComponentClass());
			printJson(recIndividualsServiceClient.firstRoomType());
			printJson(recIndividualsServiceClient.firstDeviceFunctionType());
			printJson(recIndividualsServiceClient.firstMeasurementUnit());
			printJson(recIndividualsServiceClient.firstPlacementContext());
			printJson(recIndividualsServiceClient.firstQuantityKind());

			log.info("Return twins: ");
			printJson(twinServiceClient.getTwin(realEstate.getId()));
			printJson(twinServiceClient.getTwin(realEstateComponent.getId()));
			printJson(twinServiceClient.getTwin(buildingComponent.getId()));
			printJson(twinServiceClient.getTwin(asset.getId()));
			printJson(twinServiceClient.getTwin(device.getId()));
			printJson(twinServiceClient.getTwin(actuationInterface.getId()));
			printJson(twinServiceClient.getTwin(aliasNamespace.getId()));
			log.info("-------------- ALL DONE ----------------");
		};
	}

	private void printJson(Object object) throws JsonProcessingException {
		log.info("\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
	}

}
