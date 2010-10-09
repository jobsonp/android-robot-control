package ar.edu.uade.android.actividades;

import java.io.IOException;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import ar.edu.uade.android.R;
import ar.edu.uade.android.controladores.ControladorServos;
import ar.edu.uade.android.mjpeg.MjpegInputStream;
import ar.edu.uade.android.mjpeg.MjpegView;
import ar.edu.uade.android.utils.Configuracion;
import ar.edu.uade.android.utils.Constantes;

public class ActividadPantallaWebcamPrincipal extends ActividadPantallaAbstract {

    private MjpegView mv;
    
    private ControladorServos controladorServos;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView( R.layout.webcam_principal );
        
        controladorServos = new ControladorServos();
        
        ImageView upArrow = (ImageView) findViewById( R.id.webcam_principal_up_arrow );
        ImageView leftArrow = (ImageView) findViewById( R.id.webcam_principal_left_arrow );
        ImageView rightArrow = (ImageView) findViewById( R.id.webcam_principal_right_arrow );
        ImageView downArrow = (ImageView) findViewById( R.id.webcam_principal_down_arrow );
        
        upArrow.setOnClickListener( upArrowOnClickListener );
        leftArrow.setOnClickListener( leftArrowOnClickListener );
        rightArrow.setOnClickListener( rightArrowOnClickListener );
        downArrow.setOnClickListener( downArrowOnClickListener );
        
        Button speechButton = (Button) this.findViewById( R.id.webcam_principal_speech_button );
        speechButton.setOnClickListener( speechOnClickListener );
        
        Button screenshotButton = (Button) this.findViewById( R.id.webcam_principal_screenshot_button );
        screenshotButton.setOnClickListener( screenshotCaptureOnClickListener );
        
        initializeStreamingView();
    }
    
    private OnClickListener speechOnClickListener = new OnClickListener() {
        
    	public void onClick(View v) {
        	
    		dialog();
    		
        }
        
    };
    
    private OnClickListener screenshotCaptureOnClickListener = new OnClickListener() {
        
    	public void onClick(View v) {
        	
    		try {
				screenshot( v );
				toast( getString( R.string.toast_screeshotCaptured) );
			} catch (IOException e) {
				Log.e( ActividadPantallaWebcamPrincipal.class.getName(), "Screenshot couldn't be captured.", e );
			}
    		
        }
        
    };
    
    private OnClickListener upArrowOnClickListener = new OnClickListener() {
        
    	public void onClick(View v) {
        	
        	try {
        		controladorServos.adelante();
        		Log.d( ActividadPantallaWebcamPrincipal.class.getName(), "go forward executed." );
        	} catch (Exception e) {
        		Log.e( ActividadPantallaWebcamPrincipal.class.getName(), "go forward couldn't be executed.", e );
			}
        	
        }
        
    };
    
    private OnClickListener downArrowOnClickListener = new OnClickListener() {
        
    	public void onClick(View v) {
        	
        	try {
        		controladorServos.atras();
        		Log.d( ActividadPantallaWebcamPrincipal.class.getName(), "go back executed." );
        	} catch (Exception e) {
        		Log.e( ActividadPantallaWebcamPrincipal.class.getName(), "go back couldn't be executed.", e );
			}
        	
        }
        
    };
    
    private OnClickListener leftArrowOnClickListener = new OnClickListener() {
        
    	public void onClick(View v) {
        	
        	try {
        		controladorServos.girarIzq();
        		Log.d( ActividadPantallaWebcamPrincipal.class.getName(), "turn left executed." );
        	} catch (Exception e) {
        		Log.e( ActividadPantallaWebcamPrincipal.class.getName(), "turn left couldn't be executed. ", e );
			}
        	
        }
        
    };
    
    private OnClickListener rightArrowOnClickListener = new OnClickListener() {
        
    	public void onClick(View v) {
        	
        	try {
        		controladorServos.girarDer();
        		Log.d( ActividadPantallaWebcamPrincipal.class.getName(), "turn right executed." );
        	} catch (Exception e) {
        		Log.e( ActividadPantallaWebcamPrincipal.class.getName(), "turn right couldn't be executed. ", e );
			}
        	
        }
        
    };
    
    private void initializeStreamingView()
    {
        mv = (MjpegView) this.findViewById( R.id.mjpeg_view_main );
        mv.setSource( MjpegInputStream.read( "http://" + Configuracion.getConfigString( Constantes.IP_PLAYER ) + ":"
            + Configuracion.getConfigString( Constantes.MAIN_WEBCAM_PORT ) ) );
        mv.setDisplayMode( MjpegView.SIZE_BEST_FIT );

        mv.showFps( super.getPreferences().getBoolean( "Show FPS Key", true ) );
    }

    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
    }

    @Override
    public void onPause()
    {
        super.onPause();
        mv.stopPlayback();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        mv.stopPlayback();
    }

}
