/**************************************************************************
* ServoInterface es la interface que sirve para manejar servos conectados
* al Player/Stage mediante el puerto paralelo y un kernel RTAI
* 
* �sta clase fue escrita para la TESIS de Ingenier�a en Inform�tica de la
* Facultad Argentina de la Empresa (UADE).
* 
**************************************************************************/
package javaclient2;

import java.io.IOException;

import javaclient2.structures.servoRTAI.PlayerServoRTAIData;
import javaclient2.xdr.OncRpcException;
import javaclient2.xdr.XdrBufferEncodingStream;



public class ServoRTAIInterface extends PlayerDevice  {
	
	//Estructura de datos de los servos.
	private PlayerServoRTAIData pcdata;
	
	@SuppressWarnings("unused")
	private boolean readyServoData = false;
	
	/***********************************************************************
	 * CONSTRUCTOR:
	 * recibe un PlayerClient y se lo entrega a super. Luego instancia la
	 * estructura de datos PlayerServoRTAIData
	 * 
	 * @param pc
	 ***********************************************************************/
    public ServoRTAIInterface (PlayerClient pc) { 
        	super(pc);
        	//instanciar PlayerServoData
        	pcdata = new PlayerServoRTAIData();
    }
    
    /***********************************************************************
     * moveServo mueve un servo a una posicion espec�fica.
     * Primero escribe el valor en la estructura de datos.
     * Segundo se encarga de enviar el mensaje al player stage para mover
     * el servo
     * 
     * El paquete entregado al Player / Stage (driver servoRTAI) debe ser
     * del tipo PLAYER_MSG_TYPE_CMD y PLAYER_SERVORTAI_CMD_MOVE, con un size
     * de 5.
     * 
     * luego de enviar el header, el contenido del paquete debe ser de 2
     * valores, el primero en Unsigned INT como m�scara de servo y unsigned
     * CHAR como posici�n de 0 a 127.
     * 
     * @param servo = numero de servo del 0 al 7
     * @param position = numero en grados de 0 a 180
     * 
     * @return boolean = true ok, false error
     ***********************************************************************/
    public boolean moveServo (int servo, int position)
    {	
    	int mascaraServo = 0; //deber�a ser un unsigned int
    	/**********************************************
    	 * Mascara del servo a enviar
    	 * para el servo 0 -> 00000001
    	 * para el servo 1 -> 00000010
    	 * para el servo 2 -> 00000100
    	 *     ...              ...
    	 * para el servo 7 -> 10000000
    	 **********************************************/
    	if (servo == 0) { mascaraServo = 1; }
    	if (servo == 1) { mascaraServo = 2; }
    	if (servo == 2) { mascaraServo = 4; }
    	if (servo == 3) { mascaraServo = 8; }
    	if (servo == 4) { mascaraServo = 16; }
    	if (servo == 5) { mascaraServo = 32; }
    	if (servo == 6) { mascaraServo = 64; }
    	if (servo == 7) { mascaraServo = 128; }
    	//mascaraServo = 65535;
    	
    	/**********************************************
    	 * 
    	 */
    	System.out.println(" [Servo Interface] la m�scara de servo es: " + (int)mascaraServo + " data: " + position);
    	//Guardar Backup
    	PlayerServoRTAIData backup = pcdata;
    	//Escribir el valor en la estructura de datos.
    	if (pcdata.setServo(servo, position))
    	{
    		//TODO: Transmitir mensaje al player/stage
            try {
            	//Seg�n matiu el size es siempre 5
            	int size = 4 + 4 + 5 + 3; //4 + 4 + temp.length () + leftOvers; asi era en el speech
            	int leftOvers = 3; //4+4+5=13, debe ser multiplo de 4 entonces se suma 3 en blanco
            	sendHeader (PLAYER_MSGTYPE_CMD, PLAYER_SERVORTAI_CMD_MOVE, 8);
            	/******************************************
            	 * TODO: Corregir el contenido del paquete
            	 * En la siguiente linea hay un error,
            	 * segun matiu a su driver le tengo que pasar
            	 * un Unsigned INT como mascara de servos
            	 * y un Unsigned Char como valor del servo
            	 ******************************************/
            	//3da prueba
            	XdrBufferEncodingStream xdr = new XdrBufferEncodingStream (size);
            	xdr.beginEncoding (null, 0);
            	//xdr.xdrEncodeInt  (size);
            	//xdr.xdrEncodeByte ((byte)size);
            	xdr.xdrEncodeInt (mascaraServo);
            	xdr.xdrEncodeByte((byte)position);
            	xdr.endEncoding ();
            	os.write (xdr.getXdrData (), 0, xdr.getXdrLength ());
            	xdr.close ();
            	byte[] buf = new byte[leftOvers];
            	os.write (buf, 0, leftOvers);            	
            	os.flush ();
            	
            } catch (IOException e) {
            	throw new PlayerException 
                	(" [ ServoRTAI ] : Couldn't send servo-rtai command move request: " + 
                        e.toString (), e);
            } catch (OncRpcException e) {
            	throw new PlayerException 
                	(" [ ServoRTAI ] : Couldn't XDR-encode servo-rtai command move request: " + 
                        e.toString (), e);
            }
    		return true;
    	} else
    	{
    		pcdata = backup;
    		return false;
    	}
    	
    }
    
    /***********************************************************************
     * getData devuelve la estructura de datos de los servos.
     * 
     * @return PlayerServoData 
     ***********************************************************************/
    public PlayerServoRTAIData getData() {
    	return pcdata;
    }
    
    /***********************************************************************
     * getData devuelve la estructura de datos de los servos.
     * 
     * @param data = la estructura PlayerServoData
     ***********************************************************************/
    public void setData(PlayerServoRTAIData data) {
    	pcdata = data;
    }
    
}
