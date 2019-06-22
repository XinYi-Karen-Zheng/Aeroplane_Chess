import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Plane{
    String color;
    int currentX;
    int currentY;
    boolean inFinalRoll = false;
    Player owner;
    
    int startX;
    int startY;
    int finalRollX;
    int finalRollY;
    
    String sourceName;

    
    public Plane(String Color, int x, int y, Player p) {
        this.color = Color;
        this.currentX = x;
        this.currentY = y;
        this.owner = p;
        
        if (color.equals("red")) {
            sourceName = "src/red_plane.png";
            startX = 1;
            startY = 3;
            finalRollX = 1;
            finalRollY = 5;
        }
        else if (color.equals("green")) {
            sourceName = "src/green_plane.png";
            startX = 3;
            startY = 9;
            finalRollX = 5;
            finalRollY = 9;
        }
        else if (color.equals("black")) {
            sourceName = "src/black_plane.png";
            startX = 9;
            startY = 7;
            finalRollX = 9;
            finalRollY = 5;
        }
        else {
            sourceName = "src/blue_plane.png";
            startX = 7;
            startY = 1;
            finalRollX = 5;
            finalRollY = 1;
        }
        
    }
    
    public void start(int steps, Command c) {
        if (c.returnType().equals("plane")) {
            currentX = startX;
            currentY = startY;
            c.changeState();
            c.repaint();
            
            c.returnNext().changeState();
            ImageIcon image = new ImageIcon(sourceName);
            image.setImage(image.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT ));
            c.returnNext().setIcon(image);
        } else {
            move(6, c);
        }
    }
    
    public void move(int steps, Command c) {
        
        if (!c.returnType().equals("plane")) {
        Command destination = c;
        if (inFinalRoll) {
            destination = c.turn;
            steps = steps - 1;
        }
        
        for (int x=0; x<steps; x++) {
            if (destination.returnNext()!=null) {
            destination = destination.returnNext();
            } else {
                owner.getPlanes().remove(this);
                destination = null;
                break;
            }
        }
        
        if (destination != null) {
        if (!destination.checkPlane()) {
            if (inFinalRoll) {
                c.changeState();
                Color color = new Color(c.RGBR, c.RGBG, c.RGBB);
                destination.setBackground(color);
            } else {
            c.changeState();
            Color color = new Color(c.RGBR, c.RGBG, c.RGBB);
            c.setBackground(color);}
            currentX = destination.getX();
            currentY = destination.getY();
            destination.changeState();
            ImageIcon image = new ImageIcon(sourceName);
            image.setImage(image.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT ));
            destination.setIcon(image);
            destination.action(this);
        }
        
        
        inFinalRoll = false;
        
        if(currentX == finalRollX && currentY == finalRollY) {
            inFinalRoll = true;
            }
        }
        }
    }
    
    public String printLocation() {
        return " ( " + Integer.valueOf(currentX) + " , " + Integer.valueOf(currentY) + " ) ";
    }
    
}