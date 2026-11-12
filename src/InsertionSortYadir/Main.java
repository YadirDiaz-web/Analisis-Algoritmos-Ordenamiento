/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InsertionSortYadir;

/**
 *
 * @author yadir
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) {

        // Defino los tamaños de entrada que voy a probar.
        int[] cantidades = {
            100, 500, 1000, 5000,
            10000, 15000, 20000,
            25000, 30000
        };

        // Listas para almacenar tamaños y tiempos de cada algoritmo.
        List<Integer> tamanios = new ArrayList<>();
        List<Long> tiemposInsertion = new ArrayList<>();
        List<Long> tiemposBubble = new ArrayList<>();
        List<Long> tiemposQuick = new ArrayList<>();

        // Variables para verificar que todos los arreglos queden bien ordenados.
        boolean calidadInsertion = true;
        boolean calidadBubble = true;
        boolean calidadQuick = true;

        System.out.println("N\tInsertion(ns)\tBubble(ns)\tQuick(ns)\tCalidad");

        for (int cantidad : cantidades) {

            // Genero el arreglo base y creo copias idénticas para cada algoritmo.
            int[] datosBase = generarDatos(cantidad);
            int[] datosInsertion = datosBase.clone();
            int[] datosBubble = datosBase.clone();
            int[] datosQuick = datosBase.clone();

            // 1. Mido Insertion Sort (Algoritmo propio).
            long inicio = System.nanoTime();
            InsertionSort.ordenar(datosInsertion);
            long tiempoIns = System.nanoTime() - inicio;
            if (!InsertionSort.verificarCalidad(datosInsertion)) {
                calidadInsertion = false;
            }

            // 2. Mido Bubble Sort.
            inicio = System.nanoTime();
            BubbleSort.ordenar(datosBubble);
            long tiempoBub = System.nanoTime() - inicio;
            if (!BubbleSort.verificarCalidad(datosBubble)) {
                calidadBubble = false;
            }

            // 3. Mido Quick Sort.
            inicio = System.nanoTime();
            QuickSort.ordenar(datosQuick);
            long tiempoQui = System.nanoTime() - inicio;
            if (!QuickSort.verificarCalidad(datosQuick)) {
                calidadQuick = false;
            }

            // Guardo todos los resultados.
            tamanios.add(cantidad);
            tiemposInsertion.add(tiempoIns);
            tiemposBubble.add(tiempoBub);
            tiemposQuick.add(tiempoQui);

            boolean todoOk = calidadInsertion && calidadBubble && calidadQuick;
            System.out.println(cantidad + "\t" + tiempoIns + "\t\t" + tiempoBub
                    + "\t\t" + tiempoQui + "\t\t" + (todoOk ? "OK" : "ERROR"));
        }

        // Abro la interfaz gráfica con las 4 pestañas de análisis y comparación.
        Graficador.mostrarDashboardCompleto(
                tamanios,
                tiemposInsertion, calidadInsertion,
                tiemposBubble, calidadBubble,
                tiemposQuick, calidadQuick);
    }

    // Este método genera un arreglo con números aleatorios.
    public static int[] generarDatos(int tamaño) {
        Random random = new Random();
        int[] arreglo = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = random.nextInt(100000);
        }
        return arreglo;
    }
}
