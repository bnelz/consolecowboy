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

    String directory = System.getProperty("user.dir");
    String OS = System.getProperty("os.name").toLowerCase();

    public BufferedImage cmd() throws IOException {
        String fullDirectory = directory + "\\src\\assets\\cmd50.png";
        if (OS.startsWith("mac")) {
            fullDirectory = directory + "/src/assets/cmd50.png";
        }
        BufferedImage img = ImageIO.read(new File(fullDirectory));
        return img;
    }

    public BufferedImage netscape() throws IOException {
        String fullDirectory = directory + "\\src\\assets\\netscape50.png";
        if (OS.startsWith("mac")) {
            fullDirectory = directory + "/src/assets/netscape50.png";
        }
        BufferedImage img = ImageIO.read(new File(fullDirectory));
        return img;
    }

    public BufferedImage java() throws IOException {
        String fullDirectory = directory + "\\src\\assets\\java50.png";
        if (OS.startsWith("mac")) {
            fullDirectory = directory + "/src/assets/java50.png";
        }
        BufferedImage img = ImageIO.read(new File(fullDirectory));
        return img;
    }

    public BufferedImage aol() throws IOException {
        String fullDirectory = directory + "\\src\\assets\\aol50.png";
        if (OS.startsWith("mac")) {
            fullDirectory = directory + "/src/assets/aol50.png";
        }
        BufferedImage img = ImageIO.read(new File(fullDirectory));
        return img;
    }

    public BufferedImage floppy() throws IOException {
        String fullDirectory = directory + "\\src\\assets\\floppy50.png";
        if (OS.startsWith("mac")) {
            fullDirectory = directory + "/src/assets/floppy50.png";
        }
        BufferedImage img = ImageIO.read(new File(fullDirectory));
        return img;
    }

    public BufferedImage cd() throws IOException {
        String fullDirectory = directory + "\\src\\assets\\cd50.png";
        if (OS.startsWith("mac")) {
            fullDirectory = directory + "/src/assets/cd50.png";
        }
        BufferedImage img = ImageIO.read(new File(fullDirectory));
        return img;
    }
}
