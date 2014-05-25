/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

import javax.swing.ImageIcon;

/**
 *
 * @author Niels Kristian Ravn
 */
public class MyImage {
    
    ImageIcon image;
    String path;

    public MyImage(ImageIcon image, String path) {
        this.image = image;
        this.path = path;
    }

    public MyImage(String path) {
        this.path = path;
        image = new ImageIcon(path);
    }
    
    

    public ImageIcon getImage() {
        return image;
    }

    public String getPath() {
        return path;
    }

    /**
     * sets the path of the image, and loads the image in that path
     * @param path the place where the image is located on the hardrive.
     */
    public void setPath(String path) {
        this.path = path;
        image = new ImageIcon(path);
    }
    
    
    
}
