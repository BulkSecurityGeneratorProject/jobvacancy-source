Feature: Como candidato quiero ver sólo las ofertas no vencidas

  Scenario: Se muestra la oferta con fecha posterior a hoy
    Given el usuario esta logueado a la aplicación
    And existe una oferta con fecha posterior a hoy
    When lista las ofertas
    Then debe ver la oferta

  Scenario: No se muestra la oferta con fecha anterior a hoy
    Given el usuario esta logueado a la aplicación
    And existe una oferta con fecha anterior a hoy
    When lista las ofertas
    Then no debe ver la oferta

  Scenario: Se muestra la oferta con fecha de hoy
    Given el usuario esta logueado a la aplicación
    And existe una oferta con fecha de hoy
    When lista las ofertas
    Then debe ver la oferta

  Scenario: Se muestran todas las ofertas en MyOffers
    Given el usuario esta logueado a la aplicación
    And existe una oferta con fecha de hoy
    And existe una oferta con fecha anterior a hoy
    And existe una oferta con fecha posterior a hoy
    When lista mis ofertas
    Then debe ver todas las ofertas
