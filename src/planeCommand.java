import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;


public class planeCommand extends Command{
    
    public planeCommand(int x, int y, boolean isPlane, String color, ArrayList<Player> players)
    {
        super(x, y, isPlane, color);
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (isPlane) {
                    Plane target = null;
                    for (Player p : players) {
                        for (Plane n : p.getPlanes()) {
                            if (n.currentX == x && n.currentY == y) {
                                target = n;
                            }
                        }
                    }
                    action(target);
                }
                
            }
        });

    }
    
    public void action(Plane a) {
        
    }
    
    public String returnType() {
        return "plane";
    }
    
  //  @Override
  //  public void draw(Graphics g, int x, int y) {
    //    if (hasPlane) {
      //      g.drawImage(image, x, y, Command.COMMAND_SIZE, Command.COMMAND_SIZE, null);
 //       } else {
   //         super.draw(g, x, y);
     //   }
  //  }
    
}