FROM openjdk:11

COPY target/proptechos-rec-connector-sallyr-*-dependencies.jar /proptechos-rec-connector.jar

CMD ["java", "-XshowSettings:vm", "-jar", "proptechos-rec-connector.jar", "/config/sallyR_device_config.json"]
