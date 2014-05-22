/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL.Commands;

import java.sql.SQLException;
import java.util.Stack;

/**
 *
 * @author Niels Kristian Ravn
 */
public class CommandStack {

    Stack<ICommand> backwards, forward;
    
    public CommandStack(){
        backwards = new Stack<>();
        forward = new Stack<>();
    }
    
    public void goBack() throws SQLException{
        ICommand com = backwards.pop();
        com.revoke();
        forward.push(com);
    }
    
    public void goForward() throws SQLException{
        ICommand com = forward.pop();
        com.excecute();
        backwards.push(com);
    }
    
    public void addCommandToStack(ICommand com) throws SQLException{
        com.excecute();
        backwards.push(com);
        forward.clear();
    }
    
    public boolean canGoForward(){
        return forward.size() > 0;
    }
    
    public boolean canGoBackwards(){
        return backwards.size() > 0;
    }
}
