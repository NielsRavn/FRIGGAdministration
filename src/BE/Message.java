/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BE;

/**
 *
 * @author Niels Kristian Ravn
 */
public class Message {
    
    private int id;
    private String message;
    private boolean shown;

    /**
     * creates a new message with all data, used when getting data from the database.
     * @param id the id of the message
     * @param message the message
     * @param shown if the message should be shown or not.
     */
    public Message(int id, String message, boolean shown) {
        this.id = id;
        this.message = message;
        this.shown = shown;
    }

    /**
     * creates a new message from message and shown value, used when creating new messages.
     * @param message the message to be shown
     * @param shown whether or not it should be shown.
     */
    public Message(String message, boolean shown) {
        this.message = message;
        this.shown = shown;
    }

    /**
     * @return the id of the message
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the new id of the message to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the message to be shown.
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the new message to be set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return whether or nor the message should be shown. when true it is visible.
     */
    public boolean isShown() {
        return shown;
    }

    /**
     * @param shown the new value for shown to be set, when true it is visible.
     */
    public void setShown(boolean shown) {
        this.shown = shown;
    }
    
    
}
