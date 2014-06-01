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

    /**
     * creates a new car with the given elemets.
     *
     * @param carNr the car nr
     * @param iconPath the path where the picture is
     * @param name the name of the car
     * @param seats how many seats the car has.
     */
    public Car(int carNr, String iconPath, String name, int seats) {
        this.carNr = carNr;
        image = new MyImage(iconPath);
        this.name = name;
        this.seats = seats;
    }

    /**
     * creates a new car with only a car nr, the rest will be set to a database
     * save able state
     *
     * @param carNr the car nr of the car.
     */
    public Car(int carNr) {
        this.carNr = carNr;
        this.image = new MyImage("");
        this.name = "";
        this.seats = 0;
    }

    /**
     * @return the carnr of this car
     */
    public int getCarNr() {
        return carNr;
    }

    /**
     * @param carNr the new carnr of this car.
     */
    public void setCarNr(int carNr) {
        this.carNr = carNr;
    }

    /**
     * @return the name/type of vihecle
     */
    public String getName() {
        return name;
    }

    /**
     * @param name sets the name/type of vihecle
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the number of seats on this car
     */
    public int getSeats() {
        return seats;
    }

    /**
     * @param seats sets the number of seats on this car.
     */
    public void setSeats(int seats) {
        this.seats = seats;
    }

    /**
     * @return the Myimage object of this car, which contains both image and
     * image path.
     */
    public MyImage getImage() {
        return image;
    }

    /**
     * @param image sets the myimage of this car.
     */
    public void setImage(MyImage image) {
        this.image = image;
    }

    /**
     * creates a copy of this car object
     *
     * @return a new car object with same values as this one.
     */
    public Car getCopyOfCar() {
        Car c = new Car(carNr, image.getPath(), name, seats);
        return c;
    }

}
