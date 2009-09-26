package ar.edu.uade.android.actividades;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import ar.edu.uade.android.R;
import ar.edu.uade.android.mjpeg.MjpegInputStream;
import ar.edu.uade.android.mjpeg.MjpegView;

public class ActividadPantallaWebcamPrincipal
    extends ActividadPantallaAbstract
{

    private MjpegView mv;

    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        // TODO Auto-generated method stub
        super.onCreate( savedInstanceState );

        // sample public cam
        String URL = "http://10.0.2.2:1234";

        requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN );

        setContentView( R.layout.webcam_principal );

        mv = (MjpegView) this.findViewById( R.id.mjpeg_view_main );

        mv.setSource( MjpegInputStream.read( URL ) );
        mv.setDisplayMode( MjpegView.SIZE_BEST_FIT );
        mv.showFps( true );
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
