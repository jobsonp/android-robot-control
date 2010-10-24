package framework.modulos.player;

import java.util.Vector;

import javaclient2.GPSInterface;
import javaclient2.PlayerClient;
import framework.conector.Conector;
import framework.interfaces.IModuloGPS;

public class ModuloPlayerGPS implements IModuloGPS {

    private static ModuloPlayerGPS instance = null;

    private ModuloPlayerGPS()
    {
    }

    public static ModuloPlayerGPS getInstance()
    {
        if ( instance == null )
            instance = new ModuloPlayerGPS();
        return instance;
    }

    @SuppressWarnings("unchecked")
	private float[] getDatos()
    {
        float[] datos = { 0f, 0f, 0f };
        Conector conector = Conector.getInstance();
        Vector vv = conector.getInterfaceGPS();
        ( (PlayerClient) vv.elementAt( 0 ) ).start();
        ( (PlayerClient) vv.elementAt( 0 ) ).readAll();
        // OBTENER DATOS
        if ( ( (GPSInterface) vv.elementAt( 1 ) ).isDataReady() )
        {
            datos[0] = ( ( (GPSInterface) vv.elementAt( 1 ) ).getData().getAltitude() / 1000 );
            datos[1] = ( (GPSInterface) vv.elementAt( 1 ) ).getData().getLatitude();
            datos[2] = ( (GPSInterface) vv.elementAt( 1 ) ).getData().getLongitude();
        }
        try
        {
            Thread.sleep( 1000 );
        }
        catch ( InterruptedException e )
        {
            e.printStackTrace();
        }
        // CERRAR CONEXION
        ( (PlayerClient) vv.elementAt( 0 ) ).stop();
        return datos;
    }

    public float getAltitude()
    {
        float[] data = getDatos();
        return data[0];
    }

    public float getLatitude()
    {
        float[] data = getDatos();
        return data[1];
    }

    public float getLongitude()
    {
        float[] data = getDatos();
        return data[2];
    }

}
