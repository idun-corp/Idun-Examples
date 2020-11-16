package com.proptechos;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proptechos.clients.ActuationInterfaceServiceClient;
import com.proptechos.clients.AliasNamespaceServiceClient;
import com.proptechos.clients.BuildingComponentServiceClient;
import com.proptechos.clients.BuildingServiceClient;
import com.proptechos.clients.DeviceServiceClient;
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

	private final RealEstateServiceClient realEstateServiceClient;
	private final BuildingServiceClient buildingServiceClient;
	private final BuildingComponentServiceClient buildingComponentServiceClient;
	private final DeviceServiceClient deviceServiceClient;
	private final ActuationInterfaceServiceClient actuationInterfaceServiceClient;
	private final AliasNamespaceServiceClient namespaceServiceClient;
	private final RecIndividualsServiceClient recIndividualsServiceClient;

	@Autowired
	public JavaSdkExampleApplication(
			RealEstateServiceClient realEstateServiceClient,
			BuildingServiceClient buildingServiceClient,
			BuildingComponentServiceClient buildingComponentServiceClient,
			DeviceServiceClient deviceServiceClient,
			ActuationInterfaceServiceClient actuationInterfaceServiceClient,
			AliasNamespaceServiceClient namespaceServiceClient,
			RecIndividualsServiceClient recIndividualsServiceClient) {
		this.realEstateServiceClient = realEstateServiceClient;
		this.buildingServiceClient = buildingServiceClient;
		this.buildingComponentServiceClient = buildingComponentServiceClient;
		this.deviceServiceClient = deviceServiceClient;
		this.actuationInterfaceServiceClient = actuationInterfaceServiceClient;
		this.namespaceServiceClient = namespaceServiceClient;
		this.recIndividualsServiceClient = recIndividualsServiceClient;
	}

	public static void main(String[] args) {
		SpringApplication.run(JavaSdkExampleApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			log.info("Return RealEstate: ");
			printJson(realEstateServiceClient.getAxiomById());

			log.info("Return Building: ");
			printJson(buildingServiceClient.getAxiomById());

			log.info("Return BuildingComponent: ");
			printJson(buildingComponentServiceClient.getAxiomById());

			log.info("Return Device: ");
			printJson(deviceServiceClient.getAxiomById());

			log.info("Return ActuationInterface: ");
			printJson(actuationInterfaceServiceClient.getAxiomById());

			log.info("Return AliasNamespace: ");
			printJson(namespaceServiceClient.getAxiomById());

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
