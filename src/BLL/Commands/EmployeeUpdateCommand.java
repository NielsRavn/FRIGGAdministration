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
    
    /**
     * creates a new command to update an employee
     * @param newEmployee the new employee for going forward
     * @param oldEmployee the old employee for going backwards
     * @throws IOException 
     */
    public EmployeeUpdateCommand(Employee newEmployee, Employee oldEmployee) throws IOException{
        ea = new Employee_Access();
        this.newEmployee = newEmployee;
        this.oldEmployee = oldEmployee;
    }
    
    /**
     * updates the employee with the new employee effectively going forward
     * @throws SQLException 
     */
    @Override
    public void execute() throws SQLException {
        ea.updateEmployee(newEmployee);
    }

    /**
     * updates the employee with the old employee effectively going backwards
     * @throws SQLException 
     */
    @Override
    public void revoke() throws SQLException {
        ea.updateEmployee(oldEmployee);
    }
    
}
