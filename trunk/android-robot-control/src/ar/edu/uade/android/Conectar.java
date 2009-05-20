package ar.edu.uade.android;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import ar.edu.uade.android.constantes.Constantes;
import ar.edu.uade.android.dialogo.DialogoSalir;

public class Conectar
    extends Activity
{
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.main );
        Button buttonConectar = (Button) this.findViewById( R.id.ButtonConectar );

        buttonConectar.setOnClickListener( new Button.OnClickListener()
        {
            public void onClick( View v )
            {
                // 1) Intentar conectar
                // 2) Lanzar un nuevo thread para streaming

                startActivity( new Intent( Constantes.PANTALLA_MAIN_CONTROL ) );
            }

        } );

        Button buttonCancelar = (Button) this.findViewById( R.id.ButtonCancelar );
        buttonCancelar.setOnClickListener( new Button.OnClickListener()
        {
            public void onClick( View v )
            {
                new DialogoSalir( v.getContext() );
            }
        } );
    }

}