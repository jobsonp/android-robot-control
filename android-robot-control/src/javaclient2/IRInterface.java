/*
 *  Player Java Client 2 - IRInterface.java
 *  Copyright (C) 2002-2006 Radu Bogdan Rusu, Maxim Batalin
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
 * $Id: IRInterface.java,v 1.5 2006/03/10 19:04:59 veedee Exp $
 *
 */
package javaclient2;

import java.io.IOException;

import javaclient2.xdr.OncRpcException;
import javaclient2.xdr.XdrBufferDecodingStream;
import javaclient2.xdr.XdrBufferEncodingStream;

import javaclient2.structures.PlayerMsgHdr;
import javaclient2.structures.PlayerPose;
import javaclient2.structures.ir.PlayerIrPose;
import javaclient2.structures.ir.PlayerIrData;

/**
 * The ir interface provides access to an array of infrared (IR) range sensors. This interface 
 * accepts no commands. 
 * @author Radu Bogdan Rusu, Maxim Batalin
 * @version
 * <ul>
 *      <li>v2.0 - Player 2.0 supported
 * </ul>
 */
public class IRInterface extends PlayerDevice {

    private static final boolean isDebugging = PlayerClient.isDebugging;

    private PlayerIrData pidata;
    private boolean      readyPidata = false;
    private PlayerIrPose pipose;
    private boolean      readyPipose = false;

    /**
     * Constructor for IRInterface.
     * @param pc a reference to the PlayerClient object
     */
    public IRInterface (PlayerClient pc) { super(pc); }
    
    /**
     * Read the IR values.
     */
    public synchronized void readData (PlayerMsgHdr header) {
        try {
        	switch (header.getSubtype ()) {
        		case PLAYER_IR_DATA_RANGES: {
        			// Buffer for reading voltages_count
        			byte[] buffer = new byte[4];
        			// Read voltages_count
        			is.readFully (buffer, 0, 4);
        			
        			// Begin decoding the XDR buffer
        			XdrBufferDecodingStream xdr = new XdrBufferDecodingStream (buffer);
        			xdr.beginDecoding ();
        			int voltagesCount = xdr.xdrDecodeInt ();
        			xdr.endDecoding   ();
        			xdr.close ();
        			
        			// Buffer for reading voltage values
        			buffer = new byte[PLAYER_IR_MAX_SAMPLES * 4];
        			// Read voltage values
        			is.readFully (buffer, 0, voltagesCount * 4 + 4);
        			xdr = new XdrBufferDecodingStream (buffer);
        			xdr.beginDecoding ();
        			float[] voltages = xdr.xdrDecodeFloatVector ();
        			xdr.endDecoding   ();
        			xdr.close ();
        			
        			// Buffer for reading ranges_count
        			buffer = new byte[4];
        			// Read ranges_count
        			is.readFully (buffer, 0, 4);
        			
        			// Begin decoding the XDR buffer
        			xdr = new XdrBufferDecodingStream (buffer);
        			xdr.beginDecoding ();
        			int rangesCount = xdr.xdrDecodeInt ();
        			xdr.endDecoding   ();
        			xdr.close ();
        			
        			// Buffer for reading range values
        			buffer = new byte[PLAYER_IR_MAX_SAMPLES * 4];
        			// Read range values
        			is.readFully (buffer, 0, rangesCount * 4);
        			xdr = new XdrBufferDecodingStream (buffer);
        			xdr.beginDecoding ();
        			float[] ranges = xdr.xdrDecodeFloatVector ();
        			xdr.endDecoding   ();
        			xdr.close ();
        			
        			pidata = new PlayerIrData ();
        			
        			pidata.setVoltages_count (voltagesCount);
        			pidata.setVoltages       (voltages);
        			pidata.setRanges_count   (rangesCount);
        			pidata.setRanges         (ranges);
        			
        			readyPidata = true;
        			break;
        		}
        	}
        } catch (IOException e) {
        	throw new PlayerException 
        		("[IR] : Error reading payload: " + 
        				e.toString(), e);
        } catch (OncRpcException e) {
        	throw new PlayerException 
        		("[IR] : Error while XDR-decoding payload: " + 
        				e.toString(), e);
        }
    }
    
    
    /**
     * Get the state data.
     * @return an object of type PlayerIrData containing the requested data 
     */
    public PlayerIrData getData () { return this.pidata; }
    
    /**
     * Get the pose data.
     * @return an object of type PlayerIrPose containing the requested pose data 
     */
    public PlayerIrPose getPose () { return this.pipose; }
    
    /**
     * Check if data is available.
     * @return true if ready, false if not ready 
     */
    public boolean isDataReady () {
        if (readyPidata) {
        	readyPidata = false;
            return true;
        }
        return false;
    }
    
    /**
     * Check if pose data is available.
     * @return true if ready, false if not ready 
     */
    public boolean isPoseReady () {
        if (readyPipose) {
        	readyPipose = false;
            return true;
        }
        return false;
    }

    
    /**
     * Configuration request: Query pose.
     * <br><br>
     * See the player_ir_pose structure from player.h
     */
    public void queryPose () {
        try {
            sendHeader (PLAYER_MSGTYPE_REQ, PLAYER_IR_POSE, 0);
            os.flush ();
        } catch (IOException e) {
        	throw new PlayerException 
        		("[IR] : Couldn't send PLAYER_IR_POSE command: " + 
        				e.toString(), e);
        }
    }
    
    /**
     * Configuration request: IR power.
     * @param state 0 for power off, 1 for power on
     */
    public void setIRPower (int state) {
        try {
        	sendHeader (PLAYER_MSGTYPE_REQ, PLAYER_IR_POWER, 4);
        	XdrBufferEncodingStream xdr = new XdrBufferEncodingStream (4);
        	xdr.beginEncoding (null, 0);
        	xdr.xdrEncodeByte ((byte)state);
        	xdr.endEncoding ();
        	os.write (xdr.getXdrData (), 0, xdr.getXdrLength ());
        	xdr.close ();
        	os.flush ();
        } catch (IOException e) {
        	throw new PlayerException 
        		("[IR] : Couldn't send PLAYER_IR_POWER_REQ request: " + 
        				e.toString(), e);
        } catch (OncRpcException e) {
        	throw new PlayerException 
        		("[IR] : Error while XDR-encoding POWER request: " + 
        				e.toString(), e);
        }
    }
    
    /**
     * Handle acknowledgement response messages
     * @param header Player header
     */
    public void handleResponse (PlayerMsgHdr header) {
        try {
            switch (header.getSubtype ()) {
                case PLAYER_IR_POSE: {
                	// Buffer for reading poses_count
                	byte[] buffer = new byte[4];
                	// Read poses_count
                	is.readFully (buffer, 0, 4);
                	
                	// Begin decoding the XDR buffer
                	XdrBufferDecodingStream xdr = new XdrBufferDecodingStream (buffer);
                	xdr.beginDecoding ();
                	int posesCount = xdr.xdrDecodeInt ();
                	xdr.endDecoding   ();
                	xdr.close ();
                	
                	// Buffer for reading IR poses
                	buffer = new byte[PLAYER_IR_MAX_SAMPLES * 12];
                	// Read IR poses
                	is.readFully (buffer, 0, posesCount * 12);
                	xdr = new XdrBufferDecodingStream (buffer);
                	xdr.beginDecoding ();
                	PlayerPose[] pps = new PlayerPose[posesCount];
                	for (int i = 0; i < posesCount; i++) {
                		PlayerPose pp = new PlayerPose ();
                		pp.setPx (xdr.xdrDecodeFloat ());
                		pp.setPy (xdr.xdrDecodeFloat ());
                		pp.setPa (xdr.xdrDecodeFloat ());
                		pps[i] = pp;
                	}
                	xdr.endDecoding   ();
                	xdr.close ();
                	
                	pipose = new PlayerIrPose (); 
                	pipose.setPoses_count (posesCount);
                	pipose.setPoses (pps);
                	
                	readyPipose = true;
                	break;
                }
                case PLAYER_IR_POWER: {
                    break;
                }
                default:{
                	if (isDebugging)
                		System.err.println ("[IR]Debug] : " +
                				"Unexpected response " + header.getSubtype () + 
                				" of size = " + header.getSize ());
                    break;
                }
            }
        } catch (IOException e) {
        	throw new PlayerException 
        		("[IR] : Error reading payload: " + 
        				e.toString(), e);
        } catch (OncRpcException e) {
        	throw new PlayerException 
        		("[IR] : Error while XDR-decoding payload: " + 
        				e.toString(), e);
        }
    }

}
