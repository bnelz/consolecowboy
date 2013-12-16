/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;

/**
 * Manages image IO on the user machine based on OS type
 *
 * @author
 */
public class ImageController {

    String directory = System.getProperty("user.dir");
    String OS = System.getProperty("os.name").toLowerCase();

    public Image background() throws IOException {

        String fullDirectory = directory + "\\src\\assets\\background.png";
        URL imageURL = getClass().getResource("/assets/background.png");
        Image img = Toolkit.getDefaultToolkit().getImage(imageURL);
        return img;
    }

    public Image cmd() throws IOException {
        URL imageURL = getClass().getResource("/assets/cmd50.png");
        Image img = Toolkit.getDefaultToolkit().getImage(imageURL);
        return img;
    }

    public Image netscape() throws IOException {
        URL imageURL = getClass().getResource("/assets/netscape50.png");
        Image img = Toolkit.getDefaultToolkit().getImage(imageURL);
        return img;
    }

    public Image java() throws IOException {
        URL imageURL = getClass().getResource("/assets/java50.png");
        Image img = Toolkit.getDefaultToolkit().getImage(imageURL);
        return img;
    }

    public Image aol() throws IOException {
        URL imageURL = getClass().getResource("/assets/aol50.png");
        Image img = Toolkit.getDefaultToolkit().getImage(imageURL);
        return img;
    }

    public Image floppy() throws IOException {
        URL imageURL = getClass().getResource("/assets/floppy50.png");
        Image img = Toolkit.getDefaultToolkit().getImage(imageURL);
        return img;
    }

    public Image cd() throws IOException {

        URL imageURL = getClass().getResource("/assets/cd50.png");
        Image img = Toolkit.getDefaultToolkit().getImage(imageURL);
        return img;
    }
}
