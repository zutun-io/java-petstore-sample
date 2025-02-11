# language: es
Característica: API - Salud de la aplicación

  Escenario: Validar la salud de la aplicación
    Cuando Se verifica la salud de la aplicación
    Entonces El código de respuesta debe ser 200
    Y El campo 'status' de la respuesta debe ser 'UP'