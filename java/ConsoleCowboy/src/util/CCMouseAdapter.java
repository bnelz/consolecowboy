/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import game.Board;
import game.GamePiece;
import game.Tile;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Brandon
 */
public abstract class CCMouseAdapter extends MouseAdapter {

    private Board board;
    private GamePiece draggedChessPiece;
    private boolean dragging;
    private Rectangle pieceRectangle;
    private Point draggedPieceInitialLocation;
    private Point pressedPoint;

    public CCMouseAdapter() {
        dragging = false;
        draggedPieceInitialLocation = new Point();
        pressedPoint = new Point();
    }

    public Point getDraggedPieceLocation() {
        return new Point(pieceRectangle.x, pieceRectangle.y);
    }

    public GamePiece getDraggedPiece() {
        return draggedChessPiece;
    }

    @Override
    public void mousePressed(MouseEvent me) {
        pressedPoint = me.getPoint();
        List<Tile> tiles = Arrays.asList(board.getTiles());
        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).getGamePiece()!= null) {
                pieceRectangle = tiles.get(i).getBounds();
                if (pieceRectangle.contains(pressedPoint)) {
                    GamePiece piece = tiles.get(i).getGamePiece();
                    if (chessPieceSelected(piece, tiles.get(i))) {
                        draggedChessPiece = new GamePiece(piece);
                        tiles.get(i).setGamePiece(null);

                        draggedPieceInitialLocation.x = pieceRectangle.x;
                        draggedPieceInitialLocation.y = pieceRectangle.y;

                        dragging = true;
                        board.repaint();
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent me) {

        List<Tile> tiles = Arrays.asList(board.getTiles());
        for (int i = 0; i < tiles.size(); i++) {
            pieceRectangle = tiles.get(i).getBounds();
            if (pieceRectangle.contains(me.getPoint())) {
                if (draggedChessPiece != null) {
                    chessPiecePlaced(draggedChessPiece, tiles.get(i));
                }
            }
        }
        dragging = false;
        draggedChessPiece = null;
        board.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
        dragging = true;
        pieceRectangle.x = me.getX() - (pressedPoint.x - draggedPieceInitialLocation.x);
        pieceRectangle.y = me.getY() - (pressedPoint.y - draggedPieceInitialLocation.y);
        board.repaint();
    }

    boolean isDragging() {
        return dragging;
    }

    abstract boolean chessPieceSelected(GamePiece chessPiece, Tile tile);

    abstract void chessPiecePlaced(GamePiece chessPiece, Tile tile);

    void setChessboard(Board board) {
        this.board = board;
    }
}