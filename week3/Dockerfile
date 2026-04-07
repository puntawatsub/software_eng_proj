FROM maven:3.9.14-amazoncorretto-25-debian AS build

ENV DISPLAY=host.docker.internal:0.0

# Install only required libraries (NO MAVEN HERE)
RUN apt-get update && \
    apt-get install -y wget unzip libgtk-3-0 libgbm1 libx11-6 && \
    apt-get clean

ENV OPENJFX_URL=https://download2.gluonhq.com/openjfx/25.0.2/openjfx-25.0.2_linux-aarch64_bin-sdk.zip

# Download JavaFX SDK
RUN wget $OPENJFX_URL -O /tmp/openjfx.zip && \
    unzip /tmp/openjfx.zip -d /opt && \
    mv /opt/javafx-* /opt/openjfx && \
    rm /tmp/openjfx.zip

WORKDIR /app

# Copy project
COPY pom.xml .
COPY src ./src

# Package
RUN mvn clean package -DskipTests

# Debug
RUN ls -l target/

CMD ["java", "--module-path", "/opt/openjfx/lib", "--add-modules", "javafx.controls,javafx.fxml", "--enable-native-access=javafx.graphics", "-jar", "target/app.jar"]