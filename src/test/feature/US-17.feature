Feature: Como candidato quiero poder linkear mi CV al postularme

  Scenario: El usuario envia un mail con un link valido
    Given el usuario esta logueado a la aplicación
    And selecciona una oferta
    And ingresa nombre, mail y link valido
    When aplica al anuncio
    Then se informa al usuario que los datos fueron enviados correctamente

  Scenario: El usuario intenta enviar un mail con un link invalido
    Given el usuario esta logueado a la aplicación
    And selecciona una oferta
    And ingresa nombre, mail y link invalido
    When aplica al anuncio
    Then se informa al usuario que el link ingresado es invalido

  Scenario: El usuario intenta enviar un mail sin link
    Given el usuario esta logueado a la aplicación
    And selecciona una oferta
    And ingresa nombre y mail
    When aplica al anuncio
    Then se informa al usuario que el ingreso del link del CV es obligatorio
