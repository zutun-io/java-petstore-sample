# language: es
Característica: Registro de mascotas

  Escenario: Registrar un nuevo gato
    Dado que se tienen los siguientes headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    Cuando Se registra una nueva mascota
    """
    {
      "name": "Misty",
      "species": "CAT",
      "breed": "Persian",
      "color": "Gray",
      "age": 3
    }
    """
    Entonces El código de respuesta debe ser 201
    Y El response debe contener un campo 'id' con formato UUID

  Escenario: Registrar un nuevo perro
    Dado que se tienen los siguientes headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    Cuando Se registra una nueva mascota
    """
    {
      "name": "Buddy",
      "species": "DOG",
      "breed": "Golden Retriever",
      "color": "Golden",
      "age": 5
    }
    """
    Entonces El código de respuesta debe ser 201
    Y El response debe contener un campo 'id' con formato UUID

  Escenario: Registrar un gato con un nombre ya existente
    Dado que se tienen los siguientes headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    Cuando Se registra una nueva mascota
    """
    {
      "name": "Misty",
      "species": "CAT",
      "breed": "Siamese",
      "color": "Cream",
      "age": 2
    }
    """
    Entonces El código de respuesta debe ser 409

  Escenario: Registrar un nuevo perro solo con los campos requeridos
    Dado que se tienen los siguientes headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    Cuando Se registra una nueva mascota
    """
    {
      "name": "Rex",
      "species": "DOG",
      "breed": "Beagle",
      "age": 4
    }
    """
    Entonces El código de respuesta debe ser 201
    Y El response debe contener un campo 'id' con formato UUID

  Escenario: Registrar un gato con todos los campos (requeridos y opcionales)
    Dado que se tienen los siguientes headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    Cuando Se registra una nueva mascota
    """
    {
      "name": "Whiskers",
      "species": "CAT",
      "breed": "Bengal",
      "color": "Brown",
      "age": 1
    }
    """
    Entonces El código de respuesta debe ser 201
    Y El response debe contener un campo 'id' con formato UUID

  Escenario: Registrar un perro con campos insuficientes
    Dado que se tienen los siguientes headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    Cuando Se registra una nueva mascota
    """
    {
      "name": "Rover",
      "species": "DOG"
    }
    """
    Entonces El código de respuesta debe ser 400

  Escenario: Registrar una nueva mascota de una especie no válida
    Dado que se tienen los siguientes headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    Cuando Se registra una nueva mascota
    """
    {
      "name": "Unknown",
      "species": "LION",
      "breed": "African",
      "age": 4
    }
    """
    Entonces El código de respuesta debe ser 400

  Escenario: Registrar una nueva mascota con un nombre muy largo
    Dado que se tienen los siguientes headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    Cuando Se registra una nueva mascota
    """
    {
      "name": "ThisIsAVeryLongNameThatExceedsTheAllowedLengthForTestingPurposes very very very long ...hisIsAVeryLongNameThatExceedsTheAllowedLengthForTestingPurposes very very very long ...",
      "species": "CAT",
      "breed": "Persian",
      "age": 2
    }
    """
    Entonces El código de respuesta debe ser 400

  Escenario: Registrar una nueva mascota con una edad negativa
    Dado que se tienen los siguientes headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    Cuando Se registra una nueva mascota
    """
    {
      "name": "Tiny",
      "species": "CAT",
      "breed": "Sphinx",
      "age": -1
    }
    """
    Entonces El código de respuesta debe ser 400