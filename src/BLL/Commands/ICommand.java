/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL.Commands;

import java.sql.SQLException;

/**
 *
 * @author Niels
 */
public interface ICommand {
    
    public void execute() throws SQLException;
    
    public void revoke() throws SQLException;
    
}
