package gameFactory;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class Ball {
    private static final int WIDTH = 30, HEIGHT = 30;
    private Pong game;
    private int x, y, actualx = 2, actualY = 2;

    
    public Ball(Pong game) {
        this.game = game;
        x = game.getWidth() / 2;
        y = game.getHeight() / 2;
    }
    
    //Updates Score with position of the ball, First to 10 Wins
    public void update() {
        x += actualx;
        y += actualY;
        if (x < 0) {
        	//increases player 1 score when the ball passes player 2 
            game.getPanel().increaseScore(1);
            x = game.getWidth() / 2;
            actualx = -actualx;
        }
        else if (x > game.getWidth() - WIDTH - 7) {
        	//increases player 2 score when the ball passes player 1 
            game.getPanel().increaseScore(2);
            x = game.getWidth() / 2;
            actualx = -actualx;
        }
        else if (y < 0 || y > game.getHeight() - HEIGHT - 29)
            actualY = -actualY;
        if (game.getPanel().getScore(1) == 10) {
            JOptionPane.showMessageDialog(null, "Player 1 wins", "Pong", JOptionPane.PLAIN_MESSAGE);
        	System.exit(0);
        }
        else if (game.getPanel().getScore(2) == 10) {
            JOptionPane.showMessageDialog(null, "Player 2 wins", "Pong", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        
    } checkCollision();
    }
    
    //BouncesBall
    public void checkCollision() {
        if (game.getPanel().getPlayer(1).getBounds().intersects(getBounds()) || game.getPanel().getPlayer(2).getBounds().intersects(getBounds())) 
            actualx = -actualx;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }
    
    //Colors and Fills Ball 
    public void paint(Graphics graphic) {
    	graphic.fillRect(x, y, WIDTH, HEIGHT);
    	graphic.setColor(Color.WHITE);
    }
}