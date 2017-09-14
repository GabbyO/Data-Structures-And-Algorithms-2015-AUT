//GABRIELA ORELLANA 1244821

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class BreakOut extends JPanel
{
    Player player;
    HighScore highScore;
    JLabel info_from_file;
    
    public BreakOut()
    {
        super();
        setPreferredSize(new Dimension(600, 600));
        player = new Player();
        highScore = new HighScore();
        info_from_file = new JLabel(highScore.sb.toString());
        add(info_from_file);
        setToolTipText(highScore.reader());
        setForeground(Color.red);
    }

    //GET user enter name
    public void userEnters()
    {
        player.userEnter();
    }
    
    @Override 
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        //Set Background: Black
        setBackground(Color.BLACK);
        
        //Set font and its colour/player score.
        g.setColor(Color.WHITE);
        g.drawString("HIGHSCORE: " + player.getScore(), 600/2, 600/2);
        Toolkit.getDefaultToolkit().sync();      
        repaint();
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Breakout High Score");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        BreakOut breakOut;
        breakOut = new BreakOut();
        frame.setContentPane(breakOut);
        frame.pack();

        
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        frame.setLocation((int) ((toolkit.getScreenSize().getWidth()
                - frame.getSize().getWidth()) / 2),
                (int) ((toolkit.getScreenSize().getHeight()
                - frame.getSize().getHeight()) / 2));
        frame.setVisible(true);

    }
    
}
