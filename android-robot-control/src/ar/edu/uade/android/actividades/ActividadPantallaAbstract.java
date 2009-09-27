package ar.edu.uade.android.actividades;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import ar.edu.uade.android.R;
import ar.edu.uade.android.utils.Constantes;

import com.google.android.maps.MapActivity;

public abstract class ActividadPantallaAbstract extends MapActivity implements OnMenuItemClickListener
{

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
        if( item.getItemId() == R.id.MenuDesconectar )
        {
            startActivity( new Intent( Constantes.INTENT_ACCIONES_DESCONECTAR ) );
        }
        else if( item.getItemId() == R.id.SubmenuItemVistaCamaraBrazo )
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
            startActivity( new Intent( Constantes.INTENT_PANTALLA_CONFIGURACION) );
        }
        return false;
    }
}
