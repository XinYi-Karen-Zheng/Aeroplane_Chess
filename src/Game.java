import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 * Game Main class that specifies the frame and widgets of the GUI
 */
public class Game implements Runnable {
    public void run() {

        String inputValue = JOptionPane.showInputDialog("Choose your map\n" + "choices are:\n" + 
                                    "map 1\n" + "map 2\n" + "map 3\n" + "saved map\n");
        inputValue = "src/" + inputValue + ".txt";
        
        // Top-level frame in which game components live
        // Be sure to change "TOP LEVEL FRAME" to the name of your game
        final JFrame frame = new JFrame("Aeroplane Chess");
        frame.setLocation(300, 300);

        // Main playing area
        final GameBoard board = new GameBoard("src/map_1.txt");
        frame.add(board, BorderLayout.CENTER);
        
        
        // Current user, Dice
        Box box = Box.createVerticalBox();
        
        Box box1 = Box.createHorizontalBox();
        final JLabel status = new JLabel("current player: " + board.getCurrentPlayer().getID()); 
        final JButton next = new JButton("next");
        next.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.updatePlayer();
                status.setText("current player: " + board.getCurrentPlayer().getID());
            }
        });
        box1.add(status);
        box1.add(next);
        
        Box box2 = Box.createHorizontalBox();
        // Dice ---  change the relative position of status panel and dice
        final Dice userDice = new Dice();
        final JLabel diceResult = new JLabel(Integer.toString(userDice.returnCurrent()));
        final JButton button = new JButton("Dice");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int x = userDice.newValue();
                diceResult.setText(Integer.toString(userDice.returnCurrent()));
                board.handle(x);
           }
        });
        box2.add(diceResult);
        box2.add(button);
        
        box.add(box1);
        box.add(box2);
        frame.add(box, BorderLayout.SOUTH);
        

        // control_panel: reset and save
        Box control_panel = Box.createHorizontalBox();
        frame.add(control_panel, BorderLayout.NORTH);
        // reset button
        // Note here that when we add an action listener to the reset button, we define it as an
        // anonymous inner class that is an instance of ActionListener with its actionPerformed()
        // method overridden. When the button is pressed, actionPerformed() will be called.
        final JButton reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                board.reset();
                userDice.reset();
                status.setText("current player: " + board.getCurrentPlayer().getID());
                diceResult.setText(Integer.toString(userDice.returnCurrent()));
            }
        });
        control_panel.add(reset);
        // save button
        final JButton save = new JButton("save");
        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    board.writeMap();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        control_panel.add(save);

        
        // Put the frame on the screen
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Start game
        board.reset();
    }

    /**
     * Main method run to start and run the game. Initializes the GUI elements specified in Game and
     * runs it. IMPORTANT: Do NOT delete! You MUST include this in your final submission.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Game());
        
    }
    
    public class Dice{
        private int current = 0;
        
        public int returnCurrent() {
            return current;
        }
        
        public int newValue() {
            current = (int) (Math.random()*6 + 1);
            return current;
        }
        
        public void reset() {
            current = 0;
        }
    }
    
}
