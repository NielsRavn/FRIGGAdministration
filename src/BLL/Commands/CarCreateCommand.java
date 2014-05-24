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
public class CarCreateCommand implements ICommand{

    Car car;
    Car_Access cal;
    
    public CarCreateCommand(Car car) throws IOException{
        cal = new Car_Access();
        this.car = car;
    }
    
    @Override
    public void excecute() throws SQLException {
        cal.createNewCar(car);
    }

    @Override
    public void revoke() throws SQLException {
        cal.deleteCar(car);
    }
    
}
