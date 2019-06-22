import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class TDCommand extends Command{
    String text;
    static String[] TDList;
    
    public static void construct() {
        TDList = new String[3];
        TDList[0] = "What are you most self-conscious about?";
        TDList[1] = "Have you ever let someone take the blame for something you did?";
        TDList[2] = "What lie have you told that hurt someone?";
    }
    
    public TDCommand(int x, int y, boolean isPlane, String color, ArrayList<Player> players)
    {
        super(x, y, isPlane, color);
        text = TDList[(int) Math.random()*TDList.length];

    }
    
    public void action(Plane a) {
        String inputValue = JOptionPane.showInputDialog("Truth or Dare: " + text + 
                "\n" + "input DONE when done");
        inputValue = inputValue + " ";
    }
    
    public String returnType() {
        return "TD";
    }
    
}