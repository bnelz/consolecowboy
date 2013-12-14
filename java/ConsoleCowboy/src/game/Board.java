/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Brandon
 */
public class Board extends JPanel{
    
    // Always play with an 8x8 board
    public final int width = 8;
    public final int height = 8;
    private Tile[] tiles;
    
    
    // This is a board state to track whether the user has a tile selected
    public boolean tileSelected;
    private Tile selectedTile;
            
    public Board() {
        this.tiles = new Tile[width*height];
        this.tileSelected = false;
        populateBoard();
    }

    private void populateBoard() {
        
        for(int i = 0; i < tiles.length; i++){
            try { 
                tiles[i] = new Tile(i%width,i/height,new GamePiece(), this);
                tiles[i].setBackground(Color.BLACK);
            } catch (IOException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public void remove(int x, int y){
        tiles[x * width + y].setGamePiece(null);
    }
    public void removeMultiple(ArrayList<Tile> tilesToRemove){
        if(!tilesToRemove.isEmpty()){
            for(int i = 0; i < tilesToRemove.size(); i++){
                int x = tilesToRemove.get(i).x;
                int y = tilesToRemove.get(i).y;
                tiles[x * width + y].setGamePiece(new GamePiece(null, "blank"));
            }
        }
    }
    
    public void swap(Tile selectedPiece, Tile targetPiece){
        
        /*
        @TODO
        Clean up this method, the code here is disgusting and a total hack
        */
        if(validSwap(selectedPiece, targetPiece)){
            int sx = selectedPiece.x;
            int sy = selectedPiece.y;
            int spos = sy * width + sx;
            GamePiece spiece = tiles[spos].getGamePiece();

            int tx = targetPiece.x;
            int ty = targetPiece.y;
            int tpos = ty * width + tx;
            GamePiece tpiece = tiles[tpos].getGamePiece();

            tiles[spos].setGamePiece(tpiece);
            tiles[tpos].setGamePiece(spiece);

            deSelectTile(sx,sy);
            deSelectTile(tx, ty);
            tileSelected = false;
            tiles[spos].setIsSelected(false);
            tiles[tpos].setIsSelected(false);
            //detectMatches();
            repaint();
            
        }else{
            
        }
        
        
    }
    public boolean validSwap(Tile origin, Tile target){
        int ox = origin.x;
        int oy = origin.y;
        
        int tx = target.x;
        int ty = target.y;
        
        //Check adjacency here
        if((tx == ox-1 || tx == ox + 1) && oy == ty){ // Check left and right
            return true;
        }else if((ty == oy-1 || ty == oy+1) && ox == tx){ // Check up and down
            return true;
        }else{ // Invalid swap
            return false;
        }
    }
    public void selectTile(int x, int y){
        int pos = y * width + x;
        selectedTile = tiles[pos];
        tiles[pos].setBackground(Color.GREEN);
    }
    public void deSelectTile(int x, int y){
        int pos = y * width + x;
        selectedTile = null;
        tiles[pos].setBackground(Color.BLACK);
    }
    /*
    public void detectMatches(){
        ArrayList<Tile> matches = new ArrayList<>();
        matches.addAll(checkVerticalMatches());
        matches.addAll(checkHorizontalMatches());
        removeMultiple(matches);
    }
    */
    public ArrayList<Tile> checkVerticalMatches(){
        // Check vertical matches
        ArrayList<Tile> matches = new ArrayList<>();
        for(int i = 0; i < tiles.length; i++){
            Tile origin = tiles[i];
            if(origin.y > 5){
                break;
            }else{
                String originType = origin.getGamePiece().getPieceType();
                Tile a = tiles[i+8];
                String aType = a.getGamePiece().getPieceType();
                Tile b = tiles[i+16];
                String bType = b.getGamePiece().getPieceType();
                Tile c = tiles[i+24];
                String cType = c.getGamePiece().getPieceType();
                //Tile d = tiles[i+32];
                // String dType = d.getGamePiece().getPieceType();
                /*
                if(originType.equals(aType) && aType.equals(bType) && bType.equals(cType) && cType.equals(dType)){
                    Tile[] hack = new Tile[]{origin,a,b,c,d};
                    removeMultiple(hack);
                */
                if(originType.equals(aType) && aType.equals(bType) && bType.equals(cType)){
                    matches.add(origin);
                    matches.add(a);
                    matches.add(b);
                    matches.add(c);
                   
                }else if(originType.equals(aType) && aType.equals(bType)){
                    matches.add(origin);
                    matches.add(a);
                    matches.add(b);
                }else{
                    
                }
            }
            
        }
        return matches;
    }
    
    public ArrayList<Tile> checkHorizontalMatches(){
        // Check vertical matches
        ArrayList<Tile> matches = new ArrayList<>();
        for(int i = 0; i < tiles.length; i++){
            Tile origin = tiles[i];
            if(origin.x > 5){
                break;
            }else{
                String originType = origin.getGamePiece().getPieceType();
                Tile a = tiles[i+1];
                String aType = a.getGamePiece().getPieceType();
                Tile b = tiles[i+2];
                String bType = b.getGamePiece().getPieceType();
                Tile c = tiles[i+3];
                String cType = c.getGamePiece().getPieceType();
                //Tile d = tiles[i+32];
                // String dType = d.getGamePiece().getPieceType();
                /*
                if(originType.equals(aType) && aType.equals(bType) && bType.equals(cType) && cType.equals(dType)){
                    Tile[] hack = new Tile[]{origin,a,b,c,d};
                    removeMultiple(hack);
                */
                if(originType.equals(aType) && aType.equals(bType) && bType.equals(cType)){
                    matches.add(origin);
                    matches.add(a);
                    matches.add(b);
                    matches.add(c);
                   
                }else if(originType.equals(aType) && aType.equals(bType)){
                    matches.add(origin);
                    matches.add(a);
                    matches.add(b);
                }else{
                    
                }
            }
            
        }
        return matches;
    }

    public boolean isTileSelected() {
        return tileSelected;
    }

    public void setTileSelected(boolean tileSelected) {
        this.tileSelected = tileSelected;
    }
    public Tile getTileAt(int x, int y){
        Tile tile = tiles[y * width + x];
        return tile;
    }
    public int getWidth() {
        return width;
    }

    public Tile getSelectedTile() {
        return selectedTile;
    }

    public void setSelectedTile(Tile selectedTile) {
        this.selectedTile = selectedTile;
    }

    public int getHeight() {
        return height;
    }

    public Tile[] getTiles() {
        return tiles;
    }

    
    
    
            
}
