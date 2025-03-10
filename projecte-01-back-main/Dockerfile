

# Create a stage for resolving and downloading dependencies.
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /app

# Copy the mvnw wrapper with executable permissions.
COPY . .

# Download dependencies as a separate step to take advantage of Docker's caching.
# Leverage a cache mount to /root/.m2 so that subsequent builds don't have to
# re-download packages.
RUN ./mvnw clean package -DskipTests

################################################################################

# Create a stage for building the application based on the stage with downloaded dependencies.
# This Dockerfile is optimized for Java applications that output an uber jar, which includes
# all the dependencies needed to run your app inside a JVM. If your app doesn't output an uber
# jar and instead relies on an application server like Apache Tomcat, you'll need to update this
# stage with the correct filename of your package and update the base image of the "final" stage
# use the relevant app server, e.g., using tomcat (https://hub.docker.com/_/tomcat/) as a base image.
FROM eclipse-temurin:23-jre

WORKDIR /app

COPY --from=builder /app/target/app.jar target/app.jar

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]
