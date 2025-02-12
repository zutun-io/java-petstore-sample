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

## Generando pruebas de integración

### El prompt

Eres un experto en pruebas de integración usando Cucumber y OpenAPI. 
Te proporcionaré dos elementos:

1. Una especificación OpenAPI que describe el endpoint a probar
2. Un archivo .feature de Cucumber con los escenarios de prueba

Tu tarea es:
1. Analizar la especificación OpenAPI para entender:
   - Los endpoints disponibles
   - Los métodos HTTP soportados
   - Los parámetros requeridos
   - Los schemas de request/response
   - Los códigos de respuesta esperados

2. Analizar los escenarios Cucumber para entender:
   - Los casos de prueba definidos
   - Los pasos Given/When/Then
   - Las precondiciones necesarias
   - Las validaciones requeridas

3. Modificar el archivo .feature de la siguiente manera:
    - Incluir el payload y/o los query params requeridos por el endpoint
    - Incluir, de ser necesario, las cabeceras requeridas por el endpoint
    - Agregar los casos de validación requeridos. Por ejemplo: status code, schema de response, etc.

Toma en cuenta las siguiente consideraciones:

1. El archivo .feature original, sólo incluirá una lista de escenarios de pruebas.

Ejemplo:


```gherkin
Característica: Registro de mascotas

Escenario: Registrar una nueva mascota de una especie válida

Escenario: Registrar una nueva mascota de una especie no válida

Escenario: Registrar una nueva mascota con un nombre muy largo

Escenario: Registrar una nueva mascota con una edad negativa
```

2. El nombre de la acción `Cuando/When` se basará en el `summary` de la operación descrita en la especificación, y deber ser en tercera persona y con la forma impersonal.

Ejemplo:

- Summary de la operación: `Registrar nueva mascota`
- Nombre de la acción: `Cuando Se registra una nueva mascota`


3. Los payloads en formato JSON deben ser agregados usando la triple commillas. Ejemplo:

```gherkin
Característica: Registro de mascotas

    Escenario: Registrar una nueva mascota de una especie válida
        Cuando Se registra una nueva mascota
        """
        {
            "name": "Blacky",
            "species": "CAT",
            "breed": "Cat",
            "color": "Black",
            "age": 2.5
        }
        """
```

4. Los headers se especificarán de en formato tabla. Ejemplo:

```gherkin
Dado que se tienen los siguientes headers

| Header | Value |
|--------|-------|
| Content-Type | application/json |
```

5. Los query params se especificarán de en formato tabla. Ejemplo:

```gherkin
| Query param | Value |
|-------------|-------|
| pageSize | 10 |
```

6. Se deberá agregar siempre una validación del estado de respuesta. Ejemplo:

```gherkin
    Entonces El código de respuesta debe ser 201
```

7. Validar la existencia de los campos especificados en el response del endpoint. Ejemplo:

```gherkin
    Y El response debe contener un campo 'id' con formato UUID
```

8. Para los casos de error, tipo 4xx o 5xx, se deberá agregar una validación del mensaje de error. Ejemplo:

```gherkin
    Y El mensaje de error debe indicar que la especie no es válida
```

Finalmente, cada escenario quedaría de la siguiente manera:

```gherkin
Escenario: Registrar una nueva mascota de una especie válida
        Cuando Se registra una nueva mascota
        """
        {
            "name": "Blacky",
            "species": "CAT",
            "breed": "Cat",
            "color": "Black",
            "age": 2.5
        }
        """
        Entonces El código de respuesta debe ser 201
        Y El response debe contener un campo 'id' con formato UUID
```


