package Java_Cuatrimestre_3.Ordinario;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GUI extends Palabra {
    public GUI() {
        iniciarComponentes();
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
    private void reiniciarJuego() {
        intentos = 0;
        feedbackPanel.removeAll();
        feedbackPanel.revalidate();
        feedbackPanel.repaint();
        teclado.reiniciarTeclado();
        anadirPalabras();
    }
}
