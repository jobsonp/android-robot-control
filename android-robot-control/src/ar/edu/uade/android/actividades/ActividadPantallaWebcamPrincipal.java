package ar.edu.uade.android.actividades;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import ar.edu.uade.android.R;


public class ActividadPantallaWebcamPrincipal
    extends ActividadPantallaAbstract 
{
    
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        // TODO Auto-generated method stub
        super.onCreate( savedInstanceState );
        
        setContentView( R.layout.webcam_principal );
    }

    @Override
    public boolean onTrackballEvent( MotionEvent event )
    {
        boolean eventHandled = false;

        TextView posX = (TextView) this.findViewById( R.id.pos_x );
        TextView posY = (TextView) this.findViewById( R.id.pos_y );
        
        if( event.getAction() == MotionEvent.ACTION_MOVE )
        {
            posX.setText( String.valueOf(  event.getX( ) ) );
            posY.setText( String.valueOf(  event.getY( ) ) );
            
            eventHandled = true;
        }
        
        return eventHandled;
    }
    
    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
    }
}


