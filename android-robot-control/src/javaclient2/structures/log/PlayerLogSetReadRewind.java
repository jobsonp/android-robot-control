/*
 *  Player Java Client 2 - PlayerLogSetReadRewind.java
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
 * $Id: PlayerLogSetReadRewind.java,v 1.1.1.1 2006/02/15 17:51:18 veedee Exp $
 *
 */

package javaclient2.structures.log;

import javaclient2.structures.*;

/**
 * Request/reply: Rewind playback
 * To rewind log playback to beginning of logfile, send a
 * PLAYER_LOG_REQ_SET_READ_REWIND request.  Does not affect playback state
 * (i.e., whether it is started or stopped.  Null response. 
 * @author Radu Bogdan Rusu
 * @version
 * <ul>
 *      <li>v2.0 - Player 2.0 supported
 * </ul>
 */
public class PlayerLogSetReadRewind implements PlayerConstants {




}