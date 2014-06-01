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
    
    /**
     * the command to be executed in the command when going forward
     * @throws SQLException 
     */
    public void execute() throws SQLException;
    
    /**
     * the command to be executed in the command stack when going backwards.
     * @throws SQLException 
     */
    public void revoke() throws SQLException;
    
}
