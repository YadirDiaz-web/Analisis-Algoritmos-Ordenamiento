/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InsertionSortYadir;

/**
 *
 * @author yadir
 */
public class BubbleSort {

    // Este método ordena un arreglo utilizando el algoritmo Bubble Sort (Burbuja).
    public static void ordenar(int[] arreglo) {

        int n = arreglo.length;
        boolean intercambiado;

        // Recorro todo el arreglo en múltiples pasadas.
        for (int i = 0; i < n - 1; i++) {

            intercambiado = false;

            // Comparo elementos adyacentes y empujo el mayor hacia el final.
            for (int j = 0; j < n - i - 1; j++) {

                if (arreglo[j] > arreglo[j + 1]) {
                    // Intercambio los elementos.
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j + 1];
                    arreglo[j + 1] = temp;
                    intercambiado = true;
                }
            }

            // Si no hubo intercambios en la pasada, el arreglo ya está ordenado.
            if (!intercambiado) {
                break;
            }
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
                + "Estudiado desde 1956 por Edward Harry Friend bajo el nombre de\n"
                + "'ordenamiento por intercambio'. Recibe su nombre porque los valores\n"
                + "más grandes 'flotan' como burbujas hacia el final del arreglo.\n\n"
                + "=== CARACTERÍSTICAS ===\n"
                + "- Algoritmo estable e in-place.\n"
                + "- Muy fácil de implementar y entender didácticamente.\n"
                + "- Poco eficiente en arreglos grandes debido a la gran cantidad de intercambios.\n\n"
                + "=== PSEUDOCÓDIGO ===\n"
                + "PARA i <- 0 HASTA n - 2 HACER\n"
                + "    intercambiado <- FALSO\n"
                + "    PARA j <- 0 HASTA n - i - 2 HACER\n"
                + "        SI A[j] > A[j + 1] ENTONCES\n"
                + "            intercambiar(A[j], A[j + 1])\n"
                + "            intercambiado <- VERDADERO\n"
                + "        FIN SI\n"
                + "    FIN PARA\n"
                + "    SI NO intercambiado ENTONCES SALIR\n"
                + "FIN PARA\n\n"
                + "=== RENDIMIENTO TEÓRICO O() ===\n"
                + "- Mejor caso (ya ordenado): O(n)\n"
                + "- Caso promedio (aleatorio): O(n²)\n"
                + "- Peor caso (orden inverso): O(n²)\n"
                + "- Complejidad espacial: O(1)\n";
    }
}
