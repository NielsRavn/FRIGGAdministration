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
    /**
     * craetes a new stack with a stack of commands to go forward and one to go backwards
     */
    public CommandStack(){
        backwards = new Stack<>();
        forward = new Stack<>();
    }    
    /**
     * goes back poping the top of the backwards stack, calling revoke and putting
     * it on the forward stack.
     * @throws SQLException 
     */
    public void goBack() throws SQLException{
        ICommand com = backwards.pop();
        com.revoke();
        forward.push(com);
    }    
    /**
     * goes forward poping the top of the forwards stack, execute it and
     * put it on the top of the backwards stack.
     * @throws SQLException 
     */
    public void goForward() throws SQLException{
        ICommand com = forward.pop();
        com.execute();
        backwards.push(com);
    }    
    /**
     * adds a new command to the backwars stack and clears the forward stack.
     * the command is excecuted before being pushed on the stack
     * @param com the comman to be added.
     * @throws SQLException 
     */
    public void addCommandToStack(ICommand com) throws SQLException{
        com.execute();
        backwards.push(com);
        forward.clear();
    }  
    
    /**
     * tells whether or not there are item to go forward with
     * @return true if it can go forward
     */
    public boolean canGoForward(){
        return forward.size() > 0;
    }    
    /**
     * tells whether or not there are item to go backwards with
     * @return true if it can go back
     */
    public boolean canGoBackwards(){
        return backwards.size() > 0;
    }
}
