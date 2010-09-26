package framework.modulos.stage;

import java.util.Vector;

import javaclient2.PlayerClient;
import javaclient2.Position2DInterface;
import android.os.Handler;
import ar.edu.uade.android.utils.Constantes;
import framework.conector.Conector;
import framework.interfaces.IModuloRuedas;

public class ModuloStageRuedas implements IModuloRuedas {
	
	private static ModuloStageRuedas instance = null;
	
	private ModuloStageRuedas() {

	}
	
	public static ModuloStageRuedas getInstance () {
		if (instance==null)
			instance = new ModuloStageRuedas();
		return instance;
	}

	@Override
	public void Adelante() {
		
		Conector conector = Conector.getInstance();
		Vector<Object> vv = conector.getInterfacePOSITION2D();
		
		final Position2DInterface posi = (Position2DInterface)vv.elementAt(1);
		
		((PlayerClient) vv.elementAt(0)).runThreaded(-1,-1);
		
		posi.setSpeed(Constantes.STAGE_MOVEMENT_SPEED, 0);
		
		Handler handler = new Handler(); 
		handler.postDelayed(new Runnable() { 
			public void run() {
				stop(posi);
			} 
		}, Constantes.STAGE_SECONDS_MOVEMENT*1000);
		
	}
	
	@Override
	public void Atras() {
		
		Conector conector = Conector.getInstance();
		Vector<Object> vv = conector.getInterfacePOSITION2D();
		
		final Position2DInterface posi = (Position2DInterface)vv.elementAt(1);
		
		((PlayerClient) vv.elementAt(0)).runThreaded(-1,-1);
		
		posi.setSpeed(-Constantes.STAGE_MOVEMENT_SPEED, 0);
		
		Handler handler = new Handler(); 
		handler.postDelayed(new Runnable() { 
			public void run() {
				stop(posi);
			} 
		}, Constantes.STAGE_SECONDS_MOVEMENT*1000);
		
	}

	@Override
	public void GirarDer() {
		
		Conector conector = Conector.getInstance();
		Vector<Object> vv = conector.getInterfacePOSITION2D();
		
		final Position2DInterface posi = (Position2DInterface)vv.elementAt(1);
		
		((PlayerClient) vv.elementAt(0)).runThreaded(-1,-1);
		
		posi.setSpeed(0 , -Constantes.STAGE_TURN_MOVEMENT_SPEED);
		
		Handler handler = new Handler(); 
		handler.postDelayed(new Runnable() { 
			public void run() {
				stop(posi);
			} 
		}, Constantes.STAGE_SECONDS_MOVEMENT*1000);
		
	}

	@Override
	public void GirarIzq() {
		
		Conector conector = Conector.getInstance();
		Vector<Object> vv = conector.getInterfacePOSITION2D();
		
		final Position2DInterface posi = (Position2DInterface)vv.elementAt(1);
		
		((PlayerClient) vv.elementAt(0)).runThreaded(-1,-1);
		
		posi.setSpeed(0 , Constantes.STAGE_TURN_MOVEMENT_SPEED);
		
		Handler handler = new Handler(); 
		handler.postDelayed(new Runnable() { 
			public void run() {
				stop(posi);
			} 
		}, Constantes.STAGE_SECONDS_MOVEMENT*1000);
		
	}
	
	private void stop(Position2DInterface posi) {
		posi.setSpeed(0, 0);
	}

}
