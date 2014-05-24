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
    
    public EmployeeCreateCommand(Employee employee) throws IOException{
        eal = new Employee_Access();
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
