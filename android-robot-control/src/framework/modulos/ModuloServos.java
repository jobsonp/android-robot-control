package framework.modulos;

import java.util.Vector;

import javaclient2.PlayerClient;
import javaclient2.ServoRTAIInterface;
import framework.conector.Conector;

public class ModuloServos {


	//Posiciones
	int[] posicionServo = {0,0,0,0,0,0,0,0};
	
	public ModuloServos() {
		
	}


	
	public void setServo(int numServo, int pos)
	{
		//mover servo y actualizar posicionServo
		posicionServo[numServo] = pos;
		Conector conector = Conector.getInstance();
		if (conector.isOnline())
		{
			Vector vv = conector.getInterfaceSERVO();
			((PlayerClient) vv.elementAt(0)).start();
			//	Mover
			((ServoRTAIInterface)vv.elementAt(1)).moveServo(numServo, pos);
			//	cerrar conexion
			((PlayerClient) vv.elementAt(0)).stop();
		}
	}
	
	public int getServo (int numServo)
	{
		return posicionServo[numServo];
	}
	
	public static void main (String[] args)
	{
		ModuloServos i = new ModuloServos();
		for (int j=0; j<=7; j++)
			i.setServo(j,63);
		
	}
}
