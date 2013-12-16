/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import game.Board;
import game.Player;
import game.Tile;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import util.ImageController;

/**
 * The main game frame that displays the match 3 board
 *
 * @author
 */
public class ConsoleCowboy extends JFrame implements ActionListener {

    /**
     * @param args the command line arguments
     */
    private Board board;
    private Player player;
    private ImageController ic;

    public ConsoleCowboy() {
        setPreferredSize(new Dimension(500, 500));
        board = new Board();
        setResizable(false);
        player = board.getPlayer();
        ic = new ImageController();
        init();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Sets up the play area and adds the tiles to the Grid Layout
     */
    private void init() {
        JPanel playerPane = new JPanel();
        JPanel gamePane = new JPanel();
        JLabel background = new JLabel();
        try {
            background.setIcon(new ImageIcon(ic.background()));
        } catch (IOException ex) {
            Logger.getLogger(ConsoleCowboy.class.getName()).log(Level.SEVERE, null, ex);
        }
        playerPane.setPreferredSize(new Dimension(500,75));
        playerPane.add(background);
        setLayout(new BorderLayout());
        JLabel nameLabel = new JLabel("Name", JLabel.CENTER);
        JLabel playerName = new JLabel("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        GridLayout grid = new GridLayout(board.getWidth(), board.getHeight());
        gamePane.setLayout(grid);
        add(gamePane, BorderLayout.SOUTH);
        add(playerPane, BorderLayout.NORTH);

        Tile[] tiles = board.getTiles();

        for (int i = 0; i < tiles.length; i++) {
            gamePane.add(tiles[i]);
        }
        playerPane.repaint();
        pack();
        repaint();

    }

}
