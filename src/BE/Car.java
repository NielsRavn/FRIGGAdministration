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
    MyImage image;
    String name;
    int seats;

    public Car(int carNr, String iconPath, String name, int seats) {
        this.carNr = carNr;
        image = new MyImage(iconPath);
        this.name = name;
        this.seats = seats;
    }

    public Car(int carNr) {
        this.carNr = carNr;
        this.image = new MyImage("");
        this.name = "";
        this.seats = 0;
    }

    public int getCarNr() {
        return carNr;
    }

    public void setCarNr(int carNr) {
        this.carNr = carNr;
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

    public MyImage getImage() {
        return image;
    }

    public void setImage(MyImage image) {
        this.image = image;
    }
    
    public Car getCopyOfCar(){
        Car c = new Car(carNr, image.getPath(), name, seats);
        return c;
    }
    
}
