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
public class Car {
    
    int carNr;
    ImageIcon image;
    String iconPath, name;
    int seats;

    public Car(int carNr, String iconPath, String name, int seats) {
        this.carNr = carNr;
        this.iconPath = iconPath;
        this.name = name;
        this.seats = seats;
        image = new ImageIcon(iconPath);
    }

    public Car(int carNr) {
        this.carNr = carNr;
        this.image = new ImageIcon();
        this.iconPath = "";
        this.name = "";
        this.seats = 0;
    }

    public int getCarNr() {
        return carNr;
    }

    public void setCarNr(int carNr) {
        this.carNr = carNr;
    }

    public ImageIcon getImage() {
        return image;
    }

    public void setImage(ImageIcon image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }
    
    public Car getCopyOfCar(){
        Car c = new Car(carNr, iconPath, name, seats);
        return c;
    }
    
}
