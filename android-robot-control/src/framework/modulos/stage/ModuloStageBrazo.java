package framework.modulos.stage;

import java.util.Vector;

import javaclient2.PlayerClient;
import javaclient2.SpeechInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.util.Log;
import ar.edu.uade.android.AndroidRobotControl;
import ar.edu.uade.android.utils.Constantes;
import framework.conector.Conector;
import framework.interfaces.IModuloBrazo;

public class ModuloStageBrazo implements IModuloBrazo {
	
	private static ModuloStageBrazo instance = null;
	
	private SharedPreferences preferences;
	
	public enum Movement {
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
    
    private int calculateArmMovementAngle(Movement mov, int presicion) {
    	
    	Integer angleValue = null;
    	
    	switch (mov) {
    	
			case ARM_DOWN: {
				
				int armCurrentVerticalAngle = preferences.getInt( Constantes.STAGE_ARM_CURRENT_VERTICAL_ANGLE_PREFERENCES_KEY , Constantes.STAGE_ARM_VERTICAL_DEFAULT_ANGLE );
				
				armCurrentVerticalAngle -= presicion;
				
				if ( armCurrentVerticalAngle <= Constantes.STAGE_ARM_VERTICAL_MIN_ANGLE ) {
					
					armCurrentVerticalAngle = Constantes.STAGE_ARM_VERTICAL_MIN_ANGLE;
					
				}
				
				angleValue = armCurrentVerticalAngle;
				
				saveIntegerInPreferences( Constantes.STAGE_ARM_CURRENT_VERTICAL_ANGLE_PREFERENCES_KEY , angleValue );
				
				break;
				
			}
			
			case ARM_UP: {
				
				int armCurrentVerticalAngle = preferences.getInt( Constantes.STAGE_ARM_CURRENT_VERTICAL_ANGLE_PREFERENCES_KEY , Constantes.STAGE_ARM_VERTICAL_DEFAULT_ANGLE );
				
				armCurrentVerticalAngle += presicion;
				
				if ( armCurrentVerticalAngle >= Constantes.STAGE_ARM_VERTICAL_MAX_ANGLE ) {
					
					armCurrentVerticalAngle = Constantes.STAGE_ARM_VERTICAL_MAX_ANGLE;
					
				}
				
				angleValue = armCurrentVerticalAngle;
				
				saveIntegerInPreferences( Constantes.STAGE_ARM_CURRENT_VERTICAL_ANGLE_PREFERENCES_KEY , angleValue );
				
				break;
				
			}
			
			case ARM_LEFT: {
				
				int armCurrentHorizontalAngle = preferences.getInt( Constantes.STAGE_ARM_CURRENT_HORIZONTAL_ANGLE_PREFERENCES_KEY , Constantes.STAGE_ARM_HORIZONTAL_DEFAULT_ANGLE );
				
				armCurrentHorizontalAngle -= presicion;
				
				if ( armCurrentHorizontalAngle <= Constantes.STAGE_ARM_HORIZONTAL_MIN_ANGLE ) {
					
					armCurrentHorizontalAngle = Constantes.STAGE_ARM_HORIZONTAL_MIN_ANGLE;
					
				}
				
				angleValue = armCurrentHorizontalAngle;
				
				saveIntegerInPreferences( Constantes.STAGE_ARM_CURRENT_HORIZONTAL_ANGLE_PREFERENCES_KEY , angleValue );
				
				break;
				
			}
			
			case ARM_RIGHT: {
				
				int armCurrentHorizontalAngle = preferences.getInt( Constantes.STAGE_ARM_CURRENT_HORIZONTAL_ANGLE_PREFERENCES_KEY , Constantes.STAGE_ARM_HORIZONTAL_DEFAULT_ANGLE );
				
				armCurrentHorizontalAngle += presicion;
				
				if ( armCurrentHorizontalAngle >= Constantes.STAGE_ARM_HORIZONTAL_MAX_ANGLE ) {
					
					armCurrentHorizontalAngle = Constantes.STAGE_ARM_HORIZONTAL_MAX_ANGLE;
					
				}
				
				angleValue = armCurrentHorizontalAngle;
				
				saveIntegerInPreferences( Constantes.STAGE_ARM_CURRENT_HORIZONTAL_ANGLE_PREFERENCES_KEY , angleValue );
				
				break;
				
			}
			
    	}
    	
    	return angleValue;
    }
    
    private int openCloseHand (Movement handMovement) {
    	
    	Integer handPosition = null;
    	
    	switch (handMovement) {
			
			case HAND_OPEN: {
				
				int handCurrentPosition = preferences.getInt( Constantes.STAGE_HAND_CURRENT_VALUE_PREFERENCES_KEY , Constantes.STAGE_HAND_DEFAULT_POSITION );
				
				if (handCurrentPosition == Constantes.STAGE_HAND_CLOSE ) 
					
					handCurrentPosition = Constantes.STAGE_HAND_OPEN;
				
				handPosition = handCurrentPosition;
				
				saveIntegerInPreferences( Constantes.STAGE_HAND_CURRENT_VALUE_PREFERENCES_KEY , handPosition );
				
				break;
				
			}
			
			case HAND_CLOSE: {
				
				int handCurrentPosition = preferences.getInt( Constantes.STAGE_HAND_CURRENT_VALUE_PREFERENCES_KEY , Constantes.STAGE_HAND_DEFAULT_POSITION );
				
				if (handCurrentPosition == Constantes.STAGE_HAND_OPEN ) 
					
					handCurrentPosition = Constantes.STAGE_HAND_CLOSE;
				
				handPosition = handCurrentPosition;
				
				saveIntegerInPreferences( Constantes.STAGE_HAND_CURRENT_VALUE_PREFERENCES_KEY , handPosition );
				
				break;
				
			}

		}
    	
    	return handPosition;
    	
    }
    
    private void saveIntegerInPreferences( String key , Integer value ) {
    	
    	Editor edit = preferences.edit();
		
		edit.putInt( key , value );
		
		edit.commit();
    	
    }

	@Override
	public void brazoBajar(int presicion) {

		Conector conector = Conector.getInstance();

		if (conector.isOnline()) {
			
			Vector<Object> vv = conector.getInterfaceSPEECH();
			
			((PlayerClient) vv.elementAt(0)).setNotThreaded();
			
			int currentArmAngle = calculateArmMovementAngle(Movement.ARM_DOWN , presicion);
			
			String message = "Brazo - Bajar - Angulo:" + String.valueOf(currentArmAngle);
			
			((SpeechInterface)vv.elementAt(1)).speech(message);
			
			Log.d( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + "Texto Reproducido" );
			
			closePlayerClientConnection(((PlayerClient) vv.elementAt(0)));
			
		} else

			Log.e( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + Constantes.SERVIDOR_OFFLINE );
		
	}

	@Override
	public void brazoDerecha(int presicion) {
		
		Conector conector = Conector.getInstance();

		if (conector.isOnline()) {
			
			Vector<Object> vv = conector.getInterfaceSPEECH();
			
			((PlayerClient) vv.elementAt(0)).setNotThreaded();
			
			int currentArmAngle = calculateArmMovementAngle(Movement.ARM_RIGHT , presicion);
			
			String message = "Brazo - Derecha - Angulo:" + String.valueOf(currentArmAngle);
			
			((SpeechInterface)vv.elementAt(1)).speech(message);
			
			Log.d( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + "Texto Reproducido" );
			
			closePlayerClientConnection(((PlayerClient) vv.elementAt(0)));
			
		} else

			Log.e( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + Constantes.SERVIDOR_OFFLINE );
		
	}

	@Override
	public void brazoIzquierda(int presicion) {
		
		Conector conector = Conector.getInstance();

		if (conector.isOnline()) {
			
			Vector<Object> vv = conector.getInterfaceSPEECH();
			
			((PlayerClient) vv.elementAt(0)).setNotThreaded();
			
			int currentArmAngle = calculateArmMovementAngle(Movement.ARM_LEFT , presicion);
			
			String message = "Brazo - Izquierda - Angulo:" + String.valueOf(currentArmAngle);
			
			((SpeechInterface)vv.elementAt(1)).speech(message);
			
			Log.d( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + "Texto Reproducido" );
			
			closePlayerClientConnection(((PlayerClient) vv.elementAt(0)));
			
		} else

			Log.e( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + Constantes.SERVIDOR_OFFLINE );
		
	}

	@Override
	public void brazoSubir(int presicion) {
		
		Conector conector = Conector.getInstance();

		if (conector.isOnline()) {
			
			Vector<Object> vv = conector.getInterfaceSPEECH();
			
			((PlayerClient) vv.elementAt(0)).setNotThreaded();
			
			int currentArmAngle = calculateArmMovementAngle(Movement.ARM_UP , presicion);
			
			String message = "Brazo - Subir - Angulo:" + String.valueOf(currentArmAngle);
			
			((SpeechInterface)vv.elementAt(1)).speech(message);
			
			Log.d( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + "Texto Reproducido" );
			
			closePlayerClientConnection(((PlayerClient) vv.elementAt(0)));
			
		} else

			Log.e( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + Constantes.SERVIDOR_OFFLINE );
		
	}
	
	@Override
	public void manoAbrirMaximo() {
		
		Conector conector = Conector.getInstance();

		if (conector.isOnline()) {
			
			Vector<Object> vv = conector.getInterfaceSPEECH();
			
			((PlayerClient) vv.elementAt(0)).setNotThreaded();
			
			openCloseHand(Movement.HAND_OPEN);
			
			String message = "Mano - Abierta";
			
			((SpeechInterface)vv.elementAt(1)).speech(message);
			
			Log.d( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + "Texto Reproducido" );
			
			closePlayerClientConnection(((PlayerClient) vv.elementAt(0)));
			
		} else

			Log.e( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + Constantes.SERVIDOR_OFFLINE );
		
	}

	@Override
	public void manoCerrarMaximo() {
		
		Conector conector = Conector.getInstance();

		if (conector.isOnline()) {
			
			Vector<Object> vv = conector.getInterfaceSPEECH();
			
			((PlayerClient) vv.elementAt(0)).setNotThreaded();
			
			openCloseHand(Movement.HAND_CLOSE);
			
			String message = "Mano - Cerrada";
			
			((SpeechInterface)vv.elementAt(1)).speech(message);
			
			Log.d( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + "Texto Reproducido" );
			
			closePlayerClientConnection(((PlayerClient) vv.elementAt(0)));
			
		} else

			Log.e( ModuloStageBrazo.class.getName(), Constantes.MODULO_MANO + Constantes.SERVIDOR_OFFLINE );

	}
	
	private void closePlayerClientConnection (final PlayerClient playerClient) {
		
		Handler handler = new Handler(); 
		handler.postDelayed(new Runnable() { 
			public void run() {
				playerClient.close();
			} 
		}, Constantes.STAGE_SHOW_SPEECH_MESSAGE_DEFAULT_TIME_IN_SECONDS*1000);
		
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
