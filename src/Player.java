import java.util.ArrayList;

public class Player{
    String color;
    ArrayList<Plane> Planes;
    Player next;
    
    public Player(String Color, Player next) {
        this.color = Color;
        Planes = new ArrayList<Plane>();
        
        this.next = next;
    }   
    
    public void addPlane(int x, int y) {
        Planes.add(new Plane(color, x, y, this));
    }
    
    public int returnNumPlane() {
        return Planes.size();
    }
    
    public String getID() {
        if (color.equals("red"))
        {
            return "1";
        }
        else if (color.equals("blue")) {
            return "2";
        }
        else if (color.equals("black")) {
            return "3";
        }
        else {
            return "4";
        }
    }
    
    public Player next() {
        return next;
    }
    
    public void setNext(Player n) {
        this.next = n;
    }
    
    public String getColor() {
        return color;
    }
    
    public ArrayList<Plane> getPlanes(){
        ArrayList<Plane> copy = new ArrayList<> (Planes);
        return copy;
    }
}