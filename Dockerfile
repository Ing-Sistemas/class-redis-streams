FROM gradle:8.7.0-jdk17-jammy AS build
COPY . /app
WORKDIR /app
RUN ./gradlew :demo:bootJar

FROM eclipse-temurin:17-jre-jammy
EXPOSE 8080
RUN mkdir /app
COPY --from=build /app/demo/build/libs/demo.jar /app/demo.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=production","/app/demo.jar"]