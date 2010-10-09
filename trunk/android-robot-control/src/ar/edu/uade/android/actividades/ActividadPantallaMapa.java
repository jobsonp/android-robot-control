package ar.edu.uade.android.actividades;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.Toast;
import ar.edu.uade.android.R;
import ar.edu.uade.android.servicios.RobotService;
import ar.edu.uade.android.utils.GpsPosition;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class ActividadPantallaMapa extends ActividadPantallaAbstract implements OnMenuItemClickListener {
	
	private IntentFilter mFilter;
	
	private BroadcastReceiver mReceiver;

    private MapView mapView;

    private MapController mc;

    private GeoPoint p;
    
    @Override
    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        
        /* instantiate the filter and the receiver */
		mFilter = getIntentFilter();
		mReceiver = getReceiver();
        
        setContentView( R.layout.mapa );
        /* Start the service if it's not up */
		startService(getServerIntent());
        createMap();
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		registerReceiver(mReceiver, mFilter);
	}

	@Override
	public void onPause() {
		super.onPause();
		unregisterReceiver(mReceiver);
	}

	public BroadcastReceiver getReceiver() {
		return new GpsPositionUpdateReceiver();
	}

	protected Intent getServerIntent() {
		Intent i;
		i = new Intent(this, RobotService.class);
		i.setAction(RobotService.NEW_GPS_POSITION);
		return i;
	}

	protected IntentFilter getIntentFilter() {
		return new IntentFilter(RobotService.NEW_GPS_POSITION);
	}
	
	private class GpsPositionUpdateReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context arg0, Intent i) {
			GpsPosition pos = (GpsPosition)i.getExtras().get("newGpsPosition");
			updateGpsPosition(pos);
		}
	}
	
	private void createMap() {
        mapView = (MapView) findViewById( R.id.mapa );
        mapView.setBuiltInZoomControls(true);
        mapView.displayZoomControls( true );
    }
	
	private void updateGpsPosition(GpsPosition pos) {
		
		if (pos!=null) {
			
			float latitude = pos.getLatitud();
			float longitude = pos.getLongitud();

			mc = mapView.getController();

			double latPoint = latitude;
			double longPoint = longitude;

			p = new GeoPoint( (int) ( latPoint * 1E6 ), (int) ( longPoint * 1E6 ) );

			mc.animateTo( p );
			mc.setZoom( 16 );
		        
			Toast.makeText( this, R.string.toast_posicionActualizada, Toast.LENGTH_SHORT ).show();
			
		}
       
	}
	
	@Override
    protected boolean isRouteDisplayed() {
        return false;
    }

}
