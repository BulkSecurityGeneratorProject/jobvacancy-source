Feature: Como oferente quiero saber la cantidad de postulaciones de cada una de mis ofertas

  Scenario: Un usuario ve la cantidad de postulaciones en el listado de ofertas
    Given el usuario oferente esta logueado en la aplicación 
    And tiene una oferta creada
    When entra en My Offers
    Then ve la cantidad de postulaciones a sus ofertas

  Scenario: Un usuario ve la cantidad de postulaciones de una oferta en particular
    Given el usuario oferente esta logueado en la aplicación 
    And tiene una oferta creada
    When entra a la información de la oferta
    Then ve la cantidad de postulaciones a esa oferta