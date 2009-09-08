package ar.edu.uade.android.actividades;

import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;
import ar.edu.uade.android.R;


public class ActividadPantallaWebcamBrazo
    extends ActividadPantallaAbstract
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        // TODO Auto-generated method stub
        super.onCreate( savedInstanceState );
        
        setContentView( R.layout.webcam_brazo );
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
            trackballClick.setText( "Down!" );

            eventHandled = true;
        }
        else if( event.getAction() == MotionEvent.ACTION_UP )
        {
            trackballClick.setText( "Up!" );

            eventHandled = true;
        }
        else if( event.getAction() == MotionEvent.ACTION_CANCEL )
        {
            trackballClick.setText( "Cancel!" );

            eventHandled = true;
        }
        else if( event.getAction() == MotionEvent.ACTION_MOVE )
        {
            trackballClick.setText( "Move!" ); 
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