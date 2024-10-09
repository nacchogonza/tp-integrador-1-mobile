# tp-integrador-1-mobile
Repositorio TP Grupal Integrado Nro 1 - Desarrollo de Apps Moviles IFTS18
TP realizado por Agustina Machaca e Ignacio Martín Gonzalez

## Información Técnica del Proyecto

- Uso de Shared Preferences en las siguientes pantallas:
  - Test del Inversor: para el almacenamiento del dato vinculado a si el usuario ya ingresó para no volver a mostrar el Test en caso de ya haberlo completado. También almacenamos el mensaje a mostrar en WelcomeActivity que contiene el nombre del usuario y el dato de tipo de inversor según el resultado del test
  - Pantalla de Comparación: para el almacenamiento de los datos de cada una de las inversiones y los resultados de los cálculos, que luego serán tomados por HistoryActivity para mostrar el historial de las ultimas 5 comparaciones
  - Pantalla de Historial: para obtener los datos de historial
 
- Pasaje de información por parámetros: pasamos los datos ingresados para cada inversión vía parámetros de una activity a otra, tomando estos datos en ComapratorCalcActivity para hacer uso de los mismos

- Uso de Toast: tanto en las pantallas de Test del Inversor como en ComparatorInputDataActivity se implementaron toasts para dar feedback al usuario sobre los campos faltantes

- Observaciones:  
  - Al poder manejar unicamente strings en el uso de Shared Preferences tuvimos que realizar una lógica bastante rebuscada para poder generar un string separado por ';' con los distintos datos de las distintas inversiones a utilizar en el historial. Este string luego se splitea y se convierte en un array para recorrer la lista y mostrar hasta 5 comparaciones segun lo solicitado en el enunciado
  - Utilizamos el archivo 'strings' para almacenar algunos textos de mayor longitud con el objetivo de ayudar a la legibilidad del codigo en los archivos xml
  - Para el historial se agregaron TextViews de manera 'programatica' para que se creen las Views necesarias segun la cantidad de comparaciones y sin dejar creados por default todos los campos previamente en el archivo xml
  - Según la necesidad en las distintas activitys se fueron creando métodos para utilizar en el contexto del activity y modularizar el código para hacerlo más legible
