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
 * @author Niels Kristian Ravn
 */
public class EmployeeCreateCommand implements ICommand{

    Employee employee;
    Employee_Access eal;
    
    /**
     * creates a new command to create an employee
     * @param employee the employee to create
     * @throws IOException 
     */
    public EmployeeCreateCommand(Employee employee) throws IOException{
        eal = new Employee_Access();
        this.employee = employee;
    }
    
    /**
     * creates the employee in the database effectivly going forward.
     * @throws SQLException 
     */
    @Override
    public void excecute() throws SQLException {
        eal.createNewEmployee(employee);
    }

    /**
     * deletes the new employee from the database effectivly going backwards
     * @throws SQLException 
     */
    @Override
    public void revoke() throws SQLException {
        eal.deleteEmployee(employee);
    }
    
}
