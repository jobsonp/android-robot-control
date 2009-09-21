package ar.edu.uade.android.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import ar.edu.uade.android.R;
import ar.edu.uade.android.utils.Constantes;

public class ActividadDesconectar
    extends Activity
{

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        // 1) Abrir aviso de loading
        // 2) Desconectar y hacer toda la cochinada
        // 3) Cerrar aviso de loading
        
        Toast.makeText( this, R.string.toast_desconectando, Toast.LENGTH_SHORT ).show();
        
        startActivity( new Intent( Constantes.INTENT_PANTALLA_CONEXION ) );
    }
}
