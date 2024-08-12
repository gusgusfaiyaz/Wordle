package Java_Cuatrimestre_3.Ordinario;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
public class Teclado extends JPanel {
    private Map<Character, JButton> keyboardButtons;
    public Teclado() {
        setLayout(new GridLayout(3, 10, 5, 5)); // 3 filas con separación de 5px
        setBorder(new EmptyBorder(10, 10, 10, 10)); // Margen alrededor del teclado

        keyboardButtons = new HashMap<>();

        // Array de chars QWERTY
        char[] qwertyOrden = {
                'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p',
                'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l',
                'z', 'x', 'c', 'v', 'b', 'n', 'm'
        };
        for (char c : qwertyOrden) { //Ciclo foreach para asignar un boton a cada caracter del teclado
            JButton button = new JButton(String.valueOf(c));
            button.setForeground(Color.BLACK);
            button.setBackground(Color.LIGHT_GRAY);
            button.setFont(new Font("Arial", Font.BOLD, 20));
            button.setEnabled(true);
            keyboardButtons.put(c, button);
            add(button);
        }
    }
    public void actualizarTecla(char letra, Color color) {
        //Metodo para actualizar los colores de cada tecla
        JButton button = keyboardButtons.get(letra);
        if (button != null) {
            button.setBackground(color);
        }
    }
    public void reiniciarTeclado() {
        //Metodo para reiniciar las letras marcadas del teclado
        for (JButton button : keyboardButtons.values()) {
            button.setBackground(Color.LIGHT_GRAY);
        }
    }
}