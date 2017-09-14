//GABRIELA ORELLANA 1244821

import java.awt.Color;

public class Ball 
{  
    private double deltaT;
    private double radius;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private Color color;

    //MOVE the ball
    public void move() 
    {
        positionX += deltaT * velocityX;
        positionY += deltaT * velocityY;
    }

    @Override
    public String toString() 
    {
        return "position(" + getPositionX() + "," + getPositionY() + "), velocity("
                + getVelocityX() + "," + getVelocityY() + "), deltaT(" + getDeltaT()
                + "), radius(" + getRadius() + ")";
    }

    public double getPositionX() 
    {
        return this.positionX;
    }

    public void setPositionX(double positionX) 
    {
        this.positionX = positionX;
    }
    
    public double getPositionY() 
    {
        return this.positionY;
    }

    public void setPositionY(double positionY) 
    {
        this.positionY = positionY;
    }

    public double getVelocityX() 
    {
        return this.velocityX;
    }

    public void setVelocityX(double velocityX) 
    {
        this.velocityX = velocityX;
    }

    public double getVelocityY() 
    {
        return this.velocityY;
    }

    public void setVelocityY(double velocityY) 
    {
        this.velocityY = velocityY;
    }

    public double getDeltaT() 
    {
        return this.deltaT;
    }

    public void setDeltaT(double deltaT) 
    {
        this.deltaT = deltaT;
    }

    public double getRadius() 
    {
        return this.radius;
    }

    public void setRadius(double radius) 
    {
        this.radius = radius;
    }

    public Color getColor() 
    {
        return color;
    }

    public void setColor(Color color) 
    {
        this.color = color;
    }

}
