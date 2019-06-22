import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public abstract class Command extends JButton{
    int RGBR;
    int RGBG;
    int RGBB;
    String color;
    Command next;
    Command turn;
    
    int x; //x coordinate
    int y; //y coordinate
    boolean isPlane;
    
    ImageIcon icon = null;
    
    public static final int COMMAND_SIZE = 50;
    
    public Command(int xCord, int yCord, boolean IsPlane, String color) {
        /*
         * if (color.equals("red")) { icon = new ImageIcon("src/red.PNG"); } else if
         * (color.equals("green")) { icon = new ImageIcon("src/green.PNG"); } else if
         * (color.equals("black")) { icon = new ImageIcon("src/black.PNG"); } else {
         * icon = new ImageIcon("src/blue.PNG"); }
         */
        
        this.x = xCord;
        this.y = yCord;
        this.isPlane = IsPlane;
        this.color = color;
        
        if (color.equals("red")) {
            this.RGBR=255;
            this.RGBG=0;
            this.RGBB=0;
       }
        else if (color.equals("blue")) {
            this.RGBR=0;
            this.RGBG=0;
            this.RGBB=255;
        }
        else if (color.equals("black")) {
            this.RGBR=0;
            this.RGBG=0;
            this.RGBB=0;
        }
        else if (color.equals("green")){
            this.RGBR=0;
            this.RGBG=255;
            this.RGBB=0;
        }
        else {
            this.RGBR=255;
            this.RGBG=255;
            this.RGBB=255;
        }
        
     Color c = new Color(RGBR, RGBG, RGBB);
     this.setBackground(c);
     this.repaint();
     this.setRolloverEnabled(false);
     next = null;
    }
    
    public ImageIcon returnICon() {
       return icon;
    }
    
  //  public void draw(Graphics g, int x, int y) {
    //    Color c = new Color(RGBR, RGBG, RGBB);
      //  g.setColor(c);
    //    g.fillRect(x, y, Command.COMMAND_SIZE, Command.COMMAND_SIZE);
  //  }
    
    public boolean checkPlane() {
        return isPlane;
    }
    
    public void changeState() {
        if(isPlane) {
            this.setIcon(null);
        }
        isPlane = !isPlane;
    }
    
    public String getColor() {
        return color;
    }
    
    public void setNext(Command c) {
        this.next = c;
    }
    
    public void setTurn(Command c) {
        this.turn = c;
    }
    
    public Command returnNext() {
        return next;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public String returnType() {
        return "Command";
    }
    
    public abstract void action(Plane a);
    
    public String returnStep() {
        return "none";
    }
    
    public void setSteps(int x) {
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
}

