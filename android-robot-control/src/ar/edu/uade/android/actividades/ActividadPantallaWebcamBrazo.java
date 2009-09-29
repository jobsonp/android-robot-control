package ar.edu.uade.android.actividades;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import ar.edu.uade.android.R;
import ar.edu.uade.android.mjpeg.MjpegInputStream;
import ar.edu.uade.android.mjpeg.MjpegView;
import ar.edu.uade.android.utils.Configuracion;
import ar.edu.uade.android.utils.Constantes;


public class ActividadPantallaWebcamBrazo
    extends ActividadPantallaAbstract
{
    private MjpegView mv;
    
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        // TODO Auto-generated method stub
        super.onCreate( savedInstanceState );

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView( R.layout.webcam_brazo );

        mv = (MjpegView) this.findViewById( R.id.mjpeg_view_brazo );

        startStreaming();
    }

    private void startStreaming()
    {
        mv.setSource( MjpegInputStream.read( "http://" + Configuracion.getConfigString( Constantes.PORT_PLAYER ) + ":"
            + Configuracion.getConfigString( Constantes.ARM_WEBCAM_PORT ) ) );
        mv.setDisplayMode( MjpegView.SIZE_BEST_FIT );
        mv.showFps( super.getPreferences().getBoolean( "Show FPS Key", true ) );
    }

    @Override
    public boolean onTrackballEvent( MotionEvent event )
    {
        boolean eventHandled = false;
        
        TextView trackballClick = (TextView) this.findViewById( R.id.TrackballClick );
        TextView posX = (TextView) this.findViewById( R.id.pos_x_brazo );
        TextView posY = (TextView) this.findViewById( R.id.pos_y_brazo );
        
        trackballClick.setText( "" );
        posX.setText( "" );
        posY.setText( "" );
        
        if( event.getAction() == MotionEvent.ACTION_DOWN )
        {
            trackballClick.setText( "Event: Down!" );

            eventHandled = true;
        }
        else if( event.getAction() == MotionEvent.ACTION_UP )
        {
            trackballClick.setText( "Event: Up!" );

            eventHandled = true;
        }
        else if( event.getAction() == MotionEvent.ACTION_CANCEL )
        {
            trackballClick.setText( "Event: Cancel!" );

            eventHandled = true;
        }
        else if( event.getAction() == MotionEvent.ACTION_MOVE )
        {
            trackballClick.setText( "Event: Move!" ); 
            posX.setText( "X: " + String.valueOf(  event.getX( ) ) );
            posY.setText( "Y: " + String.valueOf(  event.getY( ) ) );
            
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