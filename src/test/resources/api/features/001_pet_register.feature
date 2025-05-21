# language: en
Feature: Pet Registration

  Scenario: Register a new cat
    Given the following headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    When a new pet is registered
    """
    {
      "name": "Misty",
      "species": "CAT",
      "breed": "Persian",
      "color": "Gray",
      "age": 3
    }
    """
    Then the response code should be 201
    And the response should contain a field 'id' with UUID format

  Scenario: Register a new dog
    Given the following headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    When a new pet is registered
    """
    {
      "name": "Buddy",
      "species": "DOG",
      "breed": "Golden Retriever",
      "color": "Golden",
      "age": 5
    }
    """
    Then the response code should be 201
    And the response should contain a field 'id' with UUID format

  Scenario: Register a cat with an existing name
    Given the following headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    When a new pet is registered
    """
    {
      "name": "Misty",
      "species": "CAT",
      "breed": "Siamese",
      "color": "Cream",
      "age": 2
    }
    """
    Then the response code should be 409

  Scenario: Register a new dog with only required fields
    Given the following headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    When a new pet is registered
    """
    {
      "name": "Rex",
      "species": "DOG",
      "breed": "Beagle",
      "age": 4
    }
    """
    Then the response code should be 201
    And the response should contain a field 'id' with UUID format

  Scenario: Register a cat with all fields (required and optional)
    Given the following headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    When a new pet is registered
    """
    {
      "name": "Whiskers",
      "species": "CAT",
      "breed": "Bengal",
      "color": "Brown",
      "age": 1
    }
    """
    Then the response code should be 201
    And the response should contain a field 'id' with UUID format

  Scenario: Register a dog with insufficient fields
    Given the following headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    When a new pet is registered
    """
    {
      "name": "Rover",
      "species": "DOG"
    }
    """
    Then the response code should be 400

  Scenario: Register a new pet of an invalid species
    Given the following headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    When a new pet is registered
    """
    {
      "name": "Unknown",
      "species": "LION",
      "breed": "African",
      "age": 4
    }
    """
    Then the response code should be 400

  Scenario: Register a new pet with a very long name
    Given the following headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    When a new pet is registered
    """
    {
      "name": "ThisIsAVeryLongNameThatExceedsTheAllowedLengthForTestingPurposes very very very long ...hisIsAVeryLongNameThatExceedsTheAllowedLengthForTestingPurposes very very very long ...",
      "species": "CAT",
      "breed": "Persian",
      "age": 2
    }
    """
    Then the response code should be 400

  Scenario: Register a new pet with a negative age
    Given the following headers
      | Header        | Value              |
      | Content-Type  | application/json   |
    When a new pet is registered
    """
    {
      "name": "Tiny",
      "species": "CAT",
      "breed": "Sphinx",
      "age": -1
    }
    """
    Then the response code should be 400
