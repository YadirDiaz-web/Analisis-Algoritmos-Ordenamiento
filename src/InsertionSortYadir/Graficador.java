/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InsertionSortYadir;

/**
 *
 * @author Yadir Diaz
 */
import java.awt.BasicStroke;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class Graficador {

    // Paleta de colores moderna
    private static final Color COLOR_PRIMARIO = new Color(30, 41, 59);
    private static final Color COLOR_FONDO = new Color(241, 245, 249);
    private static final Color COLOR_TARJETA = Color.WHITE;
    private static final Color COLOR_BORDE = new Color(203, 213, 225);
    private static final Color COLOR_TEXTO = new Color(15, 23, 42);
    private static final Color COLOR_SUBTEXTO = new Color(100, 116, 139);

    // Colores para las 3 curvas
    private static final Color COLOR_INSERTION = new Color(37, 99, 235);
    private static final Color COLOR_BUBBLE = new Color(225, 29, 72);
    private static final Color COLOR_QUICK = new Color(16, 185, 129);
    private static final Color COLOR_EXITO_BG = new Color(220, 252, 231);
    private static final Color COLOR_EXITO_TXT = new Color(22, 101, 52);

    // Estima automáticamente la complejidad Big O experimental.
    public static String identificarBigO(List<Long> tiempos, List<Integer> tamagnios) {
        long primerTiempo = tiempos.get(0);
        long ultimoTiempo = tiempos.get(tiempos.size() - 1);
        int primerN = tamagnios.get(0);
        int ultimoN = tamagnios.get(tamagnios.size() - 1);

        double factorTiempo = (double) ultimoTiempo / primerTiempo;
        double factorN = (double) ultimoN / primerN;
        double relacion = factorTiempo / factorN;

        if (relacion < 0.2) {
            return "O(log n)";
        }
        if (relacion < 2) {
            return "O(n)";
        }
        if (relacion < 15) {
            return "O(n log n)";
        }
        return "O(n²)";
    }

    // Estiliza cualquier gráfica JFreeChart.
    private static void estilizarGrafica(JFreeChart chart, Color[] coloresSeries) {
        chart.setBackgroundPaint(COLOR_TARJETA);
        chart.getTitle().setFont(new Font("Segoe UI", Font.BOLD, 16));
        chart.getTitle().setPaint(COLOR_TEXTO);

        if (chart.getLegend() != null) {
            chart.getLegend().setBackgroundPaint(COLOR_TARJETA);
            chart.getLegend().setItemFont(new Font("Segoe UI", Font.PLAIN, 12));
            chart.getLegend().setFrame(org.jfree.chart.block.BlockBorder.NONE);
        }

        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinesVisible(true);
        plot.setDomainGridlinePaint(new Color(226, 232, 240));
        plot.setRangeGridlinePaint(new Color(226, 232, 240));
        plot.setOutlineVisible(false);

        plot.getDomainAxis().setLabelFont(new Font("Segoe UI", Font.BOLD, 12));
        plot.getDomainAxis().setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));
        plot.getRangeAxis().setLabelFont(new Font("Segoe UI", Font.BOLD, 12));
        plot.getRangeAxis().setTickLabelFont(new Font("Segoe UI", Font.PLAIN, 11));

        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        for (int i = 0; i < coloresSeries.length; i++) {
            renderer.setSeriesPaint(i, coloresSeries[i]);
            renderer.setSeriesStroke(i, new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            renderer.setSeriesShapesVisible(i, true);
            renderer.setSeriesShapesFilled(i, true);
        }
        plot.setRenderer(renderer);
    }

    // Crea un botón moderno estilizado.
    private static JButton crearBotonEstilizado(String texto, Color fondo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setBackground(fondo);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setOpaque(true);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(new EmptyBorder(8, 16, 8, 16));
        return btn;
    }

    // Crea una tarjeta KPI con título y valor destacado.
    private static JPanel crearTarjetaKPI(String titulo, String valor, Color colorValor, Color fondoTarjeta) {
        JPanel card = new JPanel(new BorderLayout(0, 4));
        card.setBackground(fondoTarjeta);
        card.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(10, 14, 10, 14)
        ));

        JLabel lblTitulo = new JLabel(titulo.toUpperCase());
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 10));
        lblTitulo.setForeground(COLOR_SUBTEXTO);

        JLabel lblValor = new JLabel(valor);
        lblValor.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblValor.setForeground(colorValor);

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(lblValor, BorderLayout.CENTER);
        return card;
    }

    // Crea una sección estilizada para mostrar información teórica o pseudocódigo.
    private static JPanel crearSeccionTexto(String titulo, String contenido, boolean esCodigo) {
        JPanel panel = new JPanel(new BorderLayout(0, 6));
        panel.setBackground(COLOR_TARJETA);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(12, 14, 12, 14)
        ));
        panel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblTitulo.setForeground(COLOR_PRIMARIO);

        JTextArea txt = new JTextArea(contenido.trim());
        txt.setEditable(false);
        txt.setLineWrap(true);
        txt.setWrapStyleWord(true);
        if (esCodigo) {
            txt.setFont(new Font("Consolas", Font.PLAIN, 12));
            txt.setBackground(new Color(248, 250, 252));
            txt.setForeground(new Color(30, 41, 59));
            txt.setBorder(new EmptyBorder(8, 10, 8, 10));
        } else {
            txt.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            txt.setBackground(COLOR_TARJETA);
            txt.setForeground(COLOR_TEXTO);
        }

        panel.add(lblTitulo, BorderLayout.NORTH);
        panel.add(txt, BorderLayout.CENTER);
        return panel;
    }

    // Estiliza una tabla JTable moderna garantizando contraste en Windows.
    private static JScrollPane crearTablaEstilizada(DefaultTableModel modelo) {
        JTable tabla = new JTable(modelo);
        tabla.setRowHeight(28);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tabla.setGridColor(new Color(226, 232, 240));
        tabla.setShowVerticalLines(false);
        tabla.setIntercellSpacing(new Dimension(0, 1));
        tabla.setEnabled(false);

        JTableHeader header = tabla.getTableHeader();
        header.setPreferredSize(new Dimension(0, 34));
        header.setReorderingAllowed(false);

        // Renderizador personalizado para forzar el fondo oscuro y texto blanco en Windows
        DefaultTableCellRenderer headerRenderer = new DefaultTableCellRenderer();
        headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        headerRenderer.setBackground(COLOR_PRIMARIO);
        headerRenderer.setForeground(Color.WHITE);
        headerRenderer.setFont(new Font("Segoe UI", Font.BOLD, 12));
        headerRenderer.setOpaque(true);

        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setHeaderRenderer(headerRenderer);
        }

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tabla.getColumnCount(); i++) {
            tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(new LineBorder(COLOR_BORDE, 1, true));
        scroll.getViewport().setBackground(Color.WHITE);
        return scroll;
    }

    // Construye el panel individual ANIMADO de cada algoritmo.

    public static JPanel crearPanelIndividual(
            List<Long> tiempos,
            List<Integer> tamagnos,
            String nombreFuncion,
            String infoDidactica,
            boolean calidadVerificada,
            Color colorPrincipal) {

        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        long max = 0;
        long suma = 0;
        for (int i = 0; i < tiempos.size(); i++) {
            long t = tiempos.get(i);
            if (t > max) {
                max = t;
            }
            suma += t;
            dataset.addValue(null, nombreFuncion, String.valueOf(tamagnos.get(i)));
        }
        double promedio = (double) suma / tiempos.size();
        String complejidad = identificarBigO(tiempos, tamagnos);

        JPanel panelIzquierdo = new JPanel(new BorderLayout(12, 12));
        panelIzquierdo.setBackground(COLOR_FONDO);
        panelIzquierdo.setBorder(new EmptyBorder(15, 15, 15, 10));

        JPanel panelKPIs = new JPanel(new GridLayout(1, 4, 10, 0));
        panelKPIs.setBackground(COLOR_FONDO);

        panelKPIs.add(crearTarjetaKPI(
                "Calidad de Orden",
                calidadVerificada ? "✔ APROBADO" : "✖ ERROR",
                calidadVerificada ? COLOR_EXITO_TXT : Color.RED,
                calidadVerificada ? COLOR_EXITO_BG : new Color(254, 226, 226)
        ));
        panelKPIs.add(crearTarjetaKPI("Complejidad Big-O", complejidad, colorPrincipal, COLOR_TARJETA));
        panelKPIs.add(crearTarjetaKPI("Tiempo Promedio", nf.format((long) promedio) + " ns", COLOR_TEXTO, COLOR_TARJETA));
        panelKPIs.add(crearTarjetaKPI("Tiempo Máximo", nf.format(max) + " ns", COLOR_TEXTO, COLOR_TARJETA));

        JFreeChart chart = ChartFactory.createLineChart(
                "Curva de Rendimiento: " + nombreFuncion,
                "Tamaño de Entrada (N)",
                "Tiempo (Nanosegundos)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        estilizarGrafica(chart, new Color[]{colorPrincipal});

        // Fijamos el eje Y para que la animación sea fluida sin saltos de escala.
        chart.getCategoryPlot().getRangeAxis().setRange(0, max * 1.08);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
        chartPanel.setBackground(COLOR_TARJETA);

        // Barra inferior de controles para animar la gráfica individual.
        JPanel barraControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        barraControles.setBackground(COLOR_TARJETA);
        barraControles.setBorder(new LineBorder(COLOR_BORDE, 1, true));

        JButton btnPlay = crearBotonEstilizado("▶  Reproducir Avance", new Color(22, 163, 74));
        JButton btnReiniciar = crearBotonEstilizado("↺  Reiniciar", new Color(71, 85, 105));
        JButton btnVerTodo = crearBotonEstilizado("⏭  Mostrar Todo", colorPrincipal);
        JLabel lblEstado = new JLabel("Presiona Play para animar (0 / " + tamagnos.size() + ")");
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblEstado.setForeground(COLOR_TEXTO);

        barraControles.add(btnPlay);
        barraControles.add(btnReiniciar);
        barraControles.add(btnVerTodo);
        barraControles.add(lblEstado);

        JPanel centroGrafica = new JPanel(new BorderLayout(0, 10));
        centroGrafica.setBackground(COLOR_FONDO);
        centroGrafica.add(chartPanel, BorderLayout.CENTER);
        centroGrafica.add(barraControles, BorderLayout.SOUTH);

        panelIzquierdo.add(panelKPIs, BorderLayout.NORTH);
        panelIzquierdo.add(centroGrafica, BorderLayout.CENTER);

        // Panel derecho: Documentación didáctica + Tabla de tiempos dinámica
        JPanel panelDerechoContenido = new JPanel();
        panelDerechoContenido.setLayout(new BoxLayout(panelDerechoContenido, BoxLayout.Y_AXIS));
        panelDerechoContenido.setBackground(COLOR_FONDO);
        panelDerechoContenido.setBorder(new EmptyBorder(15, 10, 15, 15));

        String[] bloques = infoDidactica.split("===");
        for (int i = 1; i < bloques.length; i += 2) {
            String tituloBloque = bloques[i].trim();
            String cuerpoBloque = (i + 1 < bloques.length) ? bloques[i + 1].trim() : "";
            boolean esPseudo = tituloBloque.contains("PSEUDOCÓDIGO");
            panelDerechoContenido.add(crearSeccionTexto(tituloBloque, cuerpoBloque, esPseudo));
            panelDerechoContenido.add(Box.createVerticalStrut(10));
        }

        DefaultTableModel modeloTabla = new DefaultTableModel(new Object[]{"Tamaño (N)", "Tiempo (ns)", "Tiempo (ms)"}, 0);
        JScrollPane tablaScroll = crearTablaEstilizada(modeloTabla);
        tablaScroll.setPreferredSize(new Dimension(400, 290));
        tablaScroll.setMaximumSize(new Dimension(2000, 290));
        tablaScroll.setAlignmentX(Component.LEFT_ALIGNMENT);

        panelDerechoContenido.add(tablaScroll);

        // Lógica de reproducción paso a paso para la gráfica individual.
        final int[] indiceActual = {0};

        Runnable agregarSiguientePunto = () -> {
            int idx = indiceActual[0];
            if (idx < tamagnos.size()) {
                int n = tamagnos.get(idx);
                long ns = tiempos.get(idx);
                double ms = ns / 1_000_000.0;

                dataset.setValue(ns, nombreFuncion, String.valueOf(n));
                modeloTabla.addRow(new Object[]{
                    nf.format(n),
                    nf.format(ns) + " ns",
                    String.format("%.3f ms", ms)
                });

                indiceActual[0]++;
                lblEstado.setText("Punto " + indiceActual[0] + " de " + tamagnos.size() + " (N = " + nf.format(n) + ")");
            }
        };

        Timer timer = new Timer(600, null);
        timer.addActionListener(e -> {
            if (indiceActual[0] < tamagnos.size()) {
                agregarSiguientePunto.run();
            } else {
                timer.stop();
                btnPlay.setText("▶  Reproducir Avance");
                lblEstado.setText("✔ Animación completada (" + tamagnos.size() + " puntos)");
            }
        });

        btnPlay.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();
                btnPlay.setText("▶  Continuar");
            } else {
                if (indiceActual[0] >= tamagnos.size()) {
                    indiceActual[0] = 0;
                    modeloTabla.setRowCount(0);
                    for (int i = 0; i < tamagnos.size(); i++) {
                        dataset.setValue(null, nombreFuncion, String.valueOf(tamagnos.get(i)));
                    }
                }
                btnPlay.setText("⏸  Pausar");
                agregarSiguientePunto.run();
                timer.start();
            }
        });

        btnReiniciar.addActionListener(e -> {
            timer.stop();
            indiceActual[0] = 0;
            modeloTabla.setRowCount(0);
            for (int i = 0; i < tamagnos.size(); i++) {
                dataset.setValue(null, nombreFuncion, String.valueOf(tamagnos.get(i)));
            }
            btnPlay.setText("▶  Reproducir Avance");
            lblEstado.setText("Reiniciado (0 / " + tamagnos.size() + ")");
        });

        btnVerTodo.addActionListener(e -> {
            timer.stop();
            while (indiceActual[0] < tamagnos.size()) {
                agregarSiguientePunto.run();
            }
            btnPlay.setText("▶  Reproducir Avance");
            lblEstado.setText("✔ Mostrando todos los puntos (" + tamagnos.size() + " / " + tamagnos.size() + ")");
        });

        JScrollPane scrollDerecho = new JScrollPane(panelDerechoContenido);
        scrollDerecho.setBorder(null);
        scrollDerecho.getVerticalScrollBar().setUnitIncrement(16);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdo, scrollDerecho);
        splitPane.setDividerLocation(760);
        splitPane.setDividerSize(6);
        splitPane.setBorder(null);

        JPanel contenedorPrincipal = new JPanel(new BorderLayout());
        contenedorPrincipal.add(splitPane, BorderLayout.CENTER);
        return contenedorPrincipal;
    }

    // Construye el panel comparativo ANIMADO con las 3 gráficas juntas.
    public static JPanel crearPanelComparativo(
            List<Integer> tamagnos,
            List<Long> tiemposInsertion,
            List<Long> tiemposBubble,
            List<Long> tiemposQuick) {

        NumberFormat nf = NumberFormat.getInstance(Locale.US);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        long maxGlobal = 0;
        for (int i = 0; i < tamagnos.size(); i++) {
            maxGlobal = Math.max(maxGlobal, tiemposInsertion.get(i));
            maxGlobal = Math.max(maxGlobal, tiemposBubble.get(i));
            maxGlobal = Math.max(maxGlobal, tiemposQuick.get(i));
        }

        for (int i = 0; i < tamagnos.size(); i++) {
            String etiquetaN = String.valueOf(tamagnos.get(i));
            dataset.addValue(null, "Insertion Sort (Propio)", etiquetaN);
            dataset.addValue(null, "Bubble Sort", etiquetaN);
            dataset.addValue(null, "Quick Sort", etiquetaN);
        }

        JPanel panelIzquierdo = new JPanel(new BorderLayout(12, 12));
        panelIzquierdo.setBackground(COLOR_FONDO);
        panelIzquierdo.setBorder(new EmptyBorder(15, 15, 15, 10));

        JPanel panelKPIs = new JPanel(new GridLayout(1, 3, 10, 0));
        panelKPIs.setBackground(COLOR_FONDO);

        JPanel cardIns = crearTarjetaKPI("Insertion Sort (N = -)", "En espera...", COLOR_INSERTION, COLOR_TARJETA);
        JPanel cardBub = crearTarjetaKPI("Bubble Sort (N = -)", "En espera...", COLOR_BUBBLE, COLOR_TARJETA);
        JPanel cardQui = crearTarjetaKPI("Quick Sort (N = -)", "En espera...", COLOR_QUICK, COLOR_TARJETA);
        JLabel lblTituloIns = (JLabel) cardIns.getComponent(0);
        JLabel lblValorIns = (JLabel) cardIns.getComponent(1);
        JLabel lblTituloBub = (JLabel) cardBub.getComponent(0);
        JLabel lblValorBub = (JLabel) cardBub.getComponent(1);
        JLabel lblTituloQui = (JLabel) cardQui.getComponent(0);
        JLabel lblValorQui = (JLabel) cardQui.getComponent(1);
        panelKPIs.add(cardIns);
        panelKPIs.add(cardBub);
        panelKPIs.add(cardQui);
        JFreeChart chart = ChartFactory.createLineChart(
                "Comparación Dinámica: Insertion Sort vs Bubble Sort vs Quick Sort",
                "Tamaño de Entrada (N)",
                "Tiempo (Nanosegundos)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        estilizarGrafica(chart, new Color[]{COLOR_INSERTION, COLOR_BUBBLE, COLOR_QUICK});
        chart.getCategoryPlot().getRangeAxis().setRange(0, maxGlobal * 1.08);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_BORDE, 1, true),
                new EmptyBorder(10, 10, 10, 10)
        ));
        chartPanel.setBackground(COLOR_TARJETA);
        JPanel barraControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        barraControles.setBackground(COLOR_TARJETA);
        barraControles.setBorder(new LineBorder(COLOR_BORDE, 1, true));
        JButton btnPlay = crearBotonEstilizado("▶  Reproducir Avance", new Color(22, 163, 74));
        JButton btnReiniciar = crearBotonEstilizado("↺  Reiniciar", new Color(71, 85, 105));
        JButton btnVerTodo = crearBotonEstilizado("⏭  Mostrar Todo", COLOR_INSERTION);
        JLabel lblEstado = new JLabel("Presiona Play para animar el avance (0 / " + tamagnos.size() + ")");
        lblEstado.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblEstado.setForeground(COLOR_TEXTO);
        barraControles.add(btnPlay);
        barraControles.add(btnReiniciar);
        barraControles.add(btnVerTodo);
        barraControles.add(lblEstado);
        JPanel centroGrafica = new JPanel(new BorderLayout(0, 10));
        centroGrafica.setBackground(COLOR_FONDO);
        centroGrafica.add(chartPanel, BorderLayout.CENTER);
        centroGrafica.add(barraControles, BorderLayout.SOUTH);
        panelIzquierdo.add(panelKPIs, BorderLayout.NORTH);
        panelIzquierdo.add(centroGrafica, BorderLayout.CENTER);
        JPanel panelDerecho = new JPanel(new BorderLayout(0, 12));
        panelDerecho.setBackground(COLOR_FONDO);
        panelDerecho.setBorder(new EmptyBorder(15, 10, 15, 15));
        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"N", "Insertion (ns)", "Bubble (ns)", "Quick (ns)"}, 0);
        JScrollPane tablaScroll = crearTablaEstilizada(modelo);
        String conclusiones = "1. Quick Sort O(n log n) es ampliamente el más rápido: mantiene tiempos mínimos "
                + "incluso al ordenar 30,000 elementos.\n\n"
                + "2. Insertion Sort O(n²) supera a Bubble Sort porque realiza desplazamientos de memoria "
                + "en lugar de múltiples intercambios completos en cada comparación.\n\n"
                + "3. Bubble Sort O(n²) muestra la curva más pronunciada y es el menos eficiente para "
                + "grandes volúmenes de datos.";
        JPanel cardConclusiones = crearSeccionTexto("ANÁLISIS Y CONCLUSIONES COMPARATIVAS", conclusiones, false);
        panelDerecho.add(tablaScroll, BorderLayout.CENTER);
        panelDerecho.add(cardConclusiones, BorderLayout.SOUTH);
        final int[] indiceActual = {0};
        Runnable agregarSiguientePunto = () -> {
            int idx = indiceActual[0];
            if (idx < tamagnos.size()) {
                int n = tamagnos.get(idx);
                String etiquetaN = String.valueOf(n);
                long tIns = tiemposInsertion.get(idx);
                long tBub = tiemposBubble.get(idx);
                long tQui = tiemposQuick.get(idx);
                dataset.setValue(tIns, "Insertion Sort (Propio)", etiquetaN);
                dataset.setValue(tBub, "Bubble Sort", etiquetaN);
                dataset.setValue(tQui, "Quick Sort", etiquetaN);
                modelo.addRow(new Object[]{
                    nf.format(n),
                    nf.format(tIns),
                    nf.format(tBub),
                    nf.format(tQui)
                });
                lblTituloIns.setText("INSERTION SORT (N = " + nf.format(n) + ")");
                lblValorIns.setText(nf.format(tIns) + " ns");
                lblTituloBub.setText("BUBBLE SORT (N = " + nf.format(n) + ")");
                lblValorBub.setText(nf.format(tBub) + " ns");
                lblTituloQui.setText("QUICK SORT (N = " + nf.format(n) + ")");
                lblValorQui.setText(nf.format(tQui) + " ns");
                indiceActual[0]++;
                lblEstado.setText("Punto " + indiceActual[0] + " de " + tamagnos.size() + " (N = " + nf.format(n) + ")");
            }
        };
        Timer timer = new Timer(650, null);
        timer.addActionListener(e -> {
            if (indiceActual[0] < tamagnos.size()) {
                agregarSiguientePunto.run();
            } else {
                timer.stop();
                btnPlay.setText("▶  Reproducir Avance");
                lblEstado.setText("✔ Animación completada (" + tamagnos.size() + " puntos)");
            }
        });
        btnPlay.addActionListener(e -> {
            if (timer.isRunning()) {
                timer.stop();
                btnPlay.setText("▶  Continuar");
            } else {
                if (indiceActual[0] >= tamagnos.size()) {
                    indiceActual[0] = 0;
                    modelo.setRowCount(0);
                    for (int i = 0; i < tamagnos.size(); i++) {
                        String etiquetaN = String.valueOf(tamagnos.get(i));
                        dataset.setValue(null, "Insertion Sort (Propio)", etiquetaN);
                        dataset.setValue(null, "Bubble Sort", etiquetaN);
                        dataset.setValue(null, "Quick Sort", etiquetaN);
                    }
                }
                btnPlay.setText("⏸  Pausar");
                agregarSiguientePunto.run();
                timer.start();
            }
        });
        btnReiniciar.addActionListener(e -> {
            timer.stop();
            indiceActual[0] = 0;
            modelo.setRowCount(0);
            for (int i = 0; i < tamagnos.size(); i++) {
                String etiquetaN = String.valueOf(tamagnos.get(i));
                dataset.setValue(null, "Insertion Sort (Propio)", etiquetaN);
                dataset.setValue(null, "Bubble Sort", etiquetaN);
                dataset.setValue(null, "Quick Sort", etiquetaN);
            }
            lblTituloIns.setText("INSERTION SORT (N = -)");
            lblValorIns.setText("En espera...");
            lblTituloBub.setText("BUBBLE SORT (N = -)");
            lblValorBub.setText("En espera...");
            lblTituloQui.setText("QUICK SORT (N = -)");
            lblValorQui.setText("En espera...");
            btnPlay.setText("▶  Reproducir Avance");
            lblEstado.setText("Reiniciado (0 / " + tamagnos.size() + ")");
        });
        btnVerTodo.addActionListener(e -> {
            timer.stop();
            while (indiceActual[0] < tamagnos.size()) {
                agregarSiguientePunto.run();
            }
            btnPlay.setText("▶  Reproducir Avance");
            lblEstado.setText("✔ Mostrando todos los puntos (" + tamagnos.size() + " / " + tamagnos.size() + ")");
        });
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, panelIzquierdo, panelDerecho);
        splitPane.setDividerLocation(760);
        splitPane.setDividerSize(6);
        splitPane.setBorder(null);
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.add(splitPane, BorderLayout.CENTER);
        return contenedor;
    }
    // Construye el bloque institucional de la Universidad Central con fondo blanco para resaltar el logo.

    private static JPanel crearPanelUniversidad() {
        JPanel panelUni = new JPanel(new FlowLayout(FlowLayout.RIGHT, 14, 0));
        panelUni.setOpaque(false);

        JPanel textosUni = new JPanel(new GridLayout(2, 1, 0, 2));
        textosUni.setOpaque(false);

        JLabel lblNombreUni = new JLabel("UNIVERSIDAD CENTRAL", SwingConstants.RIGHT);
        lblNombreUni.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblNombreUni.setForeground(Color.WHITE);

        JLabel lblFacultad = new JLabel("Facultad de Ingeniería y Ciencias Básicas", SwingConstants.RIGHT);
        lblFacultad.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblFacultad.setForeground(new Color(203, 213, 225));

        textosUni.add(lblNombreUni);
        textosUni.add(lblFacultad);

        // Contenedor blanco para que el escudo resalte sobre el encabezado oscuro.
        JPanel contenedorLogo = new JPanel(new BorderLayout());
        contenedorLogo.setBackground(Color.WHITE);
        contenedorLogo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(new Color(203, 213, 225), 1, true),
                new EmptyBorder(5, 8, 5, 8)
        ));

        JLabel lblLogo = new JLabel();
        URL urlLogo = Graficador.class.getResource("/InsertionSortYadir/logo_ucentral.png");

        if (urlLogo != null) {
            ImageIcon iconoOriginal = new ImageIcon(urlLogo);
            // Escalamos manteniendo la proporción original a una altura de 54px.
            int alto = 54;
            int ancho = (iconoOriginal.getIconWidth() * alto) / iconoOriginal.getIconHeight();
            if (ancho <= 0) {
                ancho = 54;
            }
            Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
            lblLogo.setIcon(new ImageIcon(imagenEscalada));
        } else {
            lblLogo.setText(" UC ");
            lblLogo.setFont(new Font("Segoe UI", Font.BOLD, 18));
            lblLogo.setForeground(new Color(0, 102, 51));
            lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        }

        contenedorLogo.add(lblLogo, BorderLayout.CENTER);
        panelUni.add(textosUni);
        panelUni.add(contenedorLogo);
        return panelUni;
    }


// Abre la ventana principal con encabezado moderno y pestañas estilizadas.
   
    public static void mostrarDashboardCompleto(
            List tamagnos,
            List tiemposInsertion, boolean calidadInsertion,
            List tiemposBubble, boolean calidadBubble,
            List tiemposQuick, boolean calidadQuick) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }
        JFrame frame = new JFrame("Análisis Comparativo de Algoritmos de Ordenamiento");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_PRIMARIO);
        header.setBorder(new EmptyBorder(14, 20, 14, 20));
        JLabel tituloApp = new JLabel("PRODUCTO DIDÁCTICO Y COMPARATIVO DE ORDENAMIENTO");
        tituloApp.setFont(new Font("Segoe UI", Font.BOLD, 18));
        tituloApp.setForeground(Color.WHITE);
        JLabel subtituloApp = new JLabel("Análisis de complejidad Big-O, verificación de calidad y benchmarking experimental  |  Autor: Yadir Diaz");
        subtituloApp.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtituloApp.setForeground(new Color(148, 163, 184));
        JPanel textosHeader = new JPanel(new GridLayout(2, 1, 0, 2));
        textosHeader.setOpaque(false);
        textosHeader.add(tituloApp);
        textosHeader.add(subtituloApp);
        header.add(textosHeader, BorderLayout.WEST);
        header.add(crearPanelUniversidad(), BorderLayout.EAST);
        JTabbedPane pestanas = new JTabbedPane();
        pestanas.setFont(new Font("Segoe UI", Font.BOLD, 13));
        pestanas.setBackground(COLOR_FONDO);
        pestanas.addTab("  1. Insertion Sort (Propio)  ",
                crearPanelIndividual(tiemposInsertion, tamagnos, "Insertion Sort",
                        InsertionSort.obtenerInformacion(), calidadInsertion, COLOR_INSERTION));
        pestanas.addTab("  2. Bubble Sort  ",
                crearPanelIndividual(tiemposBubble, tamagnos, "Bubble Sort",
                        BubbleSort.obtenerInformacion(), calidadBubble, COLOR_BUBBLE));
        pestanas.addTab("  3. Quick Sort  ",
                crearPanelIndividual(tiemposQuick, tamagnos, "Quick Sort",
                        QuickSort.obtenerInformacion(), calidadQuick, COLOR_QUICK));
        pestanas.addTab("  4. Comparación General  ",
                crearPanelComparativo(tamagnos, tiemposInsertion, tiemposBubble, tiemposQuick));
        frame.add(header, BorderLayout.NORTH);
        frame.add(pestanas, BorderLayout.CENTER);
        frame.setSize(1340, 780);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
