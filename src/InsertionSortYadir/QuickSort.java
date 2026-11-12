/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InsertionSortYadir;

/**
 *
 * @author yadir
 */
public class QuickSort {

    // Método público principal que inicia el ordenamiento Quick Sort.
    public static void ordenar(int[] arreglo) {
        if (arreglo == null || arreglo.length == 0) {
            return;
        }
        quickSort(arreglo, 0, arreglo.length - 1);
    }

    // Método recursivo que divide el arreglo usando un pivote.
    private static void quickSort(int[] arreglo, int bajo, int alto) {
        if (bajo < alto) {
            // Obtengo el índice donde quedó ubicado el pivote.
            int indicePivote = particionar(arreglo, bajo, alto);

            // Ordeno recursivamente la mitad izquierda y la mitad derecha.
            quickSort(arreglo, bajo, indicePivote - 1);
            quickSort(arreglo, indicePivote + 1, alto);
        }
    }

    // Este método organiza los menores a la izquierda del pivote y los mayores a la derecha.
    private static int particionar(int[] arreglo, int bajo, int alto) {
        int pivote = arreglo[alto];
        int i = bajo - 1;

        for (int j = bajo; j < alto; j++) {
            if (arreglo[j] <= pivote) {
                i++;
                int temp = arreglo[i];
                arreglo[i] = arreglo[j];
                arreglo[j] = temp;
            }
        }

        int temp = arreglo[i + 1];
        arreglo[i + 1] = arreglo[alto];
        arreglo[alto] = temp;

        return i + 1;
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
                + "Desarrollado en 1959 por el científico británico Tony Hoare mientras\n"
                + "trabajaba en traducción automática. Es uno de los algoritmos más\n"
                + "usados en bibliotecas estándar de programación.\n\n"
                + "=== CARACTERÍSTICAS ===\n"
                + "- Utiliza la estrategia 'Divide y Vencerás' mediante un elemento pivote.\n"
                + "- Ordenamiento in-place, aunque no es estable.\n"
                + "- Mucho más rápido en la práctica que los algoritmos cuadráticos.\n\n"
                + "=== PSEUDOCÓDIGO ===\n"
                + "FUNCION quickSort(A, bajo, alto)\n"
                + "    SI bajo < alto ENTONCES\n"
                + "        pivote <- particionar(A, bajo, alto)\n"
                + "        quickSort(A, bajo, pivote - 1)\n"
                + "        quickSort(A, pivote + 1, alto)\n"
                + "    FIN SI\n"
                + "FIN FUNCION\n\n"
                + "=== RENDIMIENTO TEÓRICO O() ===\n"
                + "- Mejor caso: O(n log n)\n"
                + "- Caso promedio: O(n log n)\n"
                + "- Peor caso (pivote desbalanceado): O(n²)\n"
                + "- Complejidad espacial: O(log n) por la pila recursiva\n";
    }
}
