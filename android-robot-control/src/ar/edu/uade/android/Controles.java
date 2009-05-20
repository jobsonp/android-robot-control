package ar.edu.uade.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class Controles
    extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.control );
        Button buttonAvanzar = (Button) this.findViewById( R.id.ButtonAvanzar );
        Button buttonRetroceder = (Button) this.findViewById( R.id.ButtonRetroceder );
        Button buttonDerecha = (Button) this.findViewById( R.id.ButtonDerecha );
        Button buttonIzquierda = (Button) this.findViewById( R.id.ButtonIzquierda );
    }
}
