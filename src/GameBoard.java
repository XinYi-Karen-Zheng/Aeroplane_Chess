import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import javax.swing.*;


public class GameBoard extends JPanel {
    private Command[][] map;
    private Player currentPlayer = null;
    private ArrayList<Player> players;

    public GameBoard(String fileName) {
        super();
        
        // initialize planes
        players = new ArrayList<Player>();
        Player four = new Player("green", null);
        Player three = new Player("black", four);
        Player two = new Player("blue", three);
        Player one = new Player("red", two);
        four.setNext(one);
        players.add(one);
        players.add(two);
        players.add(three);
        players.add(four);
                
        currentPlayer = one;
        
        map = fileToMap(fileName);
        setNexts(map);
        this.setLayout(null);
        for (int x = 0; x<map.length; x++) {
            for (int y = 0; y<map.length; y++) {
                map[x][y].setBounds(x*Command.COMMAND_SIZE, y*Command.COMMAND_SIZE, Command.COMMAND_SIZE, Command.COMMAND_SIZE);
                //map[x][y].setIcon(map[x][y].returnICon());
                map[x][y].repaint();
                this.add(map[x][y]);
            }
        }
        addPlanes(map);
        
        this.repaint();
    }
    
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
    
    public void updatePlayer() {
        currentPlayer = currentPlayer.next();
    }

    
    public Command[][] fileToMap(String fileName) {
        Command[][] toReturn = new Command[11][11];
        try {
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            TDCommand.construct();
            for (int y = 0; y < 11; y++) {
                for (int x = 0; x < 11; x++) {
                    String nextLine = in.readLine();
                    if (!nextLine.equals("")) {
                        String[] args = nextLine.split(",");
                        for (int z=0; z<args.length; z++) {
                            args[z] = args[z].trim();
                        }
                        
                        if (args[1].equals("move")) {
                            toReturn[x][y] = new moveCommand(x, y, Boolean.valueOf(args[3]), args[0], players);
                        } else if (args[1].equals("TD")) {
                            toReturn[x][y] = new TDCommand(x, y, Boolean.valueOf(args[3]), args[0], players);
                        } else if (args[1].equals("plane")){
                            toReturn[x][y] = new planeCommand(x, y, Boolean.valueOf(args[3]), args[0], players);
                        } else {
                            toReturn[x][y] = new noneCommand(x, y, false, args[0]);
                        }
                        
                        if (Boolean.valueOf(args[3])) {
                            for (Player p : players) {
                                if (p.getColor().equals(args[4])) {
                                    p.addPlane(x, y);
                                }
                            }
                        }
                    } 
                }
            }
            
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }

        return toReturn;
    }
    
    public Command checkCommand(int x, int y) {
        return map[x][y];
    }

    public void reset() {
        players = new ArrayList<Player>();
        Player four = new Player("green", null);
        Player three = new Player("black", four);
        Player two = new Player("blue", three);
        Player one = new Player("red", two);
        four.setNext(one);
        players.add(one);
        players.add(two);
        players.add(three);
        players.add(four);
                
        currentPlayer = one;
        
        map = fileToMap("src/map_1.txt");
        setNexts(map);
        this.setLayout(null);
        for (int x = 0; x<map.length; x++) {
            for (int y = 0; y<map.length; y++) {
                map[x][y].setBounds(x*Command.COMMAND_SIZE, y*Command.COMMAND_SIZE, Command.COMMAND_SIZE, Command.COMMAND_SIZE);
                //map[x][y].setIcon(map[x][y].returnICon());
                map[x][y].repaint();
                this.add(map[x][y]);
            }
        }
        addPlanes(map);
        
        this.setVisible(true);
        this.repaint();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(11*Command.COMMAND_SIZE, 11*Command.COMMAND_SIZE);
    }

    //@Override
    //protected void paintComponent(Graphics g) {
        // STEP 3: DRAW TILES HERE
      //  for (int x = 0; x < map.length; x++) {
        //    for (int y = 0; y < map.length; y++) {
          //      if(map[x][y] != null) {
            //        map[x][y].draw(g, x * Command.COMMAND_SIZE, y * Command.COMMAND_SIZE);
              //  }
            //}
        //}
    //}
    
    public void handle(int x) {
        
        Plane target = null;
        String toShow = "";
        
        for (Plane p : currentPlayer.getPlanes()) {
            toShow = toShow + "  " + p.printLocation();
        }
        
        String inputValue = JOptionPane.showInputDialog("Choose a plane to move\n" + "choices are:\n" + 
                toShow + "\n" + "enter in the form x,x");
        String[] args = inputValue.split(",");
        int xPosition = Integer.parseInt(args[0].trim());
        int yPosition = Integer.parseInt(args[1].trim());
        for (Plane p : currentPlayer.getPlanes()) {
            if (p.currentX == xPosition && p.currentY == yPosition) {
                target = p;
            }
        }
        
        if(target != null) {
            if(x == 6) {
                target.start(x, map[xPosition][yPosition]);
            }
            else {
                target.move(x, map[xPosition][yPosition]);
            }
        }
        
        if(currentPlayer.getPlanes().size() == 0) {
            JOptionPane.showConfirmDialog(null, "You win!");
        }
        
        this.repaint();
    }
    
    public void setNexts(Command[][] m) {
        m[1][3].setNext(m[2][3]);
        m[2][3].setNext(m[3][3]);
        m[3][3].setNext(m[3][2]);
        m[3][2].setNext(m[3][1]);
        m[3][1].setNext(m[4][1]);
        m[4][1].setNext(m[5][1]);
        m[5][1].setNext(m[6][1]);
        m[6][1].setNext(m[7][1]);
        m[7][1].setNext(m[7][2]);
        m[7][2].setNext(m[7][3]);
        m[7][3].setNext(m[8][3]);
        m[8][3].setNext(m[9][3]);
        m[9][3].setNext(m[9][4]);
        m[9][4].setNext(m[9][5]);
        m[9][5].setNext(m[9][6]);
        m[9][6].setNext(m[9][7]);
        m[9][7].setNext(m[8][7]);
        m[8][7].setNext(m[7][7]);
        m[7][7].setNext(m[7][8]);
        m[7][8].setNext(m[7][9]);
        m[7][9].setNext(m[6][9]);
        m[6][9].setNext(m[5][9]);
        m[5][9].setNext(m[4][9]);
        m[4][9].setNext(m[3][9]);
        m[3][9].setNext(m[3][8]);
        m[3][8].setNext(m[3][7]);
        m[3][7].setNext(m[2][7]);
        m[2][7].setNext(m[1][7]);
        m[1][7].setNext(m[1][6]);
        m[1][6].setNext(m[1][5]);
        m[1][5].setNext(m[1][4]);
        m[1][4].setNext(m[1][3]);
        
        m[2][5].setNext(m[3][5]);
        m[3][5].setNext(m[4][5]);
        m[4][5].setNext(m[5][5]);
        
        m[5][2].setNext(m[5][3]);
        m[5][3].setNext(m[5][4]);
        m[5][4].setNext(m[5][5]);
        
        m[8][5].setNext(m[7][5]);
        m[7][5].setNext(m[6][5]);
        m[6][5].setNext(m[5][5]);
        
        m[5][8].setNext(m[5][7]);
        m[5][7].setNext(m[5][6]);
        m[5][6].setNext(m[5][5]);
        
        m[0][0].setNext(m[1][3]);
        m[0][1].setNext(m[1][3]);
        m[1][0].setNext(m[1][3]);
        m[1][1].setNext(m[1][3]);
        
        m[0][9].setNext(m[3][9]);
        m[0][10].setNext(m[3][9]);
        m[1][9].setNext(m[3][9]);
        m[1][10].setNext(m[3][9]);
        
        m[9][9].setNext(m[9][7]);
        m[9][10].setNext(m[9][7]);
        m[10][9].setNext(m[9][7]);
        m[10][10].setNext(m[9][7]);
        
        m[9][0].setNext(m[7][1]);
        m[10][0].setNext(m[7][1]);
        m[9][1].setNext(m[7][1]);
        m[10][1].setNext(m[7][1]);
        
        m[5][1].setTurn(m[5][2]);
        m[1][5].setTurn(m[2][5]);
        m[5][9].setTurn(m[5][8]);
        m[9][5].setTurn(m[8][5]);
    }
    
    public void addPlanes(Command[][] map) {
        for (Player p : players) {
            for (Plane a : p.getPlanes()) {
                ImageIcon image = new ImageIcon(a.sourceName);
                image.setImage(image.getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT ));
                map[a.currentX][a.currentY].setIcon(image);
            }
        }
    }
    
    public ArrayList<Player> getPlayers(){
        return players;
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int x = 0; x < map.length; x++) {
            for (int y = 0; y < map[x].length; y++)
                map[x][y].repaint();
        }
        
    }
    
    public void writeMap() throws IOException {   
        String fileName = "./src/saved.txt";
        FileWriter output = null;
        BufferedWriter out = null;
        
        try {
            output = new FileWriter(fileName);
            out = new BufferedWriter(output);
        for (int x = 0; x<map.length; x++)
        {
            for (int y=0; y<map.length; y++) {
                String toWrite = "";
                toWrite = toWrite + map[y][x].getColor() + ", " + map[y][x].returnType() + ", " 
                                    + map[y][x].returnStep() + ", " + String.valueOf(map[y][x].isPlane);
                
                if (map[y][x].isPlane) {
                    for (Player p : players) {
                        for (Plane n : p.getPlanes())
                        {
                            if (n.currentX==y && n.currentY==x) {
                                toWrite = toWrite + ", " + p.getColor();
                            }
                        }
                    }
                }
                
                out.write(toWrite);
                out.write("\n");
            }
        }
        out.close();
    } catch (Exception e) {
        e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
