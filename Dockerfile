#Usar una imagen base ligera de OpenJDK para ejecutar aplicaciones Java
FROM openjdk:17-jdk-slim

# Define la variable del archivo JAR
ARG JAR_FILE=target/optilife-api-0.0.1.jar

# Copia el archivo JAR en el contenedor
COPY ${JAR_FILE} optilife-api.jar

# Expone el puerto 8080
EXPOSE 8080

# Comando para ejecutar el archivo JAR
ENTRYPOINT ["java","-jar","optilife-api.jar"]