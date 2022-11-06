# TP-PCONC-2S2022

# Cosas para consultar:
 - Conversión final de la imágen nos da imágen en negro.
 - Alcanza con ver el tiempo en ms para saber si está trabajando concurrentemente? O necesitamos algún otro mecanismo? (logs u otros?)
 - Main y su thread "principal", como funcionan? Como hacemos que main termine? ***

# Cosas para hacer: (flujo a grandes rasgos)
 - Crear buffer con espacio dado por el parámetro.
 - Levantar el thread pool para la cantidad de workers dados por parámetro.
 - Los workers deberían ir al buffer a tratar de tomar rangos dentro de la matriz para convertirlos (inicialmente el buffer estará vacío, por lo que se irán a dormir).
 - Worker counter ***
 - 

# Notas: 
 - El worker deberá tomar una task (implementando la interfáz Runnable) para realizar el filtrado.

- Crear una clase task que a la que se le asigne un pedazo de matriz con su correspondiente posicion
En el main vamos haciendo la separacion de la matriz, a medida que la vamos separando se hace un launch de esa task (donde se escribe en el buffer la task)
task = [
     [[3,3,2],
      [0,0,1],
      [3,1,2]], 0, 0
]
buffer.write(task)

- El thread pool cuando se inicializa lanza los workers que van a estar leyendo el buffer, cada worker va a realizar el "convertir"que le corresponde
y va a guardar ese valor en una variable.
- Luego cuando el workerCounter termine la ejecucion, se lee de cada worker el valor guardado despues de convertir y se asigna la nueva matriz