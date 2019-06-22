public class noneCommand extends Command{
    
    public noneCommand(int x, int y, boolean isPlane, String color)
    {
        super(x, y, isPlane, color);
    }
    
    public void action(Plane a) {
        
    }
    
    public void actionPerformed() {
        
    }
    
    public String returnType() {
        return "none";
    }
}