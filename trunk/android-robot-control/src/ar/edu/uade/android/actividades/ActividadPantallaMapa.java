package ar.edu.uade.android.actividades;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.Toast;
import ar.edu.uade.android.R;
import ar.edu.uade.android.servicios.ServicioGPS;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class ActividadPantallaMapa extends ActividadPantallaAbstract implements OnMenuItemClickListener {

	PositionReceiver receiver;
	
    MapView mapView;

    MapController mc;

    GeoPoint p;

    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.mapa );
        refreshGPSPosition();
    }
    
    @Override
    protected void onResume() {
    	IntentFilter filter;
	    filter = new IntentFilter(ServicioGPS.POSITION_UPDATED);
	    receiver = new PositionReceiver();
	    registerReceiver(receiver, filter);
		super.onResume();
    }

    @Override
    public void onPause() {
      unregisterReceiver(receiver);
      super.onPause();
    }
    
    @SuppressWarnings("deprecation")
	private void refreshGPSPosition() {
    	mapView = (MapView) findViewById( R.id.mapa );
        LinearLayout zoomLayout = (LinearLayout) findViewById( R.id.layout_zoom );
        View zoomView = mapView.getZoomControls();

        zoomLayout.addView( zoomView, new LinearLayout.LayoutParams( LayoutParams.WRAP_CONTENT,
                                                                     LayoutParams.WRAP_CONTENT ) );
        mapView.displayZoomControls( true );
        startService(new Intent(this, ServicioGPS.class));
    }
    
    public class PositionReceiver extends BroadcastReceiver {
    	
  	  @Override
  	  public void onReceive(Context context, Intent intent) {
  		loadPositionFromProvider(intent);
  	  }
  	  
 	}
    
	private void loadPositionFromProvider(Intent intent) {
    	
    	float latitude = (Float)intent.getExtras().get("latitude");
    	float longitude = (Float)intent.getExtras().get("latitude");
    	
        mc = mapView.getController();

        double latPoint = latitude;
        double longPoint = longitude;

        p = new GeoPoint( (int) ( latPoint * 1E6 ), (int) ( longPoint * 1E6 ) );

        mc.animateTo( p );
        mc.setZoom( 10 );
        mapView.invalidate();
        
        Toast.makeText( this, R.string.toast_posicionActualizada, Toast.LENGTH_SHORT ).show();
        
    }
    
    @Override
    protected boolean isRouteDisplayed() {
      return false;
    }

}
