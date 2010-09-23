package ar.edu.uade.android.utils;

import java.util.Properties;

import android.util.Log;

public class Configuracion
{
    private static Properties properties;

    private Configuracion()
    {
        properties = new Properties();

        // Properties de configuracion
        properties.put( Constantes.ONLINE_PLAYER, "true" );
        properties.put( Constantes.BRAZO_HORIZ_ID, "7" );
        properties.put( Constantes.BRAZO_VERT_ID, "6" );
        properties.put( Constantes.BRAZO_MANO_ID, "5" );
        properties.put( Constantes.BRAZO_HORIZ_MIN, "0" );
        properties.put( Constantes.BRAZO_HORIZ_MAX, "127" );
        properties.put( Constantes.BRAZO_VERT_MIN, "0" );
        properties.put( Constantes.BRAZO_VERT_MAX, "63" );
        properties.put( Constantes.BRAZO_MANO_MIN, "63" );
        properties.put( Constantes.BRAZO_MANO_MAX, "127" );
        properties.put( Constantes.BRAZO_STEP, "127" );

        // CONSTANTES DEL BRAZO, configuraciones por defecto.
        properties.put( Constantes.SERVO_HORIZONTAL_ARRAY, "0" );
        properties.put( Constantes.SERVO_VERTICAL_ARRAY, "1" );
        properties.put( Constantes.SERVO_MANO_ARRAY, "2" );

        // CONSTANTES DE LA CAMARA
        properties.put( Constantes.CAMARA_PRINCIPAL, "1" );
        properties.put( Constantes.CAMARA_BRAZO, "0" );

        Log.d( Configuracion.class.getName(), "Configuracion cargada." );
    }

    /**
     * Obtiene el valor de una key como string
     * @param key de la property deseada
     * @return el valor para la key
     */
    public static String getConfigString( String key )
    {
        inicializarProperties();
        return properties.getProperty( key );
    }

    /**
     * Obtiene el valor de una key como int
     * @param key de la property deseada
     * @return el valor para la key
     */
    public static int getConfigInt( String key )
    {
        inicializarProperties();
        try
        {
            return Integer.valueOf( properties.getProperty( key ) );
        }
        catch ( NumberFormatException nfe )
        {
            Log.d( Configuracion.class.getName(), "No se pudo castear la property: " + key + " a entero." );
            return 0;
        }
    }

    /**
     * Obtiene el valor de una key como boolean
     * @param key de la property deseada
     * @return el valor para la key
     */
    public static boolean getConfigBoolean( String key )
    {
        inicializarProperties();
        return Boolean.valueOf( properties.getProperty( key ) );
    }

    /**
     * Agrega una property con key y value
     * @param key de la property deseada
     * @param value el objeto
     */
    public static void setConfiguracion( String key, String value )
    {
        inicializarProperties();
        properties.put( key, value );
        Log.d( Configuracion.class.getName(), "Se agrego a la configuracion la key: " + key + " con value:  " + value );
    }

    /**
     * Chequea que las properties esten cargadas, de lo contrario las carga.
     */
    private static void inicializarProperties()
    {
        if ( properties == null )
        {
            new Configuracion();
        }
    }
}
