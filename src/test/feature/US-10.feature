Feature: Como candidato quiero poder adjuntar mi CV al postularme

  Scenario: El usuario envia un mail con un CV valido
    Given el usuario esta logueado a la aplicación
    And selecciona una oferta
    And ingresa nombre, email y carga el cv
    When aplica al anuncio
    Then se le notifica que los datos se subieron correctamente.

  Scenario: El usuario no ingresa un CV
    Given el usuario esta logueado a la aplicación
    And selecciona una oferta
    And ingresa nombre y email
    When aplica al anuncio
    Then se le notifica que el archivo del cv es requerido.
