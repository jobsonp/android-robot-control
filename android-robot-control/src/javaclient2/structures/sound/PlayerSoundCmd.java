/*
 *  Player Java Client 2 - PlayerSoundCmd.java
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
 * $Id: PlayerSoundCmd.java,v 1.1.1.1 2006/02/15 17:51:19 veedee Exp $
 *
 */

package javaclient2.structures.sound;

import javaclient2.structures.*;

/**
 * Command: play clip (PLAYER_SOUND_CMD_IDX)
 * The sound interface accepts an index of a pre-recorded sound file
 * to play.  
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v2.0 - Player 2.0 supported
 * </ul>
 */
public class PlayerSoundCmd implements PlayerConstants {

    // Index of sound to be played. 
    private int index;


    /**
     * @return  Index of sound to be played. 
     **/
    public synchronized int getIndex () {
        return this.index;
    }

    /**
     * @param newIndex  Index of sound to be played. 
     *
     */
    public synchronized void setIndex (int newIndex) {
        this.index = newIndex;
    }

}