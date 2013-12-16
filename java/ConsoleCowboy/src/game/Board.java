/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 * Manages a matrix of tile objects that acts as the game board
 * Handles board setup and game logic
 * 
 * @author 
 */
public class Board extends JPanel {

    // Always play with an 8x8 board
    public final int width = 8;
    public final int height = 8;
    private Tile[] tiles;
    private Player player;
    private boolean isSetup = false; // Tracks initial setup

    // This is a board state to track whether the user has a tile selected
    public boolean tileSelected;
    private Tile selectedTile;

    public Board() {
        this.tiles = new Tile[width * height];
        this.tileSelected = false;
        this.player = new Player("Dave");
        populateBoard();
    }

    /**
     * Populates the board for initial setup
     * Trashes the board until one is generated with no matches present
     */
    private void populateBoard() {

        for (int i = 0; i < tiles.length; i++) {
            try {
                tiles[i] = new Tile(i % width, i / height, new GamePiece(), this);
                tiles[i].setBackground(Color.BLACK);
            } catch (IOException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        boolean hasMatches = true;
        while (hasMatches) {
            hasMatches = detectMatches();
        }
        isSetup = true;
    }
    
    /**
     * Populates empty tiles with a new random game piece
     * @return Returns true if there were empty tiles to populate
     */
    private boolean populateEmptyTiles() {
        boolean populated = false;
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].getGamePiece().getPieceType().equals("blank")) {
                try {
                    tiles[i].setGamePiece(new GamePiece());
                    tiles[i].setBackground(Color.BLACK);
                } catch (IOException ex) {
                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                }

                populated = true;
            } else {
                
            }
        }
        if(detectMatches()) {
            populateEmptyTiles();
        }
        return populated;
    }
    
    /**
     * Removes an individual tile from the game board
     * @param x coordinate of tile to remove
     * @param y coordinate of tile to remove
     */
    public void remove(int x, int y) {
        tiles[x * width + y].setGamePiece(null);
    }
    
    /**
     * Removes a set of tiles from the game board
     * @param tilesToRemove list of tiles to remove
     */
    public void removeMultiple(ArrayList<Tile> tilesToRemove) {
        if (!tilesToRemove.isEmpty()) {
            for (int i = 0; i < tilesToRemove.size(); i++) {
                int x = tilesToRemove.get(i).x;
                int y = tilesToRemove.get(i).y;
                tiles[y * width + x].setGamePiece(new GamePiece(null, "blank"));
            }
        }
    }
    /**
     * Iterates through each column of the board and swaps until the base case
     * 
     * @param count Tracks the recursion
     */
    private void dropTiles() {
        boolean tilesDropped = false;
        for (int i = tiles.length - 1; i >= 8; i--) {
            Tile tileToCheck = tiles[i];
            
            if (tileToCheck.getGamePiece().getPieceType().equals("blank")) {
                if(!(tiles[i].getGamePiece().getPieceType().equals("blank") &&
                        tiles[i-8].getGamePiece().getPieceType().equals("blank"))){
                    swap(tiles[i], tiles[i - 8]);
                    repaint();
                    
                    tilesDropped = true;
                }
            }
        }
        if (tilesDropped) {
            dropTiles();
        }
    }
    /**
     * Swaps two tiles and then checks if the swap created a match
     * Removes focus from the swapped tiles
     * @param selectedPiece tile in focus
     * @param targetPiece tile to swap with
     */
    public void swap(Tile selectedPiece, Tile targetPiece) {

        /*
         @TODO
         Clean up this method, the code here is disgusting and a total hack
         */
        if (validSwap(selectedPiece, targetPiece)) {
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

            deSelectTile(sx, sy);
            deSelectTile(tx, ty);
            tileSelected = false;
            tiles[spos].setIsSelected(false);
            tiles[tpos].setIsSelected(false);
            detectMatches();
            repaint();

        }
    }
    /**
     * Checks adjacency in the matrix and validates the swap
     * @param origin tile in focus to begin check
     * @param target tile attempting to be swapped
     * @return 
     */
    public boolean validSwap(Tile origin, Tile target) {
        int ox = origin.x;
        int oy = origin.y;

        int tx = target.x;
        int ty = target.y;

        //Check adjacency here
        if ((tx == ox - 1 || tx == ox + 1) && oy == ty) { // Check left and right
            return true;
        } else if ((ty == oy - 1 || ty == oy + 1) && ox == tx) { // Check up and down
            return true;
        } else if (origin.getGamePiece().getPieceType().equals("blank") && target.getGamePiece().getPieceType().equals("blank")) { // Invalid swap
            return false;
        } else {
            return false;
        }
    }
    /**
     * Puts the selected tile in focus and provides user feedback
     * @param x coordinate of tile to select
     * @param y coordinate of tile to select
     */
    public void selectTile(int x, int y) {
        int pos = y * width + x;
        selectedTile = tiles[pos];
        tiles[pos].setBackground(Color.GREEN);
    }
    /**
     * Removes the selected tile from focus and provides user feedback
     * @param x coordinate of tile to deselect
     * @param y coordinate of tile to deselect
     */
    public void deSelectTile(int x, int y) {
        int pos = y * width + x;
        selectedTile = null;
        tiles[pos].setBackground(Color.BLACK);
    }
    /**
     * Maintains a list of the total set of valid matches horizontally and
     * vertically and populates the board when finished
     * @return true if matches are found
     */
    public boolean detectMatches() {
        boolean hasMatches;
        ArrayList<Tile> matches = new ArrayList<>();
        matches.addAll(checkVerticalMatches());
        matches.addAll(checkHorizontalMatches());
        if (matches.isEmpty()) {
            hasMatches = false;
            return hasMatches;
        } else {
            hasMatches = true;
            removeMultiple(matches);
            
            if (isSetup) {
                dropTiles();
                populateEmptyTiles();
            }
            populateEmptyTiles();
            return hasMatches;
        }
    }
    /**
     * Iterates through column 0-5 and checks for sets of 3 or 4 matching tiles
     * @return list of valid matches
     */
    public ArrayList<Tile> checkVerticalMatches() {
        // Check vertical matches
        ArrayList<Tile> matches = new ArrayList<>();
        for (int i = 0; i < tiles.length; i++) {
            Tile origin = tiles[i];
            if (origin.getGamePiece().getPieceType().equals("blank")) {

            } else {
                if (origin.y > 5) {
                    break;
                } else {
                    if (origin.y < 5) {
                        String originType = origin.getGamePiece().getPieceType();
                        Tile a = tiles[i + 8];
                        String aType = a.getGamePiece().getPieceType();
                        Tile b = tiles[i + 16];
                        String bType = b.getGamePiece().getPieceType();
                        Tile c = tiles[i + 24];
                        String cType = c.getGamePiece().getPieceType();
                        if (originType.equals(aType) && aType.equals(bType) && bType.equals(cType)) {
                            matches.add(origin);
                            matches.add(a);
                            matches.add(b);
                            matches.add(c);

                        } else if (originType.equals(aType) && aType.equals(bType)) {
                            matches.add(origin);
                            matches.add(a);
                            matches.add(b);
                        } else {

                        }
                    } else {
                        String originType = origin.getGamePiece().getPieceType();
                        Tile a = tiles[i + 8];
                        String aType = a.getGamePiece().getPieceType();
                        Tile b = tiles[i + 16];
                        String bType = b.getGamePiece().getPieceType();

                        if (originType.equals(aType) && aType.equals(bType)) {
                            matches.add(origin);
                            matches.add(a);
                            matches.add(b);
                        } else {

                        }
                    }

                }
            }

        }
        return matches;
    }
    /**
     * Iterates through rows 0-5 and checks for sets of 3 or 4 valid matches
     * @return list of valid matches
     */
    public ArrayList<Tile> checkHorizontalMatches() {
        // Check horizontal matches
        ArrayList<Tile> matches = new ArrayList<>();

        for (int i = 0; i < 57; i += 8) {
            for (int j = 0; j <= 5; j++) {
                int position = i + j;
                Tile origin = tiles[position];
                if (!(origin.getGamePiece().getPieceType().equals("blank"))) {
                    if (origin.x > 5) {
                        break;
                    } else if (origin.x < 5) {
                        String originType = origin.getGamePiece().getPieceType();
                        Tile a = tiles[position + 1];
                        String aType = a.getGamePiece().getPieceType();
                        Tile b = tiles[position + 2];
                        String bType = b.getGamePiece().getPieceType();
                        Tile c = tiles[position + 3];
                        String cType = c.getGamePiece().getPieceType();

                        if (originType.equals(aType) && aType.equals(bType) && bType.equals(cType)) {
                            matches.add(origin);
                            matches.add(a);
                            matches.add(b);
                            matches.add(c);

                        } else if (originType.equals(aType) && aType.equals(bType)) {
                            matches.add(origin);
                            matches.add(a);
                            matches.add(b);
                        }
                    } else {
                        String originType = origin.getGamePiece().getPieceType();
                        Tile a = tiles[position + 1];
                        String aType = a.getGamePiece().getPieceType();
                        Tile b = tiles[position + 2];
                        String bType = b.getGamePiece().getPieceType();

                        if (originType.equals(aType) && aType.equals(bType)) {
                            matches.add(origin);
                            matches.add(a);
                            matches.add(b);
                        }
                    }
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

    public Tile getTileAt(int x, int y) {
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
