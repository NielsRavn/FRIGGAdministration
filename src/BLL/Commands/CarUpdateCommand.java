/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL.Commands;

import BE.Car;
import BE.Employee;
import DAL.Car_Access;
import DAL.Employee_Access;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Niels
 */
public class CarUpdateCommand implements ICommand{

    Car newCar, oldCar;
    Car_Access ca;
    
    public CarUpdateCommand(Car newCar, Car oldCar) throws IOException{
        ca = new Car_Access();
        this.newCar = newCar;
        this.oldCar = oldCar;
    }
    
    @Override
    public void excecute() throws SQLException {
        ca.updateCar(newCar);
    }

    @Override
    public void revoke() throws SQLException {
        ca.updateCar(oldCar);
    }
    
}
