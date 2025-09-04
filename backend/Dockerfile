# Etapa 1: Build del JAR con Maven
FROM maven:3.9.5-eclipse-temurin-17 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar los archivos de Maven (pom.xml primero para cachear dependencias)
COPY backend/pom.xml .

# Descargar dependencias (para cachear)
RUN mvn dependency:go-offline -B

# Copiar el resto del código fuente
COPY backend/src ./src

# Compilar el proyecto y generar el JAR
RUN mvn clean package -DskipTests

# Etapa 2: Crear la imagen final
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copiar el JAR desde la etapa de build
COPY --from=build /app/target/*.jar app.jar

# Exponer el puerto (usado por Spring Boot)
ENV PORT=8080
EXPOSE 8080

# Comando para correr la app
ENTRYPOINT ["java","-jar","app.jar"]
