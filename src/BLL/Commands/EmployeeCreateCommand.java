/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL.Commands;

import BE.Employee;
import BLL.Employee_AccessLink;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Niels Kristian Ravn
 */
public class EmployeeCreateCommand implements ICommand{

    Employee employee;
    Employee_AccessLink eal;
    
    public EmployeeCreateCommand(Employee employee) throws IOException{
        eal = new Employee_AccessLink();
        this.employee = employee;
    }
    
    @Override
    public void excecute() throws SQLException {
        eal.createNewEmployee(employee);
    }

    @Override
    public void revoke() throws SQLException {
        eal.deleteEmployee(employee);
    }
    
}
