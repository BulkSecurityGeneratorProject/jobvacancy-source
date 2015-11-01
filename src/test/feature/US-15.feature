Feature: Como candidato quiero asegurarme que al postularme debe poner un mail válido

  Scenario: El usuario se registrar con un mail valido
    Given el usuario esta logueado a la aplicación
    When ingresa un nombre
    And ingresa un email valido
    Then se informa al usuario que los datos fueron enviados correctamente

  Scenario: El usuario se intenta registrar con un mail invalido
    Given el usuario esta logueado a la aplicación
    When ingresa un nombre
    And ingresa un email invalido
    Then se informa al usuario que el mail ingresado es invalido

  Scenario: El usuario se intenta registrar sin mail
    Given el usuario esta logueado a la aplicación
    When ingresa un nombre
    And no ingresa email
    Then se informa al usuario que el mail es un dato obligatorio
