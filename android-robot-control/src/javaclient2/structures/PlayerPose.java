/*
 *  Player Java Client 2 - PlayerPose.java
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
 * $Id: PlayerPose.java,v 1.2 2006/02/20 22:44:57 veedee Exp $
 *
 */
package javaclient2.structures;

/**
 * A pose in the plane.
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v2.0 - Player 2.0 supported
 * </ul>
 */
public class PlayerPose {
    private float px;		// X [m]
    private float py;		// Y [m]
    private float pa;		// yaw [rad]
    
    /**
     * 
     * @return X [m]
     */
    public synchronized float getPx () {
    	return this.px;
    }
    
    /**
     * 
     * @param newPx X [m] 
     */
    public synchronized void setPx (float newPx) {
    	this.px = newPx;
    }
    
    /**
     * 
     * @return Y [m] 
     */
    public synchronized float getPy () {
        return this.py;
    }
    
    /**
     * 
     * @param newPy Y [m] 
     */
    public synchronized void setPy (float newPy) {
        this.py = newPy;
    }
    
    /**
     * 
     * @return yaw [rad] 
     */
    public synchronized float getPa () {
        return this.pa;
    }
    
    /**
     * 
     * @param newPa yaw [rad] 
     */
    public synchronized void setPa (float newPa) {
        this.pa = newPa;
    }
}