/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;

/**
 *
 * @author Brandon
 */
public class Tile extends javax.swing.JButton implements MouseListener {

    public int x;
    public int y;
    public int pos;
    private GamePiece gamePiece;
    private Board board;
    private boolean isSelected;

    // 0 for inactive, 1 for selected
    private int state;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
        pos = y * board.width + x;
        state = 0;
        this.isSelected = false;

        /*
         try {
         chooseRandomTileType();
         } catch (IOException ex) {
         Logger.getLogger(Tile.class.getName()).log(Level.SEVERE, null, ex);
         }
         */
        setPreferredSize(new Dimension(50, 50));
    }

    public Tile(int x, int y, GamePiece gamePiece, Board board) {
        this.x = x;
        this.y = y;
        this.gamePiece = gamePiece;
        this.board = board;
        addMouseListener(this);
        /*
         addMouseListener(new MouseAdapter()
         {

         @Override
         public void mousePressed(MouseEvent e) {
         Tile.board.selectTile(x, y);
         }
         });
         */
        setIcon(new ImageIcon(gamePiece.getImage()));
        setPreferredSize(new Dimension(50, 50));
    }

    public void setGamePiece(GamePiece gamePiece) {
        this.gamePiece = gamePiece;

        if (gamePiece == null) {
            setIcon(null);
        } else {
            if (gamePiece.getImage() == null) {
                setIcon(null);
            } else {
                setIcon(new ImageIcon(gamePiece.getImage()));
            }

        }
    }

    /*
     private void chooseRandomTileType() throws IOException {
     int rand = ((int)(Math.random() * 6));
     switch(rand){
     case 0:
     // cmd
     setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\cmd.png"))));
     break;  
     case 1:
     // CD
     setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\cd.png"))));
     break;
     case 2:
     // Netscape
     setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\netscape.png"))));
     break;
     case 3:
     // Floppy
     setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\floppy.png"))));
     break;
     case 4:
     // AOL
     setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\aol.png"))));
     break;
     case 5:
     // Java
     setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\java.png"))));
     break;
     default:
     // cmd
     setIcon(new ImageIcon(ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\cmd.png"))));
     break;
            
     }
     }
    
     */
    public GamePiece getGamePiece() {
        return gamePiece;
    }

    public int getState() {
        return state;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (board.tileSelected && isSelected) {
            board.deSelectTile(x, y);
            isSelected = false;
            board.setTileSelected(false);
        } else if (board.tileSelected && !isSelected) {
            board.swap(board.getSelectedTile(), this);
        } else {
            board.selectTile(x, y);
            isSelected = true;
            board.setTileSelected(true);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public boolean isIsSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

}
