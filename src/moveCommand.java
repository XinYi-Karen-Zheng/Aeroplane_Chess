import java.util.ArrayList;

public class moveCommand extends Command{
    private int step;
    
    public moveCommand(int x, int y, boolean isPlane, String color, ArrayList<Player> players)
    {
        super(x, y, isPlane, color);
        step = (int) Math.random()*3 + 1;
    }
    
    public void action(Plane a) {
        a.move(step, this);
    }
    
    public String returnType() {
        return "move";
    }
    
    @Override
    public String returnStep() {
        return Integer.toString(step);
    }
    
    public void actionPerformed() {
        
    }
    
    // for testing purposes
    @Override
    public void setSteps(int x) {
        step=x;
    }
    
}