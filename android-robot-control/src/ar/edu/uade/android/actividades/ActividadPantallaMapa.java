package ar.edu.uade.android.actividades;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ToggleButton;
import ar.edu.uade.android.R;
import ar.edu.uade.android.servicios.RobotService;
import ar.edu.uade.android.utils.GpsPosition;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class ActividadPantallaMapa
    extends ActividadPantallaAbstract
{

    private IntentFilter mFilter;

    private BroadcastReceiver mReceiver;

    private MapView mapView;

    private MapController mc;

    private GeoPoint p;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        /* instantiate the filter and the receiver */
        mFilter = getIntentFilter();
        mReceiver = getReceiver();

        setContentView( R.layout.mapa );
        /* Start the service if it's not up */
        startService( getServerIntent() );
        createMap();
        
        ((ToggleButton)findViewById( R.id.boton_vista_principal )).setChecked( false );
        ((ToggleButton)findViewById( R.id.boton_vista_brazo )).setChecked( false );
        ((ToggleButton)findViewById( R.id.boton_vista_mapa )).setChecked( true );
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        registerReceiver( mReceiver, mFilter );
    }

    @Override
    public void onPause()
    {
        super.onPause();
        unregisterReceiver( mReceiver );
    }

    public BroadcastReceiver getReceiver()
    {
        return new GpsPositionUpdateReceiver();
    }

    protected Intent getServerIntent()
    {
        Intent i;
        i = new Intent( this, RobotService.class );
        i.setAction( RobotService.NEW_GPS_POSITION );
        return i;
    }

    protected IntentFilter getIntentFilter()
    {
        return new IntentFilter( RobotService.NEW_GPS_POSITION );
    }

    private class GpsPositionUpdateReceiver
        extends BroadcastReceiver
    {

        @Override
        public void onReceive( Context arg0, Intent i )
        {
            GpsPosition pos = (GpsPosition) i.getExtras().get( "newGpsPosition" );
            updateGpsPosition( pos );
        }
    }

    private void createMap()
    {
        mapView = (MapView) findViewById( R.id.vista_mapa );
        mapView.setBuiltInZoomControls( true );
        mapView.displayZoomControls( true );
    }

    private void updateGpsPosition( GpsPosition pos )
    {

        if ( pos != null )
        {

            float latitude = pos.getLatitud();
            float longitude = pos.getLongitud();

            mc = mapView.getController();

            double latPoint = latitude;
            double longPoint = longitude;

            p = new GeoPoint( (int) ( latPoint * 1E6 ), (int) ( longPoint * 1E6 ) );

            mc.animateTo( p );
            mc.setZoom( 16 );

            // Toast.makeText( this, R.string.toast_posicionActualizada, Toast.LENGTH_SHORT
            // ).show();
        }
    }

    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
    }

    @Override
    public void ejecutarFlechaAbajo()
    {
        getControladorServos().atras();
    }

    @Override
    public void ejecutarFlechaArriba()
    {
        getControladorServos().adelante();
    }

    @Override
    public void ejecutarFlechaDerecha()
    {
        getControladorServos().girarDer();
    }

    @Override
    public void ejecutarFlechaIzquierda()
    {
        getControladorServos().girarIzq();
    }

    @Override
    public Bitmap obtenerBitmap()
    {
        MapView vistaMapa = (MapView)findViewById( R.id.vista_mapa );
        vistaMapa.setDrawingCacheQuality( View.DRAWING_CACHE_QUALITY_LOW );
        vistaMapa.setDrawingCacheEnabled( true );
        return vistaMapa.getDrawingCache();
    }
}
