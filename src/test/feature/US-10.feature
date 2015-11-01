Feature: Como candidato quiero poder adjuntar mi CV al postularme

Scenario: El usuario envia un mail con un CV valido
  Given un usuario quiere postularse
  When sube un CV con tamaño menor a 1MB
  And completa nombre y email validos
  Then se le notifica que los datos se subieron correctamente.
 
Scenario: El usuario ingresa un CV invalido
  Given un usuario quiere postularse
  When sube un CV con tamaño mayor a 1MB
  And completa nombre y email validos
  Then se le notifica que el CV no puede pesar mas de 1MB.
 
Scenario: El usuario no ingresa un CV
  Given un usuario quiere postularse
  When no carga un cv
  Then se le notifica que se requiere dicho campo.
