FROM openjdk:17-jdk
EXPOSE 8081
WORKDIR ./
COPY . .
RUN ./gradlew build

ADD /build/libs/hackathon-3-0.0.1-SNAPSHOT.jar hackathon_server.jar
ENTRYPOINT ["java", "-jar", "hackathon_server.jar"]