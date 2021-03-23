package com.proptechos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proptechos.clients.ActuationInterfaceServiceClient;
import com.proptechos.clients.AliasNamespaceServiceClient;
import com.proptechos.clients.AssetServiceClient;
import com.proptechos.clients.BuildingComponentServiceClient;
import com.proptechos.clients.BuildingServiceClient;
import com.proptechos.clients.CollectionServiceClient;
import com.proptechos.clients.DeviceServiceClient;
import com.proptechos.clients.PropertyOwnerServiceClient;
import com.proptechos.clients.RealEstateServiceClient;
import com.proptechos.clients.RecIndividualsServiceClient;
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
	private final BuildingServiceClient buildingServiceClient;
	private final BuildingComponentServiceClient buildingComponentServiceClient;
	private final AssetServiceClient assetServiceClient;
	private final DeviceServiceClient deviceServiceClient;
	private final ActuationInterfaceServiceClient actuationInterfaceServiceClient;
	private final AliasNamespaceServiceClient namespaceServiceClient;
	private final CollectionServiceClient collectionServiceClient;
	private final RecIndividualsServiceClient recIndividualsServiceClient;

	@Autowired
	public JavaSdkExampleApplication(
			PropertyOwnerServiceClient propertyOwnerServiceClient,
			RealEstateServiceClient realEstateServiceClient,
			BuildingServiceClient buildingServiceClient,
			BuildingComponentServiceClient buildingComponentServiceClient,
			AssetServiceClient assetServiceClient,
			DeviceServiceClient deviceServiceClient,
			ActuationInterfaceServiceClient actuationInterfaceServiceClient,
			AliasNamespaceServiceClient namespaceServiceClient,
			CollectionServiceClient collectionServiceClient,
			RecIndividualsServiceClient recIndividualsServiceClient) {
		this.propertyOwnerServiceClient = propertyOwnerServiceClient;
		this.realEstateServiceClient = realEstateServiceClient;
		this.buildingServiceClient = buildingServiceClient;
		this.buildingComponentServiceClient = buildingComponentServiceClient;
		this.assetServiceClient = assetServiceClient;
		this.deviceServiceClient = deviceServiceClient;
		this.actuationInterfaceServiceClient = actuationInterfaceServiceClient;
		this.namespaceServiceClient = namespaceServiceClient;
		this.collectionServiceClient = collectionServiceClient;
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

			log.info("Return Building: ");
			printJson(buildingServiceClient.getAxiomById());

			log.info("Return BuildingComponent: ");
			printJson(buildingComponentServiceClient.getAxiomById());

			log.info("Return Asset: ");
			printJson(assetServiceClient.getAxiomById());

			log.info("Return Device: ");
			printJson(deviceServiceClient.getAxiomById());

			log.info("Return ActuationInterface: ");
			printJson(actuationInterfaceServiceClient.getAxiomById());

			log.info("Return AliasNamespace: ");
			printJson(namespaceServiceClient.getAxiomById());

			log.info("Return Collection: ");
			printJson(collectionServiceClient.getAxiomById());

			log.info("Return axioms included in Collection: ");
			printJson(collectionServiceClient.listFirstTenIncludedAxioms());

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
