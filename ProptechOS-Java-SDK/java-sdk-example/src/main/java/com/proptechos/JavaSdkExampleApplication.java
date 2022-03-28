package com.proptechos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proptechos.clients.*;
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

	private final PropertyOwnerServiceClient propertyOwnerServiceClient;
	private final RealEstateServiceClient realEstateServiceClient;
	private final RealEstateComponentClient buildingServiceClient;
	private final BuildingComponentServiceClient buildingComponentServiceClient;
	private final AssetServiceClient assetServiceClient;
	private final DeviceServiceClient deviceServiceClient;
	private final SensorServiceClient sensorServiceClient;
	private final ActuationInterfaceServiceClient actuationInterfaceServiceClient;
	private final AliasNamespaceServiceClient namespaceServiceClient;
	private final CollectionServiceClient collectionServiceClient;
	private final SystemServiceClient systemServiceClient;
	private final RecIndividualsServiceClient recIndividualsServiceClient;

	@Autowired
	public JavaSdkExampleApplication(
			PropertyOwnerServiceClient propertyOwnerServiceClient,
			RealEstateServiceClient realEstateServiceClient,
			RealEstateComponentClient realEstateComponentClient,
			BuildingComponentServiceClient buildingComponentServiceClient,
			AssetServiceClient assetServiceClient,
			DeviceServiceClient deviceServiceClient,
			SensorServiceClient sensorServiceClient,
			ActuationInterfaceServiceClient actuationInterfaceServiceClient,
			AliasNamespaceServiceClient namespaceServiceClient,
			CollectionServiceClient collectionServiceClient,
			SystemServiceClient systemServiceClient,
			RecIndividualsServiceClient recIndividualsServiceClient) {
		this.propertyOwnerServiceClient = propertyOwnerServiceClient;
		this.realEstateServiceClient = realEstateServiceClient;
		this.buildingServiceClient = realEstateComponentClient;
		this.buildingComponentServiceClient = buildingComponentServiceClient;
		this.assetServiceClient = assetServiceClient;
		this.deviceServiceClient = deviceServiceClient;
		this.sensorServiceClient = sensorServiceClient;
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
			printJson(propertyOwnerServiceClient.getAxiomById());

			log.info("Return RealEstate: ");
			printJson(realEstateServiceClient.getAxiomById());

			log.info("Return RealEstateComponent: ");
			printJson(buildingServiceClient.getAxiomById());

			log.info("Return BuildingComponent: ");
			printJson(buildingComponentServiceClient.getAxiomById());

			log.info("Return Asset: ");
			printJson(assetServiceClient.getAxiomById());

			log.info("Return Device: ");
			printJson(deviceServiceClient.getAxiomById());

			log.info("Return Sensor: ");
			printJson(sensorServiceClient.getAxiomById());

			log.info("Return ActuationInterface: ");
			printJson(actuationInterfaceServiceClient.getAxiomById());

			log.info("Return AliasNamespace: ");
			printJson(namespaceServiceClient.getAxiomById());

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
			log.info("-------------- ALL DONE ----------------");
		};
	}

	private void printJson(Object object) throws JsonProcessingException {
		log.info("\n" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
	}

}
