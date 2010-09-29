package framework.modulos.stage;

import java.util.Vector;

import javaclient2.PlayerClient;
import javaclient2.SpeechInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import ar.edu.uade.android.AndroidRobotControl;
import ar.edu.uade.android.utils.Constantes;
import framework.conector.Conector;
import framework.interfaces.IModuloBrazo;

public class ModuloStageBrazo implements IModuloBrazo {
	
	private static ModuloStageBrazo instance = null;
	
	private SharedPreferences preferences;
	
	public enum Movements {
		ARM_DOWN,
		ARM_UP,
		ARM_LEFT,
		ARM_RIGHT,
		HAND_OPEN,
		HAND_CLOSE;
	}

	private ModuloStageBrazo() {
    	
    	preferences = AndroidRobotControl.getApp().getSharedPreferences( Constantes.NOMBRE_ARCHIVO_PREFERENCIAS, 0 );
    	
    }

    public static ModuloStageBrazo getInstance()
    {
        if ( instance == null )
            instance = new ModuloStageBrazo();
        return instance;
    }
    
    private int calculateArmMovementAngle(Movements mov, int presicion) {
    	
    	Integer angleValue = null;
    	
    	switch (mov) {
    	
			case ARM_DOWN: {
				
				int armCurrentVerticalAngle = preferences.getInt( Constantes.STAGE_ARM_CURRENT_VERTICAL_ANGLE_PREFERENCES_KEY , Constantes.STAGE_ARM_VERTICAL_DEFAULT_ANGLE );
				
				armCurrentVerticalAngle -= presicion;
				
				if (armCurrentVerticalAngle <= 0) {
					armCurrentVerticalAngle = 0;
				}
				
				angleValue = armCurrentVerticalAngle;
				
				Editor edit = preferences.edit();
				
				edit.putInt( Constantes.STAGE_ARM_CURRENT_VERTICAL_ANGLE_PREFERENCES_KEY, angleValue );
				
				edit.commit();
				
				break;
				
			}
			
			default: {
			
				break;
				
			}

    	}
    	
    	return angleValue;
    }

	@Override
	public void brazoBajar(int presicion) {

		Conector conector = Conector.getInstance();

		if (conector.isOnline()) {
			
			Vector<Object> vv = conector.getInterfaceSPEECH();
			
			((PlayerClient) vv.elementAt(0)).setNotThreaded();
			
			int currentArmAngle = calculateArmMovementAngle(Movements.ARM_DOWN , presicion);

			((SpeechInterface)vv.elementAt(1)).speech("Brazo - Bajar - Angulo:" + String.valueOf(currentArmAngle));
			
			Log.e( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + "Texto Reproducido" );
			
			((PlayerClient) vv.elementAt(0)).stop();
			
		}else
		
			Log.e( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + Constantes.SERVIDOR_OFFLINE );
		
	}

	@Override
	public void brazoDerecha(int presicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void brazoIzquierda(int presicion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void brazoSubir(int presicion) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void manoAbrirMaximo() {

	}

	@Override
	public void manoCerrarMaximo() {

	}
	
	/**
	 * not apply for stage
	 */
	@Override
	public void manoAbrir(int presicion) {
		//
	}
	
	/**
	 * not apply for stage
	 */
	@Override
	public void manoCerrar(int presicion) {
		//
	}

	/**
	 * not apply for stage
	 */
	@Override
	public int[] getMaximos() {
		return null;
	}

	/**
	 * not apply for stage
	 */
	@Override
	public int[] getMinimos() {
		return null;
	}

	/**
	 * not apply for stage
	 */
	@Override
	public int[] getPosBrazo() {
		return null;
	}

	/**
	 * not apply for stage
	 */
	@Override
	public int getValorHorizontal() {
		return 0;
	}

	/**
	 * not apply for stage
	 */
	@Override
	public int getValorMano() {
		return 0;
	}

	/**
	 * not apply for stage
	 */
	@Override
	public int getValorVertical() {
		return 0;
	}

	/**
	 * not apply for stage
	 */
	@Override
	public void moverHorizontalSuave(int posicion) {
		//
	}

	/**
	 * not apply for stage
	 */
	@Override
	public void moverManoSuave(int posicion) {
		//
	}

	/**
	 * not apply for stage
	 */
	@Override
	public void moverVerticalSuave(int posicion) {
		//
	}

	/**
	 * not apply for stage
	 */
	@Override
	public void setPosBrazo(int[] data) {
		//
	}

}
