package ar.edu.uade.android.actividades;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import ar.edu.uade.android.constantes.Constantes;

public class ActividadDesconectar
    extends Activity
{

    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        // 1) Abrir aviso de loading
        // 2) Desconectar y hacer toda la cochinada
        // 3) Cerrar aviso de loading
        
        startActivity( new Intent( Constantes.INTENT_PANTALLA_CONEXION ) );
    }
}
