/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package BLL;

import BE.Alarm;
import DAL.Alarm_Access;

/**
 *
 * @author Susanne
 */
public class Alarm_AccessLink {
    Alarm_Access aa;
    
    public Alarm_AccessLink() {
        aa = new Alarm_Access();
    }
    
    public Alarm getAlarmByDate (int date) {
        return aa.getAlarmByDate(date);
    }
}
