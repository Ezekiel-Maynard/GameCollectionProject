package gameFactory;

import java.awt.Color;

import javax.swing.JFrame;

public class Pong extends JFrame {
    private final static int WIDTH = 700, HEIGHT = 450;
    private NewPongWindow window;

    public Pong() {
        setSize(WIDTH, HEIGHT);
        setTitle("Pong");
        setBackground(Color.BLUE);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        window = new NewPongWindow(this);
        add(window);
    }

    public NewPongWindow getPanel() {
        return window;
    }

    public static void main(String[] args) {
        new Pong();
    }
}