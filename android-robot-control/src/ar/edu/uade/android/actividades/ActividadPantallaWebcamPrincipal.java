package ar.edu.uade.android.actividades;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import ar.edu.uade.android.R;
import ar.edu.uade.android.mjpeg.MjpegInputStream;
import ar.edu.uade.android.mjpeg.MjpegView;
import ar.edu.uade.android.servicios.ServicioBrazo;
import ar.edu.uade.android.utils.Configuracion;
import ar.edu.uade.android.utils.Constantes;

public class ActividadPantallaWebcamPrincipal extends ActividadPantallaAbstract {

    private MjpegView mv;
    
    private ServicioBrazo brazo;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        // TODO Auto-generated method stub
        super.onCreate( savedInstanceState );

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView( R.layout.webcam_principal );
        
        Button button = (Button)findViewById(R.id.button_test);
        button.setOnClickListener(mCorkyListener);
        
        brazo =  new ServicioBrazo();

        startStreaming();
    }
    
    // Create an anonymous implementation of OnClickListener
    private OnClickListener mCorkyListener = new OnClickListener() {
        public void onClick(View v) {
        	
        	try {
        		brazo.brazoBajar(1);
        		Log.d( "un exito", "todo");
        	} catch (Exception e) {
				Log.e( "Como el toor", "todo");
			}
        	
        	
        }
    };

    private void startStreaming()
    {
        mv = (MjpegView) this.findViewById( R.id.mjpeg_view_main );
        mv.setSource( MjpegInputStream.read( "http://" + Configuracion.getConfigString( Constantes.IP_PLAYER ) + ":"
            + Configuracion.getConfigString( Constantes.MAIN_WEBCAM_PORT ) ) );
        mv.setDisplayMode( MjpegView.SIZE_BEST_FIT );

        mv.showFps( super.getPreferences().getBoolean( "Show FPS Key", true ) );
    }

    @Override
    public boolean onTrackballEvent( MotionEvent event )
    {
        boolean eventHandled = false;

        TextView posX = (TextView) this.findViewById( R.id.pos_x );
        TextView posY = (TextView) this.findViewById( R.id.pos_y );

        if ( event.getAction() == MotionEvent.ACTION_MOVE )
        {
            posX.setText( "X: " + String.valueOf( event.getX() ) );
            posY.setText( "Y: " + String.valueOf( event.getY() ) );

            eventHandled = true;
        }

        return eventHandled;
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
