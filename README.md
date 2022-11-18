# TP-PCONC-2S2022

# Introduccion 
La consigna del trabajo pedía que desarrollaramos un programa que pudiera aplicar filtros de convolución a una imagen, para ello, se debía dividir la misma (a la que representamos como una matriz de n*m píxeles) y crear tareas que varios procesos concurrentes debían resolver. Esta tarea sería aplicar una transformación en el valor de la matriz de entrada (consiste en tomar los vértices vecinos a cada celda, multiplicarlos uno a uno por el valor en la celda correspondiente a la matriz de 3 x 3 que se usa de filtro, y sumarlos) y asignarlo como valor en las mismas coordenadas de una matriz salida, posteriormente, esa matriz se convierte en una imagen de salida.

# Inicio del programa
Para iniciar el programa, se debe ejecutar el archivo Main.java, y suministrar los datos:
- Ruta de archivo de entrada: debe ser una imagen en formato jpg o jpeg, y debe ser el path relativo al archivo Main.java
- Ruta de archivo de salida: debe ser el destino deseado para el archivo de salida, con la extension deseada
- Tamaño del buffer: se sugiere un numero entre 1 y 64
- Cantidad de Threads: se sugiere un numero entre 1 y 16
- Filtro a aplicar: los filtros posibles son: blur, sharpen, sobelVertical, sobelHorizontal y bordes

# Finalizacion del programa
Cuando la consola imprime los milisegundos demorados en procesar la imagen, la misma estara disponible, y el programa habra finalizado con exito
