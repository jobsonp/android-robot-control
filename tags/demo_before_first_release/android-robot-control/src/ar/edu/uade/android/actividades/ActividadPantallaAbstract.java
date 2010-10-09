package ar.edu.uade.android.actividades;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.EditText;
import android.widget.Toast;
import ar.edu.uade.android.R;
import ar.edu.uade.android.controladores.ControladorSpeech;
import ar.edu.uade.android.utils.Constantes;

import com.google.android.maps.MapActivity;

public abstract class ActividadPantallaAbstract extends MapActivity implements OnMenuItemClickListener {
    
	private SharedPreferences preferences;
	
    @Override
    protected void onCreate( Bundle icicle )
    {
        super.onCreate( icicle );
        preferences = getSharedPreferences( Constantes.NOMBRE_ARCHIVO_PREFERENCIAS, 0 );
        
    }
    
    public boolean onCreateOptionsMenu( Menu menu )
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate( R.menu.menu, menu );

        menu.getItem( 0 ).setOnMenuItemClickListener( this );
        menu.getItem( 1 ).setOnMenuItemClickListener( this );
        menu.getItem( 2 ).setOnMenuItemClickListener( this );

        menu.getItem( 0 ).getSubMenu().getItem( 0 ).setOnMenuItemClickListener( this );
        menu.getItem( 0 ).getSubMenu().getItem( 1 ).setOnMenuItemClickListener( this );
        menu.getItem( 0 ).getSubMenu().getItem( 2 ).setOnMenuItemClickListener( this );

        return true;
    }

    @Override
    public boolean onMenuItemClick( MenuItem item )
    {
        if ( item.getItemId() == R.id.MenuDesconectar )
        {
            startActivity( new Intent( Constantes.INTENT_ACCIONES_DESCONECTAR ) );
        }
        else if ( item.getItemId() == R.id.SubmenuItemVistaCamaraBrazo )
        {
            item.setChecked( true );
            startActivity( new Intent( Constantes.INTENT_PANTALLA_WEBCAM_BRAZO ) );
        }
        else if ( item.getItemId() == R.id.SubmenuItemVistaCamaraPrincipal )
        {
            item.setChecked( true );
            startActivity( new Intent( Constantes.INTENT_PANTALLA_WEBCAM_PRINCIPAL ) );
        }
        else if ( item.getItemId() == R.id.SubmenuItemVistaMapa )
        {
            item.setChecked( true );
            startActivity( new Intent( Constantes.INTENT_PANTALLA_MAPA ) );
        }
        else if ( item.getItemId() == R.id.MenuConfiguracion )
        {
            startActivity( new Intent( Constantes.INTENT_PANTALLA_CONFIGURACION ) );
        }
        return false;
    }

    public SharedPreferences getPreferences()
    {
        return preferences;
    }
    
    public void toast( String s )
    {
        Context context = getApplicationContext();
        CharSequence text = s;
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText( context, text, duration );
        toast.show();
    }
    
    public void vibrationFeedback( long milliseconds ) 
    {
		
		( (Vibrator) getSystemService( VIBRATOR_SERVICE ) ).vibrate( milliseconds );
		
    }
    
    public void screenshot( View v ) throws IOException 
    {
    	
		View v1 = v.getRootView();
		v1.setDrawingCacheEnabled( true );
		Bitmap bm = v1.getDrawingCache();
		
		// Para ARC el path deberia ser configurable
		File file = new File( Environment.getExternalStorageDirectory().toString() + "/" + System.currentTimeMillis() + ".jpg" );
		file.createNewFile();
		
		OutputStream fOut = new FileOutputStream( file );
		bm.compress( Bitmap.CompressFormat.PNG, 85, fOut );
		fOut.flush();
		fOut.close();
		
		MediaStore.Images.Media.insertImage( getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName() );

    }
    
    public void dialog()
    {
    	final ControladorSpeech controladorSpeech = new ControladorSpeech( );
    	
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        
        builder.setTitle( "Ingresar Mensaje" );
        
        builder.setCancelable( false );
        
        // Set an EditText view to get user input 
        final EditText input = new EditText(this);
        builder.setView(input);
        
		builder.setPositiveButton( "Enviar", new DialogInterface.OnClickListener() {
		        public void onClick( DialogInterface dialog, int id )
		        {	
		        	String value = input.getText().toString();
		        	
		        	if ( value!=null ) {
		        		
		        		try {
		        			
		        			controladorSpeech.decirTexto(value);
		        			
		        		} catch (Exception e) {
		        			
		        			Log.e( ActividadPantallaAbstract.class.getName(), "dialog - speech - error.", e );
		        			
						}
		        		
		        	}
		        	
		        }
		} );
        
		builder.setNegativeButton( "Cancelar", new DialogInterface.OnClickListener()
        {
            public void onClick( DialogInterface dialog, int id )
            {
                dialog.cancel();
            }
        } );
        
        AlertDialog d = builder.create();

        d.show();
        
    }

}
