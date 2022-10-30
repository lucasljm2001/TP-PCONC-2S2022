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
