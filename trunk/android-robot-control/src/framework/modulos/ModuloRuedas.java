package framework.modulos;

import java.util.Vector;
import framework.conector.Conector;
import javaclient2.PlayerClient;
import javaclient2.ServoRTAIInterface;

public class ModuloRuedas extends ModuloServos {

	private static ModuloRuedas instance = null;
	private static int ADELANTE = 6;
	private static int ATRAS = 15;
	private static int STOP = 0;
	private static int GIR_DERECHA = 4;
	private static int GIR_IZQUIERDA = 2;
	private static int ROT_DERECHA = 7;
	private static int ROT_IZQUIERDA = 14;

	private ModuloRuedas() {
		super();
	}
	
	public static ModuloRuedas getInstance ()
	{
		if (instance==null)
			instance = new ModuloRuedas();
		return instance;
	}
	
	public void Adelante ()
	{
		//		INICIAR CONEXION
		Conector conector = Conector.getInstance();
		Vector vv = conector.getInterfaceMOTOR();
		((PlayerClient) vv.elementAt(0)).runThreaded(-1,-1);//.start();
		//		EJECUTAR COMANDO
		((ServoRTAIInterface)vv.elementAt(1)).moveServo(7,ADELANTE);
		//	    CERRAR CONEXION
		((PlayerClient) vv.elementAt(0)).stop();
		
	}
	
	public void Atras()
	{
		//		INICIAR CONEXION
		Conector conector = Conector.getInstance();
		Vector vv = conector.getInterfaceMOTOR();
		((PlayerClient) vv.elementAt(0)).start();
		//		EJECUTAR COMANDO
		((ServoRTAIInterface)vv.elementAt(1)).moveServo(7,ATRAS);
		//	    CERRAR CONEXION
		((PlayerClient) vv.elementAt(0)).stop();
	}
	
	public void Stop()
	{
		//		INICIAR CONEXION
		Conector conector = Conector.getInstance();
		Vector vv = conector.getInterfaceMOTOR();
		((PlayerClient) vv.elementAt(0)).runThreaded(-1,-1);//.runThreaded(-1,-1)();
		//		EJECUTAR COMANDO
		((ServoRTAIInterface)vv.elementAt(1)).moveServo(7,STOP);
		//	    CERRAR CONEXION
		((PlayerClient) vv.elementAt(0)).stop();
	}
	
	public void RotarIzq()
	{
		//		INICIAR CONEXION
		Conector conector = Conector.getInstance();
		Vector vv = conector.getInterfaceMOTOR();
		((PlayerClient) vv.elementAt(0)).runThreaded(-1,-1);
		//		EJECUTAR COMANDO
		((ServoRTAIInterface)vv.elementAt(1)).moveServo(7,ROT_IZQUIERDA);
		//	    CERRAR CONEXION
		((PlayerClient) vv.elementAt(0)).stop();
	}
	
	public void RotarDer()
	{
		//		INICIAR CONEXION
		Conector conector = Conector.getInstance();
		Vector vv = conector.getInterfaceMOTOR();
		((PlayerClient) vv.elementAt(0)).runThreaded(-1,-1);
		//		EJECUTAR COMANDO
		((ServoRTAIInterface)vv.elementAt(1)).moveServo(7,ROT_DERECHA);
		//	    CERRAR CONEXION
		((PlayerClient) vv.elementAt(0)).stop();
	}
	
	public void GirarIzq()
	{
		//		INICIAR CONEXION
		Conector conector = Conector.getInstance();
		Vector vv = conector.getInterfaceMOTOR();
		((PlayerClient) vv.elementAt(0)).runThreaded(-1,-1);
		//		EJECUTAR COMANDO
		((ServoRTAIInterface)vv.elementAt(1)).moveServo(7,GIR_IZQUIERDA);
		//	    CERRAR CONEXION
		((PlayerClient) vv.elementAt(0)).stop();
	}
	
	public void GirarDer()
	{
		//		INICIAR CONEXION
		Conector conector = Conector.getInstance();
		Vector vv = conector.getInterfaceMOTOR();
		((PlayerClient) vv.elementAt(0)).runThreaded(-1,-1);
		//		EJECUTAR COMANDO
		((ServoRTAIInterface)vv.elementAt(1)).moveServo(7,GIR_DERECHA);
		//	    CERRAR CONEXION
		((PlayerClient) vv.elementAt(0)).stop();
	}
	
}
