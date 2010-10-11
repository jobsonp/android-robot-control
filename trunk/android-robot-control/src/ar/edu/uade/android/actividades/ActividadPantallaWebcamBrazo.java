package ar.edu.uade.android.actividades;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import ar.edu.uade.android.R;
import ar.edu.uade.android.controladores.ControladorBrazo;
import ar.edu.uade.android.mjpeg.MjpegInputStream;
import ar.edu.uade.android.mjpeg.MjpegView;
import ar.edu.uade.android.utils.Configuracion;
import ar.edu.uade.android.utils.Constantes;

public class ActividadPantallaWebcamBrazo extends ActividadPantallaAbstract {
    
	private MjpegView mv;
	 
	private ControladorBrazo controladorBrazo;
	
	private Boolean hasStreamingSAlreadytarted;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.webcam_brazo );

        controladorBrazo = new ControladorBrazo();
        
        ImageView upArrow = (ImageView) findViewById( R.id.webcam_brazo_up_arrow );
        ImageView leftArrow = (ImageView) findViewById( R.id.webcam_brazo_left_arrow );
        ImageView rightArrow = (ImageView) findViewById( R.id.webcam_brazo_right_arrow );
        ImageView downArrow = (ImageView) findViewById( R.id.webcam_brazo_down_arrow );
        
        Button openCloseHandButton = (Button) findViewById( R.id.webcam_brazo_mano_button );
        
        upArrow.setOnClickListener( upArrowOnClickListener );
        leftArrow.setOnClickListener( leftArrowOnClickListener );
        rightArrow.setOnClickListener( rightArrowOnClickListener );
        downArrow.setOnClickListener( downArrowOnClickListener );
        
        openCloseHandButton.setOnClickListener(openCloseHandClickListener);
        
        resetArmAndHand();
        
        initializeStreamingView();
    }
    
    private OnClickListener openCloseHandClickListener = new OnClickListener() {
        
    	public void onClick(View v) {
        	
        	try {
        	
        		SharedPreferences preferences = getSharedPreferences( Constantes.NOMBRE_ARCHIVO_PREFERENCIAS, 0 );
        		
        		int handCurrentPosition = preferences.getInt( Constantes.STAGE_HAND_CURRENT_VALUE_PREFERENCES_KEY , Constantes.STAGE_HAND_DEFAULT_POSITION );
        		
        		Button button = (Button) findViewById( R.id.webcam_brazo_mano_button );
        		
        		if ( handCurrentPosition == Constantes.STAGE_HAND_OPEN ) {
        			
            		controladorBrazo.cerrarManoMaximo();
            		
            		button.setText("Abrir");
        			
        		} else {
        			
        			controladorBrazo.abrirManoMaximo();
        			
        			button.setText("Cerrar");
        			
        		}
        		
        		Log.d( ActividadPantallaWebcamPrincipal.class.getName(), "go forward executed." );
        		
        	} catch (Exception e) {
        		
        		Log.e( ActividadPantallaWebcamPrincipal.class.getName(), "go forward couldn't be executed.", e );
        		
			}
        	
        }
        
    };
    
    private OnClickListener upArrowOnClickListener = new OnClickListener() {
        
    	public void onClick(View v) {
        	
        	try {
        		controladorBrazo.brazoSubir(Constantes.STAGE_ARM_VERTICAL_DEFAULT_MOVEMENT_ANGLE);
        		Log.d( ActividadPantallaWebcamPrincipal.class.getName(), "go forward executed." );
        	} catch (Exception e) {
        		Log.e( ActividadPantallaWebcamPrincipal.class.getName(), "go forward couldn't be executed.", e );
			}
        	
        }
        
    };
    
    private OnClickListener downArrowOnClickListener = new OnClickListener() {
        
    	public void onClick(View v) {
        	
        	try {
        		controladorBrazo.brazoBajar(Constantes.STAGE_ARM_VERTICAL_DEFAULT_MOVEMENT_ANGLE);
        		Log.d( ActividadPantallaWebcamPrincipal.class.getName(), "down arm executed." );
        	} catch (Exception e) {
        		Log.e( ActividadPantallaWebcamPrincipal.class.getName(), "down arm couldn't be executed.", e );
			}
        	
        }
        
    };
    
    private OnClickListener leftArrowOnClickListener = new OnClickListener() {
        
    	public void onClick(View v) {
        	
        	try {
        		controladorBrazo.brazoIzquierda(Constantes.STAGE_ARM_HORIZONTAL_DEFAULT_MOVEMENT_ANGLE);
        		Log.d( ActividadPantallaWebcamPrincipal.class.getName(), "turn left executed." );
        	} catch (Exception e) {
        		Log.e( ActividadPantallaWebcamPrincipal.class.getName(), "turn left couldn't be executed. ", e );
			}
        	
        }
        
    };
    
    private OnClickListener rightArrowOnClickListener = new OnClickListener() {
        
    	public void onClick(View v) {
        	
        	try {
        		controladorBrazo.brazoDerecha(Constantes.STAGE_ARM_HORIZONTAL_DEFAULT_MOVEMENT_ANGLE);
        		Log.d( ActividadPantallaWebcamPrincipal.class.getName(), "turn right executed." );
        	} catch (Exception e) {
        		Log.e( ActividadPantallaWebcamPrincipal.class.getName(), "turn right couldn't be executed. ", e );
			}
        	
        }
        
    };
    
    private void resetArmAndHand() {
    	
    	SharedPreferences preferences = getSharedPreferences( Constantes.NOMBRE_ARCHIVO_PREFERENCIAS, 0 );
    	
    	Editor edit = preferences.edit();
    	
    	edit.putInt( Constantes.STAGE_ARM_CURRENT_VERTICAL_ANGLE_PREFERENCES_KEY , Constantes.STAGE_ARM_VERTICAL_DEFAULT_ANGLE );
    	
    	edit.putInt( Constantes.STAGE_ARM_CURRENT_HORIZONTAL_ANGLE_PREFERENCES_KEY , Constantes.STAGE_ARM_HORIZONTAL_DEFAULT_ANGLE );
    	
    	edit.putInt( Constantes.STAGE_HAND_CURRENT_VALUE_PREFERENCES_KEY , Constantes.STAGE_HAND_DEFAULT_POSITION );
    	
    	edit.commit();
    	
    }

    
    private void initializeStreamingView()
    {
        mv = (MjpegView) this.findViewById( R.id.mjpeg_view_brazo );
        mv.setSource( MjpegInputStream.read( "http://" + Configuracion.getConfigString( Constantes.IP_PLAYER ) + ":"
            + Configuracion.getConfigString( Constantes.ARM_WEBCAM_PORT ) ) );
        mv.setDisplayMode( MjpegView.SIZE_BEST_FIT );

        mv.showFps( super.getPreferences().getBoolean( "Show FPS Key", true ) );
        
        hasStreamingSAlreadytarted = true;
    }

    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	resetArmAndHand();
    	if (!hasStreamingSAlreadytarted) {
    		mv.startPlayback();
    	}
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mv.stopPlayback();
        hasStreamingSAlreadytarted = false;
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mv.stopPlayback();
    }

}