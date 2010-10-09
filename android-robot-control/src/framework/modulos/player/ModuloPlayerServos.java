package framework.modulos.player;

import java.util.Vector;

import javaclient2.PlayerClient;
import javaclient2.ServoRTAIInterface;
import framework.conector.Conector;

public class ModuloPlayerServos {


	//Posiciones
	int[] posicionServo = {0,0,0,0,0,0,0,0};
	
	public ModuloPlayerServos() {
		
	}
	
	public void setServo(int numServo, int pos)
	{
		//mover servo y actualizar posicionServo
		posicionServo[numServo] = pos;
		Conector conector = Conector.getInstance();
		if (conector.isOnline())
		{
			Vector<Object> vv = conector.getInterfaceSERVO();
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
		ModuloPlayerServos i = new ModuloPlayerServos();
		for (int j=0; j<=7; j++)
			i.setServo(j,63);
		
	}
}
