# Petstore sample

Este ejemplo muestra la implementación de pruebas unitarias y de integración de una API Restful basado en Spring Boot.

Esta aplicación utiliza [Cucumber](https://cucumber.io/)  y [Rest Assured](https://rest-assured.io/) para la pruebas de API Rest.

## Requerimientos previos

Antes de comenzar, asegúrese de cumplir con los siguientes requisitos:

- Java 21 o superior está instalado.
- Maven está instalado (o use el wrapper maven).
- Su IDE favorito (Eclipse, IntelliJ, etc.) está configurado.

## Empezando

### Instalación y compilación

Para obtener este proyecto en funcionamiento en su entorno local, siga estos pasos:

1. Una vez que haya clonado el repositorio, navegue al directorio del proyecto:

```bash
cd payment-methods-service
```

2. Compilar el proyecto:

Si tiene maven instalado en su máquina local, puede utilizar el siguiente comando:

```bash
mvn clean compile test
```

De lo contrario, puede utilizar el wrapper de maven. Para este caso, asegúrese de establecer la variable de entorno `JAVA_HOME` apuntando al directorio de instalación de JDK 21.

```bash
sh ./mvnw clean compile test
```
### Ejecución del proyecto

1. Utilice el siguiente comando para ejecutar el proyecto:

```bash
mvn clean spring-boot:run
```

Esto iniciará el proyecto en el puerto `8080` con el perfil `local` habilitado, en el ejemplo anterior estamos utilizando H2 como base de datos.

2. Acceda a la interfaz de usuario de Swagger navegando a [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html).

## Pruebas

### Pruebas unitarias

Para ejecutar las pruebas unitarias, puede utilizar el siguiente comando:


```bash
mvn clean test
```

### Pruebas de integración

Antes de ejecutar las pruebas de integración, asegúrese de que el proyecto esté ejecutándose en su máquina local con el perfil `test` habilitado.

> Puede revisar la sección **Ejecución del proyecto** para ver cómo ejecutar el proyecto.

```bash
mvn clean verify
```


