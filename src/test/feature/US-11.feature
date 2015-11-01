Feature: Como administrador quiero saber la cantidad histórica total de ofertas publicadas

  Scenario: El usuario administrador se informa de la cantidad histórica total de ofertas publicadas
    Given el usuario esta logueado a la aplicación como administrador
    When cliquea en el botón information del menú administration
    Then se muestra la cantidad de ofertas creadas hostoricamente

  Scenario: El usuario administrador se informa de la cantidad actual de ofertas creadas
    Given el usuario esta logueado a la aplicación como administrador
    When cliquea en el botón information del menú administration
    Then se muestra la cantidad de ofertas creadas actualmente
