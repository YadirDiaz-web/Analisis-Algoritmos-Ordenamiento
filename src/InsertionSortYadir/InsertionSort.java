/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */



package InsertionSortYadir;

/**
 *
 * @author yadir
 */
public class InsertionSort {

    // Este método ordena un arreglo de enteros utilizando el algoritmo Insertion Sort.
    public static void ordenar(int[] arreglo) {

        // Recorro el arreglo desde la segunda posición.
        for (int i = 1; i < arreglo.length; i++) {

            // Guardo el elemento actual que voy a insertar en la posición correcta.
            int clave = arreglo[i];

            // Obtengo la posición anterior al elemento actual.
            int j = i - 1;

            // Comparo la clave con los elementos anteriores.
            while (j >= 0 && arreglo[j] > clave) {

                // Desplazo el elemento una posición hacia la derecha.
                arreglo[j + 1] = arreglo[j];

                // Retrocedo una posición para seguir comparando.
                j--;
            }

            // Inserto la clave en la posición correcta.
            arreglo[j + 1] = clave;
        }
    }

    // Este método verifica la calidad comprobando que el arreglo esté ordenado.
    public static boolean verificarCalidad(int[] arreglo) {
        for (int i = 0; i < arreglo.length - 1; i++) {
            if (arreglo[i] > arreglo[i + 1]) {
                return false;
            }
        }
        return true;
    }

    // Este método devuelve toda la información didáctica del algoritmo.
    public static String obtenerInformacion() {
        return "=== HISTORIA ===\n"
                + "Fue mencionado por John Mauchly en 1945 en uno de los primeros trabajos\n"
                + "sobre computación. Se inspira en la forma natural en que las personas\n"
                + "ordenan una mano de cartas.\n\n"
                + "=== CARACTERÍSTICAS ===\n"
                + "- Algoritmo estable (mantiene el orden relativo de elementos iguales).\n"
                + "- Ordenamiento in-place (no requiere memoria adicional significativa).\n"
                + "- Muy eficiente para arreglos pequeños o que ya están casi ordenados.\n\n"
                + "=== PSEUDOCÓDIGO ===\n"
                + "PARA i <- 1 HASTA longitud(A) - 1 HACER\n"
                + "    clave <- A[i]\n"
                + "    j <- i - 1\n"
                + "    MIENTRAS j >= 0 Y A[j] > clave HACER\n"
                + "        A[j + 1] <- A[j]\n"
                + "        j <- j - 1\n"
                + "    FIN MIENTRAS\n"
                + "    A[j + 1] <- clave\n"
                + "FIN PARA\n\n"
                + "=== RENDIMIENTO TEÓRICO O() ===\n"
                + "- Mejor caso (ya ordenado): O(n)\n"
                + "- Caso promedio (aleatorio): O(n²)\n"
                + "- Peor caso (orden inverso): O(n²)\n"
                + "- Complejidad espacial: O(1)\n";
    }
}
