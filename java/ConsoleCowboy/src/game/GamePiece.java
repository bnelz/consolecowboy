/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.image.BufferedImage;
import java.io.IOException;
import util.ImageController;

/**
 * Model of a game piece
 * @author Brandon
 */
public class GamePiece {
    
    private BufferedImage image;
    private String pieceType;

    public GamePiece(BufferedImage image, String pieceType) {
        this.image = image;
        this.pieceType = pieceType;
    }
    public GamePiece(GamePiece piece){
        this.image = piece.image;
        this.pieceType = piece.pieceType;
    }
    /**
     * Constructs a GamePiece object with a random image and type representation
     * @throws IOException 
     */
    public GamePiece() throws IOException {
        ImageController ic = new ImageController();
        int rand = ((int)(Math.random() * 6));
        switch(rand){
            case 0:
                image = ic.cmd();
                pieceType = "cmd";
                break;
            case 1:
                image = ic.aol();
                pieceType = "aol";
                break;
            case 2:
                image = ic.cd();
                pieceType = "cd";
                break;
            case 3:
                image = ic.floppy();
                pieceType = "floppy";
                break;
            case 4:
                image = ic.java();
                pieceType = "java";
                break;
            case 5:
                image = ic.netscape();
                pieceType = "netscape";
                break;
            default:
                image = ic.cmd();
                pieceType = "cmd";
                break;      
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public String getPieceType() {
        return pieceType;
    }



    
    
    
}
