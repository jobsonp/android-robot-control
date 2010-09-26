/**************************************************************************
* PlayerServoData es la estructura de datos utilizada por ServoInterface
* 
* Ésta clase fue escrita para la TESIS de Ingeniería en Informática de la
* Facultad Argentina de la Empresa (UADE).
**************************************************************************/
package javaclient2.structures.servoRTAI;

import javaclient2.structures.*;

public class PlayerServoRTAIData implements PlayerConstants {
	
	//TODO: Métodos la estructura
	private int maxiumValue = 127; //por defecto es 127 pasos
	//Posiciones actuales de los servos inicializado a la mitad.
	private int[] ActualServoData = { 63, 63, 63, 63, 63, 63, 63, 63 };
	
	//TODO: Métodos para setear los valores de los servos.
	
	/***************************************************************************
	 * getServo devuelve la posicion de un servo en particular del 0 al 7
	 * 
	 * @param servo = número de servo del 0 al 7
	 ***************************************************************************/
	public synchronized int getServo (int servo) {
		if ((servo<=7)&(servo>=0))
			return this.ActualServoData[servo];
		return -1;
    }
	
	/***************************************************************************
	 * getServo devuelve la posicion de un servo en particular del 0 al 7
	 * 
	 * @param servo = número de servo del 0 al 7
	 ***************************************************************************/
	public synchronized boolean setServo (int servo, int angle) {
		if (((angle<=maxiumValue)&(angle>=0))&((servo<=7)&(servo>=0))) 
		{
			this.ActualServoData[servo] = angle;
			return true;
		}
		else
			System.err.println ("     [ PlayerServoData ERROR: setServo(" + servo + ", " + angle + ")  -> Must be servo in 0-7 and angle in 0-" + maxiumValue + "    ]" );
		return false;
    }
	
	/***************************************************************************
	 * getServo devuelve el array de posiciones de los 8 servos
	 * 
	 ***************************************************************************/
	public synchronized int[] getServoArray () {
		return this.ActualServoData;
    }

	/***************************************************************************
	 * getServo devuelve el array de posiciones de los 8 servos
	 * 
	 * @param servoArray = Array de 8 posiciones con los valores de cada servo
	 ***************************************************************************/
	public synchronized boolean setServoArray (int[] servoArray) {
		//Chequear la consistencia del array
		boolean ok = true;
		for (int i=0; i<=7; i++)
		{
			if ((servoArray[i]>maxiumValue)&(servoArray[i]<0))
				ok = false;
		}
		if (ok)
		{
			//Grabar el array de int[]
			ActualServoData = servoArray;
			return true;
		}
		else
		{	//Imprimir error
			System.err.println("     [ PlayerServoData ERROR: setServoArray( -> Array = ");
			for (int i=0; i<=7; i++)
			{
			System.err.println("                                            " + i + ", " + servoArray[i] + ", ");
			}
			System.err.println("                                            )  Must be 0-7, 0-" + maxiumValue + "                ]");
			return false;
		}
	}
	/***************************************************************************
	 * getMaxiumValue devuelve el maximo valor de la escala
	 * 
	 ***************************************************************************/
	public synchronized int getMaxiumValue () {
		return this.maxiumValue;
    }
	/***************************************************************************
	 * setMaxiumValue setea el maximo valor de la escala
	 * 
	 ***************************************************************************/
	public synchronized void setMaxiumValue (int value) {
		this.maxiumValue=value;
    }

}
