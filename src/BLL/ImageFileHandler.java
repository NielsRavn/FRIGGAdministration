/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import DAL.ConfFile;
import DAL.FileHandler;
import java.io.File;
import java.io.IOException;

/**
 *
 * @author Niels Kristian Ravn
 */
public class ImageFileHandler {

    public String copyFileToDefaultDirectory(File f) throws IOException {
        String destDirectory = ConfFile.getInstance().getResourceFolderPath();
        String name = f.getName();
        File newLocation =  new File(destDirectory + name);
        System.out.println("" + newLocation.getPath());
        FileHandler.copyFile(f, newLocation);
        return newLocation.getPath();
    }
    
    
    
    
}
