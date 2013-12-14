/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Brandon
 */
public class ImageController {
    
    public BufferedImage cmd() throws IOException{
        BufferedImage img = ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\cmd50.png"));
        return img;
    }
    public BufferedImage netscape() throws IOException{
        BufferedImage img = ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\netscape50.png"));
        return img;
    }
    public BufferedImage java() throws IOException{
        BufferedImage img = ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\java50.png"));
        return img;
    }
    public BufferedImage aol() throws IOException{
        BufferedImage img = ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\aol50.png"));
        return img;
    }
    public BufferedImage floppy() throws IOException{
        BufferedImage img = ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\floppy50.png"));
        return img;
    }
    public BufferedImage cd() throws IOException{
        BufferedImage img = ImageIO.read(new File("C:\\Users\\Brandon\\Documents\\NetBeansProjects\\ConsoleCowboy\\src\\assets\\cd50.png"));
        return img;
    }
}
