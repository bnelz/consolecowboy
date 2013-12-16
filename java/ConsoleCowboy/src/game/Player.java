/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game;

/**
 *
 * @author Brandon
 */
public class Player {
    
    private String name;
    private int score;
    private int bestCombo;
    private int movesRemaining;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.bestCombo = 0;
        this.movesRemaining = 50;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getBestCombo() {
        return bestCombo;
    }

    public void setBestCombo(int bestCombo) {
        this.bestCombo = bestCombo;
    }

    public int getMovesRemaining() {
        return movesRemaining;
    }

    public void setMovesRemaining(int movesRemaining) {
        this.movesRemaining = movesRemaining;
    }
    
    
}
