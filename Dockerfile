FROM openjdk:8
VOLUME /tmp/user-service
ADD target/user-service-RELEASE.jar user-service.jar
ENTRYPOINT [ "java", "-jar", "user-service.jar" ]