//GABRIELA ORELLANA 1244821

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore
{
    private Player player;
    private File file;
    private String line;
    
    private StringBuilder sb;
            
    public HighScore()
    {
        player = new Player();
        file = new File("text.txt");
        line = "";
        sb = new StringBuilder();
    }
    
    //READING the TEXT FILE
    public String reader()
    {
        try
        {  
            BufferedReader br = new BufferedReader(new FileReader(file));
            line = br.readLine();
            
            //Read File Line By Line
            while ((line = br.readLine()) != null)   
            {
                  // Print the content on the console
                  System.out.println (getLine());
                  sb.append(getLine());
            }
            br.close();
        }

        catch (IOException e)
        { 
            System.out.println("Class not found "+e);
                    
        }
        return this.getLine();
    }
   
   // SAVE current text in displayArea to the specified text file
   public void writing()
   {  
       try
        {  
            FileWriter fileWriter =
                new FileWriter(file);
                
            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            bufferedWriter.newLine();
            bufferedWriter.write(player.userEnter());
            bufferedWriter.close();
      }
      catch (IOException e)
      {
          System.out.println("Class not found "+e);
      }
   }

    public String getLine() 
    {
        return this.line;
    }
}
