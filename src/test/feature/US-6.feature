Feature: Como oferente quiero poder generar una nueva oferta a partir de una ya existente

  Scenario: Un usuario crea una nueva oferta de trabajo a partir de otra ya existente
    Given el usuario oferente esta logueado en la aplicación And tiene una oferta creada
    When cliquea el botón copy de una oferta de trabajo existente And edita los campos
    And guarda la nueva oferta.
    Then se creo una nueva oferta.
