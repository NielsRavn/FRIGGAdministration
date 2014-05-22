/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL.Commands;

import BE.Employee;
import DAL.Employee_Access;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Niels
 */
public class EmployeeUpdateCommand implements ICommand{

    Employee newEmployee, oldEmployee;
    Employee_Access ea;
    
    public EmployeeUpdateCommand(Employee newEmployee, Employee oldEmployee) throws IOException{
        ea = new Employee_Access();
        this.newEmployee = newEmployee;
        this.oldEmployee = oldEmployee;
    }
    
    @Override
    public void excecute() throws SQLException {
        ea.updateEmployee(newEmployee);
    }

    @Override
    public void revoke() throws SQLException {
        ea.updateEmployee(oldEmployee);
    }
    
}
