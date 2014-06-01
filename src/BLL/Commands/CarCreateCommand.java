/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BLL.Commands;

import BE.Car;
import DAL.Car_Access;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Niels Kristian Ravn
 */
public class CarCreateCommand implements ICommand {

    Car car;
    Car_Access cal;

    /**
     * creates a new create car command
     *
     * @param car the car to be created
     * @throws IOException
     */
    public CarCreateCommand(Car car) throws IOException {
        cal = new Car_Access();
        this.car = car;
    }

    /**
     * creates the car in the database effectivly going forward
     *
     * @throws SQLException
     */
    @Override
    public void execute() throws SQLException {
        cal.createNewCar(car);
    }

    /**
     * deletes the car from the database effectivly going backwards.
     *
     * @throws SQLException
     */
    @Override
    public void revoke() throws SQLException {
        cal.deleteCar(car);
    }

}
