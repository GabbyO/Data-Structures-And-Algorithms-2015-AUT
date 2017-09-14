//GABRIELA ORELLANA 1244821

import java.awt.Color;
import java.awt.Graphics;

public class Paddle extends Block
{
    public static final int DELTA_X = 5;
    public static final int PADDLE_WIDTH = 80;
    public static final int PADDLE_HEIGHT = 10;
    public static Color paddleColour;
    
    private int xPos;
    private final int yPos;
    private int lives;
	private Game game;
    
    public Paddle(int xPos)
    {
        this.xPos = xPos;
        this.yPos = Game.HEIGHT - 80;
        this.lives = 5;
        this.paddleColour = Color.BLUE;  
    }
    
    @Override
    public int getY() 
    {
        return this.yPos;
    }
    
    @Override
    public int sizeX() 
    {
        return this.PADDLE_WIDTH;
    }

    @Override
    public int getX()
    { 
        return this.xPos; 
    }
    
    @Override
    public int sizeY() 
    {
        return this.PADDLE_HEIGHT;
    }

    @Override
    public Color getColour() 
    {
        return this.paddleColour;
    }

    @Override
    public boolean isVisible() 
    {
        return true;
    }
     
    public void setX(int xPos)
    { 
        this.setxPos(xPos);
        if(xPos < 0)
        {
            this.setxPos(0);
        }
        
        if(xPos > (game.WIDTH - sizeX())) 
        {
            this.setxPos(game.WIDTH - sizeX());
        }
    }

    public void setxPos(int xPos) 
    {
        this.xPos = xPos;
    }
     
    public int getLives()
    { 
        return this.lives; 
    }
    
    public void setLives(int lives)
    { 
        this.lives = lives; 
    }
     
    public boolean hitPaddle(Ball ball)
    {
        if(ball.getPositionX() <= getX() + (sizeX() + 15))
        {
            if(ball.getPositionX() >= getX() - 10)
            {
                if((ball.getPositionY() + (ball.getRadius() - 1)) >= (getY()))
                {
                    if((ball.getPositionY() + (ball.getRadius() - 1)) <= (getY() + (PADDLE_HEIGHT - 5)))
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }
     
    public void drawPaddle(Graphics g)
    {
        g.setColor(paddleColour);
        g.fillRect(getX(), getY(), sizeX(), sizeY());
    }
}
