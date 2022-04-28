package gameFactory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class NewPongWindow extends JPanel implements ActionListener, KeyListener {
    private Pong game;
    private Ball ball;
    private Bars player1, player2;
    private int player1score, player2score;

    public NewPongWindow(Pong game) {
        setBackground(Color.BLUE);
        this.game = game;
        ball = new Ball(game);
        player1 = new Bars(game, KeyEvent.VK_UP, KeyEvent.VK_DOWN, game.getWidth() - 36);
        player2 = new Bars(game, KeyEvent.VK_W, KeyEvent.VK_S, 20);
        Timer timer = new Timer(5, this);
        timer.start();
        addKeyListener(this);
        setFocusable(true);
    }

    public Bars getPlayer(int playerNo) {
        if (playerNo == 1)
            return player1;
        else
            return player2;
    }

    public void increaseScore(int playerNo) {
        if (playerNo == 1)
            player1score++;
        else
            player2score++;
    }

    public int getScore(int playerNo) {
        if (playerNo == 1)
            return player1score;
        else
            return player2score;
    }

    private void update() {
        ball.update();
        player1.update();
        player2.update();
    }

    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }
    
    //Checkers for key inputs and updates
    public void keyPressed(KeyEvent e) {
        player1.pressed(e.getKeyCode());
        player2.pressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        player1.released(e.getKeyCode());
        player2.released(e.getKeyCode());
    }

    public void keyTyped(KeyEvent e) {
        ;
    }

    @Override
    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        graphic.drawString(game.getPanel().getScore(1) + " : " + game.getPanel().getScore(2), game.getWidth() / 2, 10);
        ball.paint(graphic);
        player1.paint(graphic);
        player2.paint(graphic);
    }
}