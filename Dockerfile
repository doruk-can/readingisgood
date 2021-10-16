FROM adoptopenjdk/openjdk11:alpine-jre

ADD ./target/readingisgood-0.0.1-SNAPSHOT.jar readingisgood.jar

ENTRYPOINT ["java", "-jar", "/readingisgood.jar"]


