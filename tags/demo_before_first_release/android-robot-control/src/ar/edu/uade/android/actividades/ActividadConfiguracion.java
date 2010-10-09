package ar.edu.uade.android.actividades;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import ar.edu.uade.android.R;

public class ActividadConfiguracion
    extends PreferenceActivity
{
    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );

        addPreferencesFromResource( R.xml.configuracion );
    }
}
