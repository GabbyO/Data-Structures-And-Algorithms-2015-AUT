//GABRIELA ORELLANA 1244821

import java.awt.Color;
import java.awt.Graphics;

public class Brick extends Block
{
    public final int BRICK_WIDTH = 75;
    public final int BRICK_HEIGHT = 30;
    private int xPos, yPos; 
    private User brickType;
    private boolean isVisible;
    
    public Brick()
    {
        
    }
    
    public Brick(int xPos, int yPos, User brickType)
    {
        this.xPos = xPos;
        this.yPos = yPos;
        this.brickType = brickType;
        this.isVisible = true;
    }
    
    @Override
    public int sizeX() 
    {
        return BRICK_WIDTH;
    }

    @Override
    public int sizeY() 
    {
        return BRICK_HEIGHT;
    }

    @Override
    public Color getColour() 
    {
        return this.brickType.color;
    }

    @Override
    public boolean isVisible() 
    {
        return isVisible == true;
    }
    
    public int getLife()
    {
        return this.brickType.life;
    }

    public void setColor(Color color) 
    {
        getColour();
    }
     
    //ENUM class for USER BRICKS
    //IT EACHs have OWN COLOR and LIFE
    public enum User
    {
        TOP5 (5, Color.MAGENTA),
        TOP4 (4, Color.RED), 
        TOP3 (3, Color.YELLOW),
        TOP2 (2, Color.GREEN),
        TOP1 (1, Color.CYAN),
        DEAD (0, Color.BLACK);
        
        public int life;
        private Color color;
         
        User(int life, Color color)
        {
            this.life = life;
            this.color = color;
        }
  
        public Color getColor()
        {    
            return this.color;   
        }
        public int getLife()
        {   
            return this.life;    
        }
    }
  
    @Override
    public int getX()
    {  
        return this.xPos;    
    }
    
    @Override
    public int getY()
    {  
        return this.yPos;    
    }
    
    //DRAW the brick
    public void draw(Graphics g)
    {
        g.setColor(getColour());
        g.fillRect((getX()+1), (getY()+1), sizeX()-10, sizeY()-4);
    }
 
    //CHECK if died
    public boolean dead() 
    {
        return isVisible() == false;   
    }
    
}
