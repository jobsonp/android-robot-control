package ar.edu.uade.android.actividades;

import android.os.Bundle;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import ar.edu.uade.android.R;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class ActividadPantallaMapa
    extends ActividadPantallaAbstract
    implements OnMenuItemClickListener
{

    MapView mapView;

    MapController mc;

    GeoPoint p;

    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.mapa );

        mapView = (MapView) findViewById( R.id.mapa );
        LinearLayout zoomLayout = (LinearLayout) findViewById( R.id.layout_zoom );
        View zoomView = mapView.getZoomControls();

        zoomLayout.addView( zoomView, new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT,
                                                                     LayoutParams.WRAP_CONTENT ) );
        mapView.displayZoomControls( true );
        mc = mapView.getController();

        // Get robot location from ServicioGPS

        double latPoint = 1.352566007D;
        double longPoint = 103.78921587D;

        p = new GeoPoint( (int) ( latPoint * 1E6 ), (int) ( longPoint * 1E6 ) );

        mc.animateTo( p );
        mc.setZoom( 10 );
        mapView.invalidate();
    }

    @Override
    protected boolean isRouteDisplayed()
    {
        return false;
    }
}