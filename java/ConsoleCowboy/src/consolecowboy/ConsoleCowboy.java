/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package consolecowboy;

import game.Board;
import game.Tile;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author Brandon
 */
public class ConsoleCowboy extends JFrame implements ActionListener{

    /**
     * @param args the command line arguments
     */
    private Board board;
    
    public ConsoleCowboy(){
        board = new Board();
        init();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        GridLayout grid = new GridLayout(board.getWidth(), board.getHeight());
        setLayout(grid);
        
        Tile[] tiles = board.getTiles();
        
        for(int i = 0; i < tiles.length; i++){
            add(tiles[i]);
        }
        
        pack();
        repaint();
        
    }
    public static void main(String[] args){
        ConsoleCowboy session = new ConsoleCowboy();
    }
    
}
