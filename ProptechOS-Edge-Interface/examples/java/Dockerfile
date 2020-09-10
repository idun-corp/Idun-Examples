FROM openjdk:11

COPY target/proptechos-rec-connector-*-dependencies.jar /proptechos-rec-connector.jar

CMD ["java", "-XshowSettings:vm", "-jar", "proptechos-rec-connector.jar", "/config/device_config.json"]
