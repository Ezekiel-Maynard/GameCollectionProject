package gameFactory;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bars {
    private static final int WIDTH = 10, HEIGHT = 60;
    private Pong game;
    private int up, down;
    private int x;
    private int y, actualY;

    public Bars(Pong game, int up, int down, int x) {
        this.game = game;
        this.x = x;
        y = game.getHeight() / 2;
        this.up = up;
        this.down = down;
    }

    public void update() {
        if (y > 0 && y < game.getHeight() - HEIGHT - 29)
            y += actualY;
        else if (y == 0)
            y++;
        else if (y == game.getHeight() - HEIGHT - 29)
            y--;
    }
    
    //Movement of the paddles
    public void pressed(int ButtonPressed) {
        if (ButtonPressed == up)
            actualY = -2;
        else if (ButtonPressed == down)
            actualY = 2;
    }

    //Stopping movement when the button is released
    public void released(int ButtonPressed) {
        if (ButtonPressed == up || ButtonPressed == down)
            actualY = 0;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, WIDTH, HEIGHT);
    }

    public void paint(Graphics graphic) {
        graphic.fillRect(x, y, WIDTH, HEIGHT);
        graphic.setColor(Color.WHITE);
    }
}