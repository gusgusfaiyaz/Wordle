package Java_Cuatrimestre_3.Ordinario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
public class Palabra extends JFrame {
    public String palabraCorrecta;
    public int intentos;
    public JTextField inputField;
    public JPanel feedbackPanel;
    public Teclado teclado;
    public Palabra() {
        palabraCorrecta = "";
        intentos = 0;
        iniciarComponentes();
    }
    private void anadirPalabras() {
        String[] palabras = {
                "perro", "gatos", "camas", "mesas", "libro",
                "lapiz", "raton", "plato", "silla", "piano",
                "flores", "reloj", "patos", "paris", "noche",
                "diosa", "coche", "sueño", "marzo", "lunas",
                "marea", "riata", "nubos", "llave", "fruta",
                "dulce", "corto", "largo", "amigo", "barco",
                "banco", "bravo", "cerca", "cebra", "dedos",
                "dieta", "fresa", "giras", "huevo", "hojas",
                "ideas", "juego", "lento", "llama", "monto",
                "mundo", "nacer", "naval", "nieve", "ojera",
                "parca", "pasto", "pluma", "puños", "quedo",
                "queso", "riego", "sabor", "salsa", "techo",
                "tenis", "traje", "valor", "verde", "tropa",
                "viento", "vuelo", "yegua", "zorro", "bomba",
                "canal", "calor", "canto", "cenar", "dardo",
                "flaco", "golpe", "gordo", "guapo", "harina",
                "hurto", "jugar", "junta", "limón", "lista",
                "rezar", "comer", "noble", "novia", "obrar",
                "palma", "pasta", "piano", "pobre", "primo",
                "pulga", "quema", "quiso", "reina", "resto",
                "ritmo", "rural", "salud", "secas", "señal",
                "tabla", "trato", "trigo", "tumba", "vacas"
        };
        Random random = new Random();
        int randomIndex = random.nextInt(palabras.length);
        palabraCorrecta = palabras[randomIndex];
        System.out.println("Una palabra ha sido seleccionada al azar: " + palabraCorrecta);
    }
    private void iniciarComponentes() {
        setTitle("Adivina la Palabra!");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        inputField = new JTextField(10);
        JButton submitButton = new JButton("Enviar");
        JButton restartButton = new JButton("Reiniciar");

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonsPanel.add(submitButton);
        buttonsPanel.add(restartButton);

        inputPanel.add(new JLabel("Ingresa una palabra de 5 letras:"), BorderLayout.NORTH);
        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(buttonsPanel, BorderLayout.EAST);

        //se agrega el input panel al borderlayout en la posicion norte
        add(inputPanel, BorderLayout.NORTH);

        //se declara el nuevo panel que contendra los intentos del usuario
        feedbackPanel = new JPanel();
        feedbackPanel.setLayout(new GridLayout(6, 5, 5, 5));
        feedbackPanel.setBorder(new EmptyBorder(10, 50, 10, 50));

        //añade el panel de intentos al borderlayout
        add(feedbackPanel, BorderLayout.CENTER);

        teclado = new Teclado(); // Inicializar el teclado que muestra las letras ya usadas etc
        add(teclado, BorderLayout.SOUTH); // Añadir el teclado a la interfaz

        //Se crea el listener que se encarga de verificar la palabra cuando se presiona el boton de enviar
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verificarPalabra(inputField.getText().trim().toLowerCase());
                inputField.setText("");
            }
        });
        //Se crea el listener que se encarga de limpiar la pantalla cuando se presiona el boton de reiniciar
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reiniciarJuego();
            }
        });
        anadirPalabras();
    }
    private void verificarPalabra(String palabraUsuario) {
        if (intentos >= 6) { //verifica que los intentos sean menores o igual a 6
            JOptionPane.showMessageDialog(this, "Has alcanzado el número máximo de intentos. La palabra correcta era: " + palabraCorrecta);
            return;
        }

        if (palabraUsuario.length() != 5) { //verifica que la palabra sea de 5 letras
            JOptionPane.showMessageDialog(this, "La palabra debe tener 5 letras.");
            return;
        }

        intentos++;
        JPanel attemptPanel = new JPanel(new GridLayout(1, 5, 5, 5));

        // Contar las ocurrencias de cada letra en la palabra correcta
        HashMap<Character, Integer> letraConteo = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            char letra = palabraCorrecta.charAt(i);
            letraConteo.put(letra, letraConteo.getOrDefault(letra, 0) + 1);
        }

        // Primera pasada: asignar colores verdes y reducir el conteo en el HashMap
        for (int i = 0; i < 5; i++) {
            char letra = palabraUsuario.charAt(i);
            JLabel label = new JLabel(String.valueOf(letra), SwingConstants.CENTER);
            label.setOpaque(true);
            label.setBorder(new LineBorder(Color.BLACK, 1));
            label.setFont(new Font("Arial", Font.BOLD, 20));
            label.setForeground(Color.WHITE);

            Color color = new Color(128, 128, 128); // Gris por defecto

            if (letra == palabraCorrecta.charAt(i)) {
                color = new Color(93, 182, 11); // Verde
                letraConteo.put(letra, letraConteo.get(letra) - 1); // Reducir el conteo en el HashMap
            }

            label.setBackground(color);
            attemptPanel.add(label);
            teclado.actualizarTecla(letra, color);
        }

        // Segunda pasada: asignar colores amarillos si quedan ocurrencias disponibles en el HashMap
        for (int i = 0; i < 5; i++) {
            char letra = palabraUsuario.charAt(i);
            JLabel label = (JLabel) attemptPanel.getComponent(i);

            if (label.getBackground().equals(new Color(128, 128, 128)) && letraConteo.containsKey(letra) && letraConteo.get(letra) > 0) {
                label.setBackground(new Color(254, 203, 0)); // Amarillo
                letraConteo.put(letra, letraConteo.get(letra) - 1); // Reducir el conteo en el HashMap
                teclado.actualizarTecla(letra, new Color(254, 203, 0));
            }
        }
        feedbackPanel.add(attemptPanel);
        feedbackPanel.revalidate();
        feedbackPanel.repaint();

        if (palabraUsuario.equals(palabraCorrecta)) {
            JOptionPane.showMessageDialog(this, "¡Correcto! La palabra era: " + palabraCorrecta);
        } else if (intentos >= 6) {
            JOptionPane.showMessageDialog(this, "Has alcanzado el número máximo de intentos. La palabra correcta era: " + palabraCorrecta);
        }
    }

    //Metodo para reiniciar el juego
    private void reiniciarJuego() {
        intentos = 0;
        feedbackPanel.removeAll();
        feedbackPanel.revalidate();
        feedbackPanel.repaint();
        teclado.reiniciarTeclado();
        anadirPalabras();
    }
}