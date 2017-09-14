//GABRIELA ORELLANA 1244821

import java.util.Scanner;


public class Player 
{
    private int score;
    private Brick brick;
    private Ball ball;
    
    public Player()
    {
        score = 0;
        brick = new Brick();
        ball = new Ball();
    }
    
    //GET USER entering
    public String userEnter()
    {
        Scanner scan = new Scanner(System.in);
        
        return scan.nextLine();
    }
    
    //SCORE 
    public int getScore()
    { 
        return this.score; 
    }
    
    public void setScore(int score)
    { 
        
        this.score = score; 
    }
    
    //GETTING name
    public String getName() 
    {
        return userEnter();
    }

}
