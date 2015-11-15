Feature: Como oferente quiero definir una fecha de validez de cada oferta

  Scenario: El usuario crea una oferta con una fecha valida
    Given el usuario esta logueado a la aplicación
    And selecciona crear una oferta
    And ingresa título, lugar, descripción
    And ingresa fecha valida
    When aplica la oferta
    Then se informa al usuario que la oferta fue creada correctamente

  Scenario: El usuario crea una oferta con una fecha invalida
    Given el usuario esta logueado a la aplicación
    And selecciona crear una oferta
    And ingresa título, lugar, descripción
    And ingresa fecha invalida
    When aplica la oferta
    Then se informa al usuario que la fecha tiene un formato incorrecto

  Scenario: El usuario crea una oferta sin fecha
    Given el usuario esta logueado a la aplicación
    And selecciona crear una oferta
    And ingresa título, lugar, descripción
    And no ingresa fecha
    When aplica la oferta
    Then se informa al usuario que el campo fecha debe estar completo
