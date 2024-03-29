/*
 *  Player Java Client 2 - PlayerPosition2dPositionPidReq.java
 *  Copyright (C) 2006 Radu Bogdan Rusu
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * $Id: PlayerPosition2dPositionPidReq.java,v 1.1.1.1 2006/02/15 17:51:18 veedee Exp $
 *
 */

package javaclient2.structures.position2d;

import javaclient2.structures.*;

/**
 * Request/reply: Set position PID parameters.
 *  
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v2.0 - Player 2.0 supported
 * </ul>
 */
public class PlayerPosition2dPositionPidReq implements PlayerConstants {

    // PID parameters 
    private float kp;
    // PID parameters 
    private float ki;
    // PID parameters 
    private float kd;


    /**
     * @return  PID parameters 
     **/
    public synchronized float getKp () {
        return this.kp;
    }

    /**
     * @param newKp  PID parameters 
     *
     */
    public synchronized void setKp (float newKp) {
        this.kp = newKp;
    }
    /**
     * @return  PID parameters 
     **/
    public synchronized float getKi () {
        return this.ki;
    }

    /**
     * @param newKi  PID parameters 
     *
     */
    public synchronized void setKi (float newKi) {
        this.ki = newKi;
    }
    /**
     * @return  PID parameters 
     **/
    public synchronized float getKd () {
        return this.kd;
    }

    /**
     * @param newKd  PID parameters 
     *
     */
    public synchronized void setKd (float newKd) {
        this.kd = newKd;
    }

}