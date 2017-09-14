//GABRIELA ORELLANA 1244821

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Game extends JPanel implements ActionListener, MouseMotionListener, MouseListener, Runnable
{
    public static final int HEIGHT = 600;
    public static final int WIDTH = 600;
    private Brick.User rowColor;
    private Brick.User life;
    private static Font endFont;
    private static Font scoreFont;
    private Brick.User user;
    private Player player;
    private Timer time;
    private Brick brick;
    private Paddle paddle;
    private Ball ball;
    private ArrayList<ArrayList<Brick> > bricks;
    private Random random;
    private final List<Ball> balls;
    private Thread thread;
    private boolean isRunning;
    public static final int NUMBER_OF_BALLS = 1;
    public int brickSizeX, brickSizeY;
    private HighScore highScore;
    private int score;
    private int lifes1, lifes2, lifes3, lifes4, lifes5 ;  
    private Brick tempBrick;
            
    public Game()
    {
        super();
        
        //SET SIZE 
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
        //SETTING up
        time = new Timer(15, this);
        paddle = new Paddle((WIDTH/2)-(Paddle.PADDLE_WIDTH/2));
        ball = new Ball();
        player = new Player();
        brick = new Brick();
        random = new Random(System.currentTimeMillis());
        balls = new ArrayList<>();
        highScore = new HighScore();
        bricks = new ArrayList<ArrayList<Brick> >();
        
        //GET SCORE
        this.score = player.getScore();
        
        //GETTING life
        lifes1= user.TOP1.life;
        lifes2= user.TOP2.life;
        lifes3= user.TOP3.life;
        lifes4= user.TOP4.life;
        lifes5= user.TOP5.life;
        
        //FONT
        scoreFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        endFont = new Font(Font.SANS_SERIF, Font.BOLD, 20);
        
        //For balls
        for (int i = 0; i < NUMBER_OF_BALLS; i++) 
        {
            ball = new Ball();
            ball.setPositionX(paddle.getX());
            ball.setPositionY(paddle.getY());
            double x = random.nextDouble();
            double y = random.nextDouble();
            double n = Math.sqrt(x * x + y * y);
            ball.setVelocityX(x / n);
            ball.setVelocityY(y / n);
            ball.setRadius(10);
            ball.setDeltaT(15);
            balls.add(ball);
        }

        //Create Brick Array of colours
        for(int i = 0; i < 5; ++i)
        {
            ArrayList<Brick> temp = new ArrayList<Brick>();
            rowColor = null;
            switch(i)
            {
                //From Enum of User in the brick class
                //TOP1 - TOP5 have each own of colour
                case 1:
                    rowColor = user.TOP1;
                    break;
                case 2:
                    rowColor = user.TOP2;
                    break;
                case 3:
                    rowColor = user.TOP3;
                    break;
                case 4:
                    rowColor = user.TOP4;
                    break;
                case 5:
                default:
                    rowColor = user.TOP5;
                    break;
            }
            
            
            //Adding Brick to temp ArrayList
            for(int j = 0; j < 8; ++j)
            {
                tempBrick = new Brick((j * brick.sizeX() + 4), ((i+2) * brick.sizeY()), rowColor);
                temp.add(tempBrick);
                
                //Storing the array's brick's location
                brickSizeX = (j * brick.sizeX()) + 4;
                brickSizeY = (i+2) * brick.sizeY();
            }
             
            bricks.add(temp);
            
            if(tempBrick.equals( ball.getPositionX()) && tempBrick.equals( ball.getPositionY()))
            {
                
                if(lifes1 == 0)
                {
                    rowColor = user.DEAD;
                    bricks.remove(temp);
                }
                
                if(lifes2 == 0)
                {
                    rowColor = user.DEAD;
                    bricks.remove(temp);
                }
                
                if(lifes3 == 0)
                {
                    rowColor = user.DEAD;
                    bricks.remove(temp);
                }
                
                if(lifes4 == 0)
                {
                    rowColor = user.DEAD;
                    bricks.remove(temp);
                }
                
                if(lifes5 == 0)
                {
                    rowColor = user.DEAD;
                    bricks.remove(temp);
                 }
                
            }
            
            addMouseMotionListener(this);
            addMouseListener(this);
            requestFocus();
        }
    }
     
    @Override
    public void actionPerformed(ActionEvent e)
    {
        ball.move();
        repaint();
    }
     
    //RESTARTING the ball if died
    private void resetBall() 
    {
        if(gameOver() == true)
        {
            time.stop();
            return;
        }
        
        ball.setVelocityX(WIDTH/2);
        ball.setVelocityY((HEIGHT/2) + 80);
        paddle.setLives(paddle.getLives() - 1);
    }
    
    //CHECK if ball have been at Y bottom panel
    //GAMEOVER
    private boolean gameOver() 
    {
        if(ball.getPositionY() == HEIGHT)
        {
            return true;
        }     
        return false;
    }

    @Override 
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //Set Background: Black
        setBackground(Color.BLACK);
        
        //Draw Paddle
        paddle.drawPaddle(g);
        
        //Create Ball and its colour/size
        for (Ball ball : balls) 
        {
            g.setColor(Color.WHITE);
            g.fillOval((int) (ball.getPositionX() - ball.getRadius()),
                    (int) (ball.getPositionY() - ball.getRadius()),
                    (int) ball.getRadius() * 2, (int) ball.getRadius() * 2);
        }
        
        //Draw Bricks
        for(ArrayList<Brick> row : bricks)
        {
            for(Brick b : row)
            {
                b.draw(g);
            }
        }
        
        //Set font and its colour/player score.
        g.setColor(Color.PINK);
        g.setFont(scoreFont);
        g.drawString("Score: " + score, 10, 25);
        
        //If gameover is true, the font will diplay
        if(gameOver() == true && ball.getPositionY() <= HEIGHT)
        {
            setBackground(Color.BLACK);
            g.setColor(Color.WHITE);
            g.setFont(endFont);
            g.drawString("Game Over!  Score: " + score, (WIDTH/2) - 85, (HEIGHT/2));
            time.stop();
        }
        
        //IF the bricks are empty, the font of "you won" will display.
        if(empty() == true)
        {
            g.setColor(Color.WHITE);
            g.setFont(endFont);
            g.drawString("You won!  Score: " + score, (WIDTH/2) - 85, (HEIGHT/2));
            time.stop();
        }

        Toolkit.getDefaultToolkit().sync();      
        repaint();
    }

    //IF the array of bricks are empty, the brick isvisible will return true.
    private boolean empty() 
    {
        for(ArrayList<Brick> al : bricks)
        {
            if(!al.isEmpty())
            {
                return brick.isVisible() == true;
            }
        }
        return brick.isVisible() == false;
    }
 
    //Paddle will follow the x of the mouse
    @Override 
    public void mouseMoved(MouseEvent e)
    {
        paddle.setX(e.getX() - (Paddle.PADDLE_WIDTH / 2));
    }
     
    @Override
    public void mouseDragged(MouseEvent e){}
    @Override 
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
    //Run the thread
    @Override
    public void run() 
    {
        while (isRunning == true) 
        {
            repaint();

            for (Ball ball : balls) 
            {
                //Check if is hiting the wall of the panel
                if ((ball.getPositionX() + ball.getDeltaT() * ball.getVelocityX() > getWidth() - ball.getRadius())
                        || ball.getPositionX() + ball.getDeltaT() * ball.getVelocityX() < ball.getRadius()) 
                {
                    ball.setVelocityX(-ball.getVelocityX());
                }
                if ((ball.getPositionY() + ball.getDeltaT() * ball.getVelocityY() > getHeight() - ball.getRadius())
                        || ball.getPositionY() + ball.getDeltaT() * ball.getVelocityY() < ball.getRadius()) 
                {
                    ball.setVelocityY(-ball.getVelocityY());
                }
                
                //Checking if ball is hitting the Bricks
                if(ball.getPositionX() <= (brick.getX() + brickSizeX) && ball.getPositionX() >= brick.getX())
                {
                    //hit bottom
                    if(ball.getPositionY() <= (brick.getY() + brickSizeY) && 
                            ball.getPositionY() >= (brick.getY() + (brickSizeY/2)))
                    {
                        ball.setVelocityY(-ball.getVelocityY()); 
                        
                        //SCORE add up by 10
                        score+= 10;
                        
                        //LIFE -- by 1
                        this.lifes1--;
                        this.lifes2--;
                        this.lifes3--;
                        this.lifes4--;
                        this.lifes5--;
                    }
                   
                    //hit top
                    if(ball.getPositionY() >= ((brick.getY() + 10) - ball.getRadius()) && 
                        ball.getPositionY() < ((brick.getY()+ 10) + (ball.getRadius())))
                    {
                        ball.setVelocityY(ball.getVelocityY() * -1);
                        
                        //SCORE add up by 10
                        score+= 10;
                        
                        //LIFE -- by 1
                        this.lifes1--;
                        this.lifes2--;
                        this.lifes3--;
                        this.lifes4--;
                        this.lifes5--;
                    }
                }
                
                if(ball.getPositionY() <= (brick.getY() + brickSizeY) && ball.getPositionY() >= brick.getY())
                {
                    //Hit right
                    if(ball.getPositionX() <= (brick.getX() + brickSizeX) && 
                            ball.getPositionX() > (brick.getX() + (brickSizeX - (ball.getRadius() / 2))))
                    {
                        ball.setVelocityX(ball.getVelocityX() * -1);
                        
                        //SCORE add up by 10
                        score+= 10;
                        
                        //LIFE -- by 1
                        this.lifes1--;
                        this.lifes2--;
                        this.lifes3--;
                        this.lifes4--;
                        this.lifes5--;
                    }
                    
                    //Hit left
                    if(ball.getPositionX() >= (brick.getX() - ball.getRadius()) && 
                                ball.getPositionX() < (brick.getX() + (ball.getRadius() / 2)))
                    {
                        ball.setPositionX(ball.getPositionX() * -1);
                        
                        //SCORE add up by 10
                        score+= 10;
                        
                        //LIFE -- by 1
                        this.lifes1--;
                        this.lifes2--;
                        this.lifes3--;
                        this.lifes4--;
                        this.lifes5--;
                    }     
                }      
            }
   
            for (int i = 0; i < NUMBER_OF_BALLS; i++) 
            {
                for (int j = i + 1; j < NUMBER_OF_BALLS; j++) 
                {
                    Ball ball1 = balls.get(i);
                    Ball ball2 = balls.get(j);

                    double deltaX = (ball1.getPositionX() + ball1.getDeltaT() * ball1.getVelocityX())
                            - (ball2.getPositionX() + ball2.getDeltaT() * ball2.getVelocityX());

                    double deltaY = (ball1.getPositionY() + ball1.getDeltaT() * ball1.getVelocityY())
                            - (ball2.getPositionY() + ball2.getDeltaT() * ball2.getVelocityY());

                    if (Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) <= ball1.getRadius() + ball2.getRadius()) 
                    {
                        double tempX = ball1.getVelocityX();
                        double tempY = ball1.getVelocityY();
                        double tempT = ball1.getDeltaT();
                        ball1.setVelocityX(ball2.getVelocityX());
                        ball1.setVelocityY(ball2.getVelocityY());
                        ball1.setDeltaT(ball2.getDeltaT());
                        ball2.setVelocityX(tempX);
                        ball2.setVelocityY(tempY);
                        ball2.setDeltaT(tempT);
                        break;
                    }
                }
            }
            
            //CHECK if paddle have been hitted by ball
            if(paddle.hitPaddle(ball))
            {
                ball.setVelocityY(ball.getVelocityY() * -1);
            }
  
            //BALL move
            for (Ball ball : balls) 
            {
                ball.move();
            }

            try 
            {
                Thread.sleep(50);
            } catch (InterruptedException ex) 
            {
                Logger.getLogger(Game.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }
        resetBall();
    }
    
    //Start the thread
    public void start() 
    {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }
    
    //Stop the thread
    public void stop() 
    {
        isRunning = false;
        thread = null;
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Breakout");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Game game;
        game = new Game();
        frame.setContentPane(game);
        frame.pack();

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        frame.setLocation((int) ((toolkit.getScreenSize().getWidth()
                - frame.getSize().getWidth()) / 2),
                (int) ((toolkit.getScreenSize().getHeight()
                - frame.getSize().getHeight()) / 2));
        
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                game.stop();
            }
        });
        
        game.start();
    }

}
